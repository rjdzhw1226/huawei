package com.suanfa.test;

import java.util.Scanner;

//分割均衡字符串
public class Test12 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] charArray = in.nextLine().toCharArray();
        String str = String.valueOf(charArray);
        int ans = 0;
        int index = 0;
        int countX = 0;
        int countY = 0;
        for (int i = 0; i < charArray.length; i++) {
            if(countX == countY && countX != 0 && countY != 0) {
                index = i;
                countX = 0;
                countY = 0;
                ans++;
            }
            if(charArray[i] == 'X') {
                countX++;
            }
            if(charArray[i] == 'Y') {
                countY++;
            }
        }
        int count1 = 0;
        int count2 = 0;
        if(index != 0) {
            for (char c : str.substring(index).toCharArray()) {
                if(c == 'X'){
                    count1++;
                }
                if(c == 'Y'){
                    count2++;
                }
            }
            if(count1 == count2) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
