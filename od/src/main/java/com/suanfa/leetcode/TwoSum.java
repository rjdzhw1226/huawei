package com.suanfa.leetcode;

import java.util.Hashtable;

//两数之和
public class TwoSum {

  public static int[] TwoSum(int target, int[] num){
    Hashtable<Integer, Integer> table = new Hashtable<>();
    int len = num.length;
    for (int i = len - 1; i >= 0; --i) {
      if(table.containsKey(num[i])){
        return new int[]{table.get(num[i]), i};
      }
      table.put(table.get(target - num[i]), i);
    }
    return null;
  }
}
