package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//掌握的单词个数
public class Test10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            strings[i] = s;
        }
        // ?的个数
        char[] charArray = in.nextLine().toCharArray();
        int count = 0;
        for (char c : charArray) {
            if(c == '?') {
                count++;
            }
        }
        List<String> ans = new ArrayList<>();

        for (String string : strings) {
            int len = string.length();
            for (char c : charArray) {
                if(len == 0){
                    ans.add(string);
                    break;
                }
                if(string.contains(String.valueOf(c)) && c != '?'){
                    len--;
                }
            }
            if(len <= count && len != 0){
                ans.add(string);
            }
        }
        System.out.println(ans);
    }
}
