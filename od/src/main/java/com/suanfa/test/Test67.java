package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//用连续自然数之和来表达整数
public class Test67 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int i = Integer.parseInt(in.nextLine());

    int[] nums = init(i);
    System.out.println(Arrays.toString(nums));
    //整体逻辑 我认为两个指针 从 0 0开始计算之间的和 小于则右移右指针 等于i则保存
    // 大于i则左指针右移到小于i 右指针继续往前
    // 直到左指针右指针移动到数组尾部
  }

  private static int[] init(int i) {
    int[] n = new int[i];
    while (i > 0) {
      n[i - 1] = i;
      i--;
    }
    return n;
  }


}
