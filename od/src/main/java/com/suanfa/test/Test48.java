package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//最长子字符串的长度
public class Test48 {
    //题目描述
    //给你一个字符串s，首尾相连成一个环形，请你在环中找出'0'字符出现了偶数次最长子字符串只的长度。
    //输入描述
    //输入是一个小写字母组成的字符串
    //输出描述
    //输出是一个整数
    //备注
    //1≤s.length≤ 500000
    //s只包含小写英文字母
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] chars = line.toCharArray();
        System.out.println(line);
        int count = 0;
        //双指针 处理环要尾指针++等于循环长度来重置尾指针位置
        int fir;
        int sec;
        for (int i = 0; i < chars.length; i++) {
            fir = i;
            if(fir == chars.length - 1){
                sec = 0;
            } else {
                sec = fir + 1;
            }
            List<Character> list = new ArrayList<>();
            while (fir != sec) {
                if (chars[sec] == 'o') {
                    count++;
                }
                list.add(chars[sec]);
                if(sec++ == chars.length - 1){
                    sec = 0;
                }
            }
            if(count % 2 == 0){
                StringBuilder sb = new StringBuilder();
                for (Character character : list) {
                    sb.append(character);
                }
                System.out.println(sb);
            }
            count = 0;
        }


    }
}
