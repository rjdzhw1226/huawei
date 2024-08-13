package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//API集群负载统计
public class Test16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        String[] str = new String[count];
        for (int i = 0; i < count; i++) {
            String s = in.next();
            str[i] = s;
        }
        int value = in.nextInt();
        String line = in.next();
        System.out.println(Arrays.toString(str));

        int time = 0;
        for (int i = 0; i < str.length; i++) {
            if(!str[i].contains("/")){
                continue;
            }
            if(!(str[i].split("/").length > value)){
                continue;
            }
            if (str[i].split("/")[value].equals(line)) {
                time++;
            }
        }
        System.out.println(time);
    }
}
