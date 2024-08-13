package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class colorSort {
  public static void main(String[] args) {
    sortColors(new int[]{2,0,2,1,1,0,2});
  }

  public static void sortColors(int[] nums) {
    int[] res = new int[nums.length];
//    String s2 = checkNum(nums, 2);
//    String s = "[" + checkNum(nums, 0) + checkNum(nums, 1) + s2.substring(0, s2.length() - 2) +"]";
//    System.out.println(s);
//    int[] ints0 = checkNum(nums, 0);
//    int[] ints1 = checkNum(nums, 1);
//    int[] ints2 = checkNum(nums, 2);
    int[] ints0 = Arrays.stream(nums).filter(e -> e == 0).toArray();
    int[] ints1 = Arrays.stream(nums).filter(e -> e == 1).toArray();
    int[] ints2 = Arrays.stream(nums).filter(e -> e == 2).toArray();
//    System.arraycopy(ints0, 0, res, 0, ints0.length);
//    System.arraycopy(ints1, 0, res, ints0.length, ints1.length);
//    System.arraycopy(ints2, 0, res, ints0.length + ints1.length, ints2.length);
    int[] ints = IntStream.concat(IntStream.concat(Arrays.stream(nums).filter(e -> e == 0), Arrays.stream(nums).filter(e -> e == 1)), Arrays.stream(nums).filter(e -> e == 2)).toArray();
    for (int i = 0; i < nums.length; i++) {
      nums[i] = ints[i];
    }
    //    System.out.println(Arrays.toString(res));
    //[0, 0, 1, 1, 2, 2]
  }

  //1不动 2往后换 0往前换
  public static void sortColorsQ(int[] nums){
    int lef = 0;
    int right = nums.length - 1;
    int cur = 0;
    while (cur <= right){
      if(nums[cur] == 0){
        swap(nums, lef++, cur++);
      } else if(nums[cur] == 1){
        cur++;
      } else if(nums[cur] == 2){
        swap(nums, cur, right--);
      }
    }
  }

  public static void swap(int[] nums, int l, int r) {
    int temp = nums[l];
    nums[l] = nums[r];
    nums[r] = temp;
  }

  private static String checkNum(int[] nums, int i) {
    String s = "";
    for (int j = 0; j < nums.length; j++) {
      if(nums[j] == i){
        s += i + ", ";
      }
    }
    return s;
  }
}
