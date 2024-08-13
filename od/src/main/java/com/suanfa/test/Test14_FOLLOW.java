package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//数的分解
public class Test14_FOLLOW {
    static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
//        for (int j = 1; j <= i / 2; j++) {
//            int sum = j;
//            int current = j + 1;
//            List<Integer> seq = new ArrayList<>();
//            seq.add(j);
//
//            while (sum < i) {
//                seq.add(current);
//                sum += current;
//                current++;
//            }
//            if(sum == i){
//                result.add(new ArrayList<>(seq));
//            }
//        }
//        System.out.println(result);
        desposeInteger(i);
    }

    private static void desposeInteger(int i) {
        int k = 1;
        int end = 1;
        int sum = 1;
        int min = 1;
        int[] nums = new int[2];
        while (k <= i / 2){
            if(sum < i) {
                end++;
                sum += end;
            } else if(sum > i) {
                sum -= k;
                k++;
            } else {
                if (min == end - k) {
                    nums[0] = k;
                    nums[1] = end;
                }
                sum -= k;
                k++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i + "=");

        if (nums[0] == 0) {
            System.out.println("N");
            return;
        }

        for (int j = nums[0]; j <= nums[1]; j++) {
            stringBuilder.append(j + "+");
        }

        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));

    }


}
