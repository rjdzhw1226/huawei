package com.suanfa.leetcode;

import java.util.Arrays;

public class shakeSort {
  public static void main(String[] args) {
    shakeSort sk = new shakeSort();
    int[] ints = {1, 2, 3, 4, 5, 6};
    sk.wiggleSort(ints);
    System.out.println(Arrays.toString(ints));
  }
  //摆动排序
  public void wiggleSort(int[] nums) {
    Arrays.sort(nums);

    // 1 < 3 > 2 < 4 > 5
    // 1 < 2 > 3 < 4
    // 1 < 3 > 2 < 4
    for (int i = 0, len = nums.length; i < len; i++) {

      if(i == 0){
        continue;
      } else if(i == nums.length - 1) {
        continue;
      } else {
        if(i % 2 != 0){
          if(!(nums[i] >= nums[i + 1] && nums[i] >= nums[i - 1])){
            swap(nums, i, i + 1);
          }
        }
      }
    }

  }

  public int[] swap(int[] nums, int i, int j){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    return nums;
  }
}
