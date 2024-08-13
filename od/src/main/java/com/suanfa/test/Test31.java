package com.suanfa.test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

//小朋友来自多少小区
public class Test31 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int[] array = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        Hashtable<Integer, Integer> hash = new Hashtable<>();
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if(hash.get(array[i]) == null){
                hash.put(array[i], array[i] + 1);
                sum += (array[i] + 1);
            } else {
                if(hash.get(array[i]) == 0){
                    hash.remove(array[i]);
                } else {
                    Integer integer = hash.get(array[i]);
                    integer = integer - 1;
                    hash.put(array[i], integer);
                }
            }
        }
        System.out.println(sum);
    }
}
