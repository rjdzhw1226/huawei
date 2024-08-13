package com.suanfa.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class findNextGroup {
  public static void main(String[] args) {
    nextPermutation(new int[]{1,2,3});
    System.out.println(list);
  }
  static LinkedList<String> list = new LinkedList<>();

  public static void nextPermutation(int[] nums) {
    LinkedList<Integer> linkedList = new LinkedList<>();
    for (int num : nums) {
      linkedList.add(num);
    }
    dfs(nums, 0, linkedList);
  }

  private static void dfs(int[] nums, int i, LinkedList<Integer> s) {
    if(i == nums.length){
      list.add(s.stream().map(String::valueOf).collect(Collectors.joining("")));
      return;
    }
    for (int j = i; j < nums.length; j++) {
      Collections.swap(s, i, j);
//      s.add(nums[j]);
      dfs(nums, i + 1, s);
      Collections.swap(s, j, i);
//      s.removeLast();
    }
  }
}
