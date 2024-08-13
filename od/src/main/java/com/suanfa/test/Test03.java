package com.suanfa.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test03 {

    //字符串组合插空
    public static void main (String[] args) {
//        numDecoding("1234");
        spankSort("1234");
    }

    //1 234
    //2 34 /23 4 /2 3 4
    //3 4
    public static void numDecoding (String s) {
        int i = 1;
        if (s.length() > 1) {
            while (i < s.length()) {
                String fir = s.substring(0, i);
                String sub = s.substring(i);
                System.out.println(fir + " " + sub);
                numDecoding(fir);
                numDecoding(sub);
                i++;
            }
        }
    }

    //12 3 4
    public static void spankSort(String s){
       List<String> list = new ArrayList<>();
       spank(s, 0, "", list);
        HashSet<String> set = new HashSet<>(list);
        List<String> result = new ArrayList<>(set);
       System.out.println(result);
    }

    private static void spank(String s, int i, String s1, List<String> list) {
        if(i == s.length()){
            list.add(s1.trim());
            return;
        }
        spank(s, i + 1, s1 + s.charAt(i), list);
        spank(s, i + 1, s1 + " " + s.charAt(i), list);
    }


}
