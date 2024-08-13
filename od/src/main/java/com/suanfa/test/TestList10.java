package com.suanfa.test;

import java.util.Arrays;

public class TestList10 {

  public boolean isPowerOfThree(int n) {
    return n > 0 && 1.162261467E9 % n == 0;
  }

  public static void heir(int[] n){
    for (int gap = n.length >> 1; gap >= 1; gap = gap >> 1) {
      for (int low = gap; low < n.length; low++) {
        int t = n[low];
        int i = low - gap;
        while (i >= 0 && t < n[i]){
          n[i + gap] = n[i];
          i-=gap;
        }
        if(i + gap != low){
          n[i + gap] = t;
        }
      }
    }
  }

  // 数组排序为 num0 < num1 > num2 < num3 > num4 < num5
  // 1 2 3 1 4 2 1 5 6 2
  // 1 1 1 2 2 2 3 4 5 6
  // 1 1 1 2 2 2 3 4 5
  // 1 2 1 3 1 4 2 5 2
  // 1 1 1 2 2
  // 2 3 4 5
  // 1 2 1 3 1 4 2 5 2 6
  // 1 1 1 2 2
  // 2 3 4 5 6
  // 1 2 1 3 2 4 2 6 5 1
  // 1 1 2 2 5
  // 2 3 4 6 1
  //
  public static void wiggleSort(int[] nums) {
    heir(nums);
    int same = (nums.length + 1) >> 1;
    int[] a = new int[nums.length - same];
    System.arraycopy(nums,same, a, 0, nums.length - same);
    int[] b = new int[same];
    System.arraycopy(nums,0, b, 0, same);
    int[] ans = new int[nums.length];
    if(nums.length % 2 == 0){
      for (int i = 0; i < nums.length; i++) {
        if(i % 2 != 0){
          ans[i] = b[i / 2];
        } else {
          ans[i] = a[i / 2];
        }
      }
    } else {
      for (int i = 0; i < nums.length; i++) {
        if(i % 2 != 0){
          ans[i] = a[i / 2];
        } else {
          ans[i] = b[i / 2];
        }
      }
    }
    System.out.println(new StringBuilder(Arrays.toString(ans).replace("[", "").replace("]", "")).reverse().toString());
  }

  // 1 2 3 4 5 6 7 8 9 10 11 12
  //
  public static void snails(int[] n, int row, int col){
    int[][] ns = new int[row][col];
    int count = 0;
    for (int i = 0; i < row * col; i+=row) {
      int[] num = new int[row];
      System.arraycopy(n,i,num,0,row);
      ns[count] = num;
      count++;
    }
    for (int[] ints : ns) {
      System.out.println(Arrays.toString(ints));
    }
  }



  public static void main(String[] args) {
//    wiggleSort(new int[]{1 ,1 ,1 ,2 ,2 ,2 ,3 ,4 ,5, 6});
    snails(new int[]{1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10 ,11 ,12, 13, 14 ,15, 16}, 4, 4);
//    int[] nums = new int[]{1 ,1 ,1 ,2 ,2 ,2 ,3 ,4 ,5};
//    int same = (nums.length + 1) >> 1;
//    int[] a = new int[nums.length - same];
//    System.arraycopy(nums,same, a, 0, nums.length - same);
//    int[] b = new int[same];
//    System.arraycopy(nums,0, b, 0, same);
//    System.out.println("a: " + Arrays.toString(a));
//    System.out.println("b: " + Arrays.toString(b));
  }
}
