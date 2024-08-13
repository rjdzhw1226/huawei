package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//攀登者1
public class Test24 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if(i == 0) {
                if(array[i] > array[i + 1]){
                    count++;
                }
            } else if(i == array.length - 1){
                if(array[i] > array[i - 1]){
                    count++;
                }
            } else {
                if(array[i] > array[i + 1] && array[i] > array[i - 1]) {
                    count++;
                }
            }
        }
        System.out.println(count);

    }
}
