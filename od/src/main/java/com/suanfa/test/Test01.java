package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;


public class Test01 {
    public static void main(String[] args) {
        Test01 test = new Test01();
        test.findStrBm("HERE IS A SIMPLE EXAMPLE", "EXAMPLE");
    }

    public void findStrBm(String original, String find){
        //尾指针
        int r = 0, l = 0;
        //匹配串指针
        int k = find.length() - 1;
        //寻找串指针
        int t = find.length() - 1;
        //相等个数
        int x = 0;
        //后缀指针
        int y = 0;
        //坏字符
        char b;
        //退出标志
        boolean flag = false;
        while(k < original.length() && flag == false){
//            System.out.println("k; " + k + " r; " + r + " t; " + t);
            //碰到坏字符 判断
            if (((b = original.charAt(k)) != find.charAt(t))) {
                int value = 0;
                if(x != 0){
                    //有已匹配好的字符串 计算好后缀移动位数 后移位数 = 好后缀在find中的位置 - 好后缀在find中上一次出现的位置
                    int goodTr = findGoodTr(find, find.substring(find.length() - x));
                    k = r + goodTr;
                    r = k;
                    t = l;
                    //清零已匹配数量
                    x = 0;
                } else {
                    if ((value = findCharin(find, b)) == -1) {
                        //不存在 直接后移find的长度位
                        k += (t + 1);
                        r = k;
                        l = t;
                    } else {
                        //存在 移动 t - value 位
                        int i = t - value;
                        l = t;
                        r = r + i;
                        t = t - i;
                    }
                }
            } else {
                k = r;
                t = l;
                while(original.charAt(k) == find.charAt(t)){
                    x += 1;
                    k = k - 1;
                    t = t - 1;
                    if (x == find.length()) {
                        System.out.println("匹配字符串");
                        flag = true;
                        break;
                    }
                }

            }
        }
    }

    //判断字符是否在字符串中并返回第一个下标
    public int findCharin(String target, char c){
        int ans = 0;
        if((ans = target.indexOf(c)) == -1){
            return -1;
        } else {
            return ans;
        }
    }

    //判断好后缀之间最大差值
    public int findGoodTr(String find, String sub) {
        int ans = 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < sub.length(); i++) {
            res.add(sub.substring(i));
        }
        System.out.println(res);
        List<Integer> sort = new ArrayList<>();
        //匹配目标串
        for (String re : res) {
            int u = find.length();
            int max = 0;
            int begin = 0;
            int key = -1;
            while (u >= re.length()){
                String substring = find.substring(u - re.length(), u);
                if (substring.equals(re) && key == -1) {
                    begin = u;
                    max = u;
                    key = 1;
                } else if(substring.equals(re)){
                    max = u;
                    key = 2;
                }
                if(key == 2){
                    break;
                }
                u--;
            }
            sort.add((begin - max));
        }
        if ((ans = sort.stream().max((o1, o2) -> o1 - o2).get()) == 0) {
            ans = -1;
        }
        return ans;
    }
}
