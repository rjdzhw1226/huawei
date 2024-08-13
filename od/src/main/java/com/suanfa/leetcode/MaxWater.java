package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxWater {
  public static void main(String[] args) {
    System.out.println(maxArea(new int[]{1, 2, 3, 4, 0, 9, 2, 1, 3}));
  }
  public static int maxArea(int[] height) {
    int k = 0;
    int e = 1;
    List<Integer> ans = new ArrayList<>();
    while (k < height.length - 1) {
      while (e < height.length){
        int i = (Math.min(height[e], height[k])) * (e - k);
        ans.add(i);
        e++;
      }
      k++;
      e = k + 1;
    }
//    System.out.println(ans);
    return ans.stream().max((o1, o2) -> o1 - o2).orElse(0);
  }
}
