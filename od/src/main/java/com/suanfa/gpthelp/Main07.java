package com.suanfa.gpthelp;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main07 {
    // 编码时，类型 -> 数值
    static HashMap<String, String> type2num = new HashMap<>();
    // 解码时，数值 -> 类型
    static HashMap<String, String> num2type = new HashMap<>();

    static Pattern num_RegExp = Pattern.compile("^\\d+$");
    static Pattern str_RegExp = Pattern.compile("^[0-9a-zA-Z\\s]+$");


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int command = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();

        // 编码时，类型 -> 数值
        type2num.put("Integer", "0");
        type2num.put("String", "1");
        type2num.put("Compose", "2");

        // 解码时，数值 -> 类型
        num2type.put("0", "Integer");
        num2type.put("1", "String");
        num2type.put("2", "Compose");

        switch (command) {
            case 1:
                System.out.println(encode(str));
                break;
            case 2:
                try {
                    System.out.println(decode(str));
                } catch (Exception e) {
                    // 待解码字符串如果解码过程中发生了异常，则说明格式或者嵌套关系存在问题，则此时报错
                    System.out.println("DECODE_ERROR");
                }
                break;
        }
    }

    // 校验待编码的字符串的格式
    public static boolean check_encode_str(String str) {
        // 这里通过栈结构检查代编码字符串中[,]字符的闭合
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == ']') {
                while (!stack.isEmpty() && stack.getLast() != '[') {
                    stack.removeLast();
                }

                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.removeLast();
                }

            } else {
                stack.addLast(c);
            }
        }

        return stack.isEmpty();
    }

    public static boolean check_encoded(String pos, String type, String data) {
        if (!num_RegExp.matcher(pos).find()) {
            return false;
        }

        if (!type2num.containsKey(type)) {
            return false;
        }

        if ("Integer".equals(type)) {
            return num_RegExp.matcher(data).find();
        } else if ("String".equals(type)) {
            return str_RegExp.matcher(data).find();
        }

        return true;
    }

    // 编码
    public static String encode(String s) {
        // 将数据与数据之间的逗号分隔去除
        // 例如，待编码字符串是：
        // [1,String,I am Mary],[2,Integer,23],[3,Long,1000000],[4,Compose,[1,String,I am Kitty],[2,Integer,44]]
        // 去除数据与数据之间的逗号后，变为
        // [1,String,I am Mary][2,Integer,23][3,Long,1000000][4,Compose,[1,String,I am Kitty][2,Integer,44]]
        s = s.replaceAll("],\\[", "][");

        // 待编码字符串的格式校验
        if (!check_encode_str(s)) {
            return "ENCODE_ERROR";
        }

        // 由于后面我想用正则匹配取出字符串中 "[位置,类型,值]" 信息，而 '[', ']' 字符又是正则表达式的元字符
        // 因此为了简单实现正则，这里先将待编码字符串str中所有 '[' 替换为 '<', 所有 ']' 替换为 '>'
        // 例如，待编码字符串是：
        // [1,String,I am Mary],[2,Integer,23],[3,Long,1000000],[4,Compose,[1,String,I am Kitty],[2,Integer,44]]
        // 经过替换操作后，就变为了:
        // <1,String,I am Mary><2,Integer,23><3,Long,1000000><4,Compose,<1,String,I am Kitty><2,Integer,44>>
        s = s.replaceAll("\\[", "<").replaceAll("]", ">");

        // 该正则用于匹配出待编码字符串中 "<位置,类型,值>"
        Pattern p = Pattern.compile("<([^<>]+)>");

        while (true) {
            Matcher m = p.matcher(s);
            if (!m.find()) break;

            // m.group(0) 是正则匹配到的完整子串 "<位置,类型,值>"
            // m.group(1)用于获取是第一个正则捕获组，所谓正则捕获组，即正则表达式中()中的正则能匹配到的内容，如上正则捕获组即为：[^<>]+, 匹配到的内容是："位置,类型,值"
            String[] tmp = m.group(1).split(",");

            String pos = tmp[0];
            String type = tmp[1];
            String data = tmp[2];

            // sj记录编码内容
            String encodeStr = "";

            if (check_encoded(pos, type, data)) {
                encodeStr = pos + "#" + type2num.get(type) + "#" + data.length() + "#" + data;
            }

            s = s.replace(m.group(0), encodeStr); // 将匹配到的内容 替换为 编码后的内容
        }

        return s;
    }

    public static boolean check_decoded(String pos, String type, String data) {
        if (!num_RegExp.matcher(pos).find()) {
            return false;
        }

        if (!num2type.containsKey(type)) {
            return false;
        }

        if ("0".equals(type)) {
            return num_RegExp.matcher(data).find();
        } else if ("1".equals(type)) {
            return str_RegExp.matcher(data).find();
        }

        return true;
    }


    // 解码
    public static String decode(String str) {
        // 解码有如下难点：
        // 1、各数据区之间没有分隔符，即无法直接分离出各个数据区
        // 2、Compose类型数据区的"数据"部分也是一个或多个数据区组合

        // 我这里直接将待解码字符串str按照'#'分割, 并加入到队列中, 方便头部出队
        LinkedList<String> queue = new LinkedList<>();
        Collections.addAll(queue, str.split("#"));

        // res记录解码后的内容
        StringJoiner res = new StringJoiner(",");

        // 如果队列不空，则继续循环
        while (!queue.isEmpty()) {
            // 如果待解码字符串是合法的，则：
            // 第一个#分割出来的内容认定为当前数据区的pos
            String pos = queue.removeFirst();
            // 第二个#分割出来的内容认定为当前数据区的type
            String type = queue.removeFirst();
            // 第三个#分割出来的内容认定为当前数据区的len
            int len = Integer.parseInt(queue.removeFirst());

            // 剩余部分重新以#拼接
            String remain = String.join("#", queue);
            queue.clear();

            // remain字符串的 [0,len) 部分作为当前数据区的data信息
            String data = remain.substring(0, len);

            if (remain.length() > len) {
                // remain字符串的 [len,end) 部分是其他数据区，按照#分割后重新入队
                Collections.addAll(queue, remain.substring(len).split("#"));
            }

            if ("2".equals(type)) {
                data = decode(data);
            }

            // pos, type, data都合法则进行解法
            if (check_decoded(pos, type, data)) {
                // 解码
                res.add("[" + pos + "," + num2type.get(type) + "," + data + "]");
            }
        }

        return res.toString();
    }
}
