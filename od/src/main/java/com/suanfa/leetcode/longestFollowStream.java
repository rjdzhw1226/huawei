package com.suanfa.leetcode;

import java.util.HashSet;
import java.util.Set;

public class longestFollowStream {

  //字符串中最长的连续字符1，2，3，4，5
  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    int longest = 0;
    for (int num : nums) {
      if(!set.contains(num - 1)){
        int cur = num;
        int curStack = 1;
        while (set.contains(cur + 1)){
          cur += 1;
          curStack += 1;
        }
        longest = Math.max(longest, curStack);
      }
    }
    return longest;
  }
}
