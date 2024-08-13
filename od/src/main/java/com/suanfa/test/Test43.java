package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//字符串筛选排序
public class Test43 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int k = Integer.parseInt(in.nextLine());
        char[] charArray = line.toCharArray();
        //从小到大排序 按ascii码排序 第k个字符在原字符串中的索引
        Arrays.sort(charArray);
        if(k > line.length()){
            k = line.length();
        }
        System.out.println(line.indexOf(charArray[k - 1]));
    }

}
