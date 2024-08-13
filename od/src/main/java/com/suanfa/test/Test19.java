package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//小明的幸运数
public class Test19 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        int count = in.nextInt();
        if (count < 1 || count > 100){
            System.out.println(12345);
            return;
        }
        int luck = in.nextInt();
        if (luck < -100 || luck > 100){
            System.out.println(12345);
            return;
        }
        int[] ints = new int[count + 1];
        int begin = 0;
        ints[0] = begin;
        for (int i = 1; i <= count; i++) {
            int command = in.nextInt();
            if(command < -100 || command > 100){
                System.out.println(12345);
                flag = false;
                break;
            }
            if(command == luck){
                if(luck == 0){
                    begin += command;
                } else {
                    begin += (command + 1);
                }

            } else {
                begin += command;
            }
            ints[i] = begin;
        }
        if(flag){
            System.out.println(Arrays.stream(ints).max().orElse(0));
        }
    }
}
