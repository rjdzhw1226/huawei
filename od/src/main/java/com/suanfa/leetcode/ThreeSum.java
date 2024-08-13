package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//三数之和
public class ThreeSum {
  public static void main(String[] args) {
    System.out.println(threeSum(new int[]{-4, -1, 0, -1, -1, 2, 1}));
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> arrayList = new ArrayList<>();
    if(nums.length < 3){
      return arrayList;
    }
    if(nums.length == 3){
      if(Arrays.stream(nums).sum() == 0){
        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        arrayList.add(collect);
      }
      return arrayList;
    }
    int[] sort = sortMine(nums);
    for (int i = 0; i < sort.length - 2; i++) {
      int fir = i + 1;
      int las = sort.length - 1;
      while (las > fir){
        //根据大小判断两个指针谁移动 todo
        if(sort[i] + sort[fir] + sort[las] == 0){
          List<Integer> ans = new ArrayList<>();
          ans.add(sort[i]);
          ans.add(sort[fir]);
          ans.add(sort[las]);
          arrayList.add(ans);
        }
        fir++;
        las--;
      }
    }
    return arrayList;
  }

  public static int[] sort(int[] ints){
    int[] copy = Arrays.copyOf(ints, ints.length);
    for (int i = 0; i < ints.length - 1; i++) {
      for (int j = 0; j < ints.length - 1 - i; j++) {
        if(copy[j] >= copy[j + 1]){
          int temp = copy[j];
          copy[j] = copy[j + 1];
          copy[j + 1] = temp;
        }
      }
    }
    return copy;
  }

  public static int[] sortMine(int[] ints){
    for (int i = 0; i < ints.length; i++) {
      for (int j = i; j < ints.length; j++) {
        if(ints[i] > ints[j]){
          int temp = ints[j];
          ints[j] = ints[i];
          ints[i] = temp;
        }
      }
    }
    return ints;
  }

}
