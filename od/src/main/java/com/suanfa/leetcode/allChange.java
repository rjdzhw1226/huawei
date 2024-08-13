package com.suanfa.leetcode;

import java.util.*;
//全排列
public class allChange {
  public static void main(String[] args) {
    findAll(new int[]{3,2,1});
  }
  static List<List<Integer>> res = new ArrayList<>();
  public static List<List<Integer>> findAll(int[] nums){
    LinkedList<Integer> list = new LinkedList<>();
    for (Integer num : nums) {
      list.add(num);
    }
//    diguiFindReal(nums, list, 0);
    diguiFind(nums, list, 0);
    System.out.println(res);
    return null;
  }

  private static void diguiFindReal(int[] nums, LinkedList<Integer> list, int i){
    if(i == nums.length){
      res.add(new ArrayList<>(list));
    }
    for (int i1 = i; i1 < nums.length; i1++) {
      Collections.swap(list, i, i1);
      diguiFindReal(nums, list, i + 1);
      Collections.swap(list, i, i1);
    }
  }

  private static void diguiFind(int[] nums, LinkedList<Integer> list, int i) {
    if(i == nums.length){
      res.add(new ArrayList<>(list));
      return;
    }
    for (int num : nums) {
      list.add(num);
      diguiFind(nums, list, i + 1);
      list.removeLast();
    }
  }
}
