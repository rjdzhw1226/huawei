package com.suanfa.leetcode;

import java.util.Arrays;

public class mergeTwoArray {
  public static void main(String[] args) {
    mergeQ(new int[]{2,1,3,0,0,0}, 3, new int[]{2,5,6},3);
  }

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int key1 = 0;
    int key2 = 0;
    int[] ints = Arrays.stream(nums1).filter(e -> e != 0).sorted().toArray();
    System.arraycopy(ints, 0, nums1, 0, ints.length);
    Arrays.sort(nums2);
    find(m + n, key1, key2, nums1, nums2);
  }

  //将2拷贝到1的尾部然后直接对1进行排序
  public static void mergeQ(int[] nums1, int m, int[] nums2, int n) {
    System.arraycopy(nums2, 0, nums1, m, nums2.length);
    Arrays.sort(nums1);
    System.out.println(Arrays.toString(nums1));
  }

  //1 2 3 0 0 0
  //      ^
  //2 5 6
  //  ^
  public static void find(int i1, int key1, int key2, int[] nums1, int[] nums2){
    int[] res = new int[i1];
    int count = 0;
    while (key1 < i1 && key2 < nums2.length){
      if(nums1[key1] == 0){
        res[count] = nums2[key2];
        key2++;
      } else {
        if (nums1[key1] < nums2[key2]){
          res[count] = nums1[key1];
          key1++;
        } else {
          res[count] = nums2[key2];
          key2++;
        }
      }
      count++;
    }
    System.out.println(Arrays.toString(res));
  }
}
