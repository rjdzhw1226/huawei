package com.suanfa.leetcode;

//给定一个整数n 返回n的阶乘尾随零的数量
public class ZeroTail {
  public static void main(String[] args) {
    ZeroTail z = new ZeroTail();
    System.out.println(z.trailingZeroes(5));
  }
  public int trailingZeroes(int n) {
    String s = String.valueOf(mul(n));
    int count = 0;
    boolean flag = true;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '0' && flag) {
        count++;
      } else {
        flag = false;
      }
    }
    return count;
  }

  public int mul(int n){
    if(n == 1){
      return 1;
    }
    return n * mul(n - 1);
  }
}
