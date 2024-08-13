package com.suanfa.gpthelp;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int x = sc.nextInt();

    int[] arr = new int[n];
    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
    System.out.println(getResult(n, x, arr));
  }

  public static long getResult(int n, int x, int[] arr) {
    int[] preSum = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      preSum[i] = preSum[i - 1] + arr[i - 1];
    }

    int l = 0;
    int r = 1;
    long ans = 0;

    while (r <= n) {
      if (preSum[r] - preSum[l] >= x) {
        ans += n - r + 1;
        l++;
        r = l + 1;
      } else {
        r++;
      }
    }

    return ans;
  }
}
