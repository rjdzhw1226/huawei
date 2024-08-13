package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Test07 {
    //题目描述
    //寿司店周年庆，正在举办 优惠活动回馈新老客户，寿司转盘上总共有n盘寿司，
    // prices[i]是第i盘寿司的价格如果客户选择了第i盘寿司，
    // 寿词店免费赠送客户距离第i盘寿司最近的下一盘寿司j，前提是 prices[j] < prices[i]，如果没有满足条件的，则不赠送寿司。
    //每个价格的寿司都可无限供应。
    //输入描述
    //输入的每一个数字代表每盘寿司的价格，每盘寿司的价格之间使用空格分隔，例如:
    //3 15 6 14
    //表示:
    //·第0盘寿司价格 prices[0]为 3
    //·第1盘寿司价格 prices[1]为 15
    //·第2盘寿可价格 prices[2]为6
    //·第3盘寿司价格 prices[3]为 14
    //寿司的盘数n范围为:1≤n≤500
    //每盘寿司的价格 price 范围为:1≤price≤1000
    //输出描述
    //输出享受优惠后的一组数据，每个值表示客户选择第i盘寿司时实际得到的寿司的总价格。使用空格进行分隔，例如:
    //3 21 8 17
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        stackFollow(Arrays.stream(split).mapToInt(Integer::parseInt).toArray());
        //3 15 6 14
        //3 15
        //3 6
        //3 6 14

    }

    //单调栈
    public static void stackFollow(int[] nums){
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if(stack.isEmpty()){
                stack.push(nums[i]);
            } else {
                while (true){
                    if(stack.isEmpty()){
                        break;
                    }
                    if(nums[i] > stack.peek()){
                        stack.push(nums[i]);
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }
        System.out.println(stack);
    }
}
