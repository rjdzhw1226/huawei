package com.suanfa.leetcode;

import java.sql.SQLOutput;
import java.util.*;

//删除子数组的最大得分
public class removeChildMaxNum {
  public static void main(String[] args) {
    maximumUniqueSubarray(new int[]{4,2,4,5,6});
  }

  //给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
  //
  //返回 只删除一个 子数组可获得的 最大得分
  public static int maximumUniqueSubarray(int[] nums) {
    Hashtable<Integer, Integer> hashtable = new Hashtable<>();
    int b = 0;
    int e = 0;
    int sum = 0;
    int max = 0;
    while (b < nums.length && e < nums.length) {
      if(!hashtable.containsKey(nums[e])){
        hashtable.put(nums[e], 1);
        sum += nums[e];
        max = Math.max(sum, max);
      } else {
        while (nums[b] != nums[e]){
          hashtable.remove(nums[b], 1);
          sum -= nums[b];
          b++;
        }
        b++;
      }
      e++;
    }
    return max;
  }

  public int maximumUniqueSubarrayQ(int[] nums) {
    Set<Integer> set = new HashSet<>();
    int max = 0, sum = 0, start = 0;
    for (int i = 0; i < nums.length; i++) {
      if (!set.contains(nums[i])) {
        set.add(nums[i]);
        sum += nums[i];
        max = Math.max(sum, max);
      } else {
        while (nums[i] != nums[start]) {
          sum -= nums[start];
          set.remove(nums[start]);
          start++;
        }
        start++;
      }
    }
    return max;
  }

}
