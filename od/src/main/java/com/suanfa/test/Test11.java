package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
//小明找位置 二分
public class Test11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] ints = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int num = in.nextInt();
//        Arrays.binarySearch(ints, num); 找不到的话 返回的-low-1 就是目标位置 找到直接就是目标位置

        int max = ints.length - 1;
        int min = 0;
        int ans = 0;
        while (min < max) {
            int mid = (max + min) >> 1;
            if(max - min == 1) {
                ans = min;
                break;
            }
            if(ints[mid] > num) {
                max = mid;
            }
            if(ints[mid] < num) {
                min = mid;
            }
        }
        System.out.println(ans + 2);

    }
}
