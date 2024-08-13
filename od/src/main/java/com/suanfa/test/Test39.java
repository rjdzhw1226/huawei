package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//求字符串中所有整数的最小和
public class Test39 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] charArray = line.toCharArray();

        List<String> res = new ArrayList<>();

        boolean flag = false;
        String ans = "";
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == '-'){
                flag = true;
                if(!ans.equals("")){
                    res.add(ans);
                    ans = "";
                }
                continue;
            }
            if(charArray[i] >= '0' && charArray[i] <= '9'){
                if(flag){
                    ans += ("-" + charArray[i] + "");
                    flag = false;
                } else {
                    ans += (charArray[i] + "");
                }
            }
            if(charArray[i] < '0' || charArray[i] > '9'){
                if(!ans.equals("")){
                    res.add(ans);
                    ans = "";
                }
            }
        }
        if(!ans.equals("")){
            res.add(ans);
        }
//        System.out.println(res);

        List<Long> Minlist = new ArrayList<>();
        for (String re : res) {
            int c = 0;
            while (c < re.length()){
                if(re.charAt(c) == '-'){
                    String op = "-";
                    while ((c + 1) < re.length()){
                        char at = re.charAt(c + 1);
                        if(at >= '0' && at <= '9'){
                            op += (at + "");
                            c++;
                        } else {
                            break;
                        }
                    }
                    Minlist.add(Long.parseLong(op));
//                    Minlist.add(Integer.parseInt(op));
                    c++;
                } else {
                    Minlist.add(Long.parseLong(re.charAt(c) + ""));
//                    Minlist.add(Integer.parseInt(re.charAt(c) + ""));
                    c++;
                }
            }
        }
//        System.out.println(Minlist);
        System.out.println(Minlist.stream().mapToLong(Long::longValue).sum());

    }
}
