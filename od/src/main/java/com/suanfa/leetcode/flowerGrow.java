package com.suanfa.leetcode;

import java.util.Arrays;
import java.util.Scanner;

public class flowerGrow {
  public static void main(String[] args) {
    System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 0, 1}, 1));
  }
  //数组1代表种了花 0代表没种花 两朵花不能相邻 n为要种的棵数 返回能不能种下
  public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    int[] copy = Arrays.copyOf(flowerbed, flowerbed.length);
    for (int i = 0; i < copy.length; i++) {
      if(i == 0){
        if(copy[i] == 0 && copy[i + 1] == 0){
          copy[i] = 1;
          n--;
        }
      } else if(i == copy.length - 1){
        if(copy[i] == 0 && copy[i - 1] == 0){
          copy[i] = 1;
          n--;
        }
      } else {
        if(copy[i] == 0 && copy[i + 1] == 0 && copy[i - 1] == 0){
          copy[i] = 1;
          n--;
        }
      }
    }
    if(n == 0 || n < 0){
      return true;
    } else {
      return false;
    }
  }
}
