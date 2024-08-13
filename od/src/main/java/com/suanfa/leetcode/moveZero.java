package com.suanfa.leetcode;

import java.util.Arrays;

public class moveZero {
  public static void main(String[] args) {
    moveZeroes(new int[]{0,1,3,0,0,2,0});
  }
  public static void moveZeroes(int[] nums) {
    int k = 0;
    int sec;
    for (int num : nums) {
      if(num == 0){
        break;
      }
      k++;
    }
    sec = k + 1;
    while (sec < nums.length){
      if(nums[sec] != 0){
        int temp = nums[sec];
        nums[sec] = nums[k];
        nums[k] = temp;
        k++;
        sec++;
      } else {
        sec++;
      }
    }
    System.out.println(Arrays.toString(nums));
  }
}
