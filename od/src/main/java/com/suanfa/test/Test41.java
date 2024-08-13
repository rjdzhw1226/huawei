package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//字符串分割（二）
public class Test41 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = Integer.parseInt(in.nextLine());
        String str = in.nextLine();

        List<String> list = new ArrayList<>();
        String[] split = str.split("-");
        String s = "";
        String rest = "";
        int sum = len;
        list.add(split[0]);
        for (int i = 1; i < split.length; i++) {
            if(rest.length() != 0){
                s += rest;
                rest = "";
            }
            if (sum > split[i].length()) {
                sum -= split[i].length();
                s += split[i];
            } else if(sum < split[i].length()){
                s += split[i].substring(0, sum);
                rest += split[i].substring(sum);
                sum = len;
            } else {
                s += split[i];
                sum = len;
            }
            if(s.length() == len){
                list.add(s);
                s = "";
            }
        }
        if(s.length() != 0){
            list.add(s);
        }
        else if(rest.length() != 0){
            list.add(rest);
        }
        List<String> collect = list.stream().map(e -> convert(e)).collect(Collectors.toList());
        String s1 = "";
        for (String line : collect) {
            s1 += line + "-";
        }
        System.out.println(s1.substring(0, s1.length() - 1));

    }

    public static String convert(String str) {
        int lowerCount = 0;
        int upperCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') lowerCount++;
            else if (c >= 'A' && c <= 'Z') upperCount++;
        }

        if (lowerCount > upperCount) return str.toLowerCase();
        else if (lowerCount < upperCount) return str.toUpperCase();
        else return str;
    }
}
