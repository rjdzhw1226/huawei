package com.suanfa.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaxNum {
    //给一个数组实现返回其中数字组成的最大数字
    public static void main(String[] args) {
//        System.out.println("9".substring(1).equals(""));
//        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(largestNumber(new int[]{111311, 1113}));
        System.out.println(largestNumber(new int[]{999999991, 9}));
        System.out.println(largestNumber(new int[]{0,0}));
    }
    public static String largestNumber(int[] nums) {
        if(nums.length == Arrays.stream(nums).filter(e -> e == 0).count()){
            return "0";
        }
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted((o1, o2) -> {
            return (o2 + o1).compareTo(o1 + o2);
        }).collect(Collectors.joining(""));
    }
}
