package com.suanfa.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//密码解密
public class Test26 {
    static String[] str = new String[]{
            "1","2","3","4","5","6","7","8","9","10\\*",
            "11\\*","12\\*","13\\*","14\\*","15\\*","16\\*","17\\*",
            "18\\*","19\\*","20\\*","21\\*","22\\*","23\\*","24\\*",
            "25\\*","26\\*"
    };

    static String[] strFind = new String[]{
            "1","2","3","4","5","6","7","8","9","10*",
            "11*","12*","13*","14*","15*","16*","17*",
            "18*","19*","20*","21*","22*","23*","24*",
            "25*","26*"
    };
    static Map<String, String> map = new HashMap<>();

    public static void init(){
        char count = 'a';
        for (String s : str) {
            map.put(s, count + "");
            count++;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        init();
        for (int i = str.length - 1; i >= 0; i--) {
            if(line.contains(strFind[i])){
                line = line.replaceAll(str[i], map.get(str[i]));
            }
        }
//        System.out.println(line.contains("19*"));
//        System.out.println(line.replaceAll("19\\*", "s"));
        System.out.println(line);
    }
}
