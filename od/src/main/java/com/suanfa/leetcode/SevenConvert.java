package com.suanfa.leetcode;

public class SevenConvert {
  public static void main(String[] args) {
    System.out.println(convertToBase7(100));
  }

  public static String convertToBase7(int num) {
    String s = "";
    while (num != 0){
      s += (num % 7);
      num = num / 7;
    }
    return s;
  }
}
