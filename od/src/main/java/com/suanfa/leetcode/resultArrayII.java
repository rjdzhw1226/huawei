package com.suanfa.leetcode;

import java.util.*;

//将元素分配到两个数组中
public class resultArrayII {
  //树状数组
  static class ArrayTree {
    int[] tree;

    public ArrayTree() {
    }

    public ArrayTree(int n){
      tree = new int[n + 1];
    }

    public void add(int i){
      while (i < tree.length){
        tree[i]++;
        i += i & -i;
      }
    }

    public int get(int i){
      int sum = 0;
      while (i > 0){
        sum += tree[i];
        i -= i & -i;
      }
      return sum;
    }

    public int search(int lef, int rig){
      return get(rig) - get(lef - 1);
    }

  }

  public int[] resultArray(int[] nums) {
    int n = nums.length;
    int[] sortedNums = Arrays.copyOf(nums, n);
    Arrays.sort(sortedNums);

    Map<Integer, Integer> index = new HashMap<>();
    for (int i = 0; i < n; i++) {
      index.put(sortedNums[i], i + 1);
    }

    List<Integer> arr1 = new ArrayList<>();
    arr1.add(nums[0]);
    List<Integer> arr2 = new ArrayList<>();
    arr2.add(nums[1]);
    ArrayTree arrayTree1 = new ArrayTree(n);
    ArrayTree arrayTree2 = new ArrayTree(n);
    arrayTree1.add(index.get(nums[0]));
    arrayTree2.add(index.get(nums[1]));

    for (int i = 2; i < n; i++) {
      int count1 = arr1.size() - arrayTree1.get(index.get(nums[i]));
      int count2 = arr2.size() - arrayTree2.get(index.get(nums[i]));
      if (count1 > count2 || (count1 == count2 && arr1.size() <= arr2.size())) {
        arr1.add(nums[i]);
        arrayTree1.add(index.get(nums[i]));
      } else {
        arr2.add(nums[i]);
        arrayTree2.add(index.get(nums[i]));
      }
    }
    int i = 0;
    for (Integer a : arr1) {
      nums[i++] = a;
    }
    for (Integer a : arr2) {
      nums[i++] = a;
    }
    return nums;
  }
}
