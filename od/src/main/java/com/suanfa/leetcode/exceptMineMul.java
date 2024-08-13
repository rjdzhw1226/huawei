package com.suanfa.leetcode;

import java.util.Arrays;

public class exceptMineMul {
  public static void main(String[] args) {
//    productExceptSelf(new int[]{-1,1,0,-3,3});
    productExceptSelf(new int[]{1,2,3,9,-2});
  }

  public static int[] productExceptSelf(int[] nums) {
    int[] pre = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      int sum = 1;
      for (int i1 : Arrays.stream(nums).filter(e -> e != n).toArray()) {
        sum *= i1;
      }
      pre[i] = sum;
    }
    System.out.println(Arrays.toString(pre));
    return nums;
  }


  public int[] productExceptSelfQ(int[] nums) {
    int len = nums.length;
    if (len == 0) return new int[0];
    int[] ans = new int[len];
    ans[0] = 1;
    int tmp = 1;
    for (int i = 1; i < len; i++) {
      ans[i] = ans[i - 1] * nums[i - 1];
    }
    for (int i = len - 2; i >= 0; i--) {
      tmp *= nums[i + 1];
      ans[i] *= tmp;
    }
    return ans;
  }
}
