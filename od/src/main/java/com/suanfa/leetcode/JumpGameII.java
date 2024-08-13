package com.suanfa.leetcode;

public class JumpGameII {
  //每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，
  // 你可以跳转到任意 nums[i + j] 处:
  public int jump(int[] nums) {
    int k = 0;
    int i = 0;
    int step = 0;
    for (int j = 0; j < nums.length - 1; j++) {
      i = Math.max(i, j + nums[j]);
      if(j == k){
        k = i;
        step++;
      }
    }
    return step;
  }
}
