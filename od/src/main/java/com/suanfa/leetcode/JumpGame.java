package com.suanfa.leetcode;

//与跳跃游戏II的区别为 一次可以跳的值是固定的
public class JumpGame {
    //当前能到达的最远下标 不断遍历更新最大值k 达不到就是不能达到
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > k){
                return false;
            }
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

    public boolean findRightMost(int[] nums){
        int n = nums.length;
        int right = 0;
        for (int i = 0; i < n; i++) {
            if(i <= right){
                right = Math.max(right, i + nums[i]);
                if(right >= n - 1){
                    return true;
                }
            }
        }
        return false;
    }
}
