package com.suanfa.test;

import java.util.Scanner;

public class Test70 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int i = in.nextInt();
    int result = getResult(i);
    System.out.println(result);
  }

  //猴子爬山 （爬楼梯）
  public static int getResult(int n) {
    int[] dp = new int[n + 1];

    if (n >= 1) {
      dp[1] = 1;
    }
    if (n >= 2) {
      dp[2] = 1;
    }
    if (n >= 3) {
      dp[3] = 2;
    }

    for (int i = 4; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 3];
    }

    return dp[n];
  }
}
