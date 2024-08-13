package com.suanfa.leetcode;

import java.util.*;

public class robDP {
  public static void main(String[] args) {
//    robDP ro = new robDP();
//    System.out.println(ro.rob(new int[]{3, 2, 7, 9, 1}));
    System.out.println(coinChange(new int[]{1, 2, 5}, 11));
//    System.out.println(Arrays.stream(new int[]{1, 2, 5}).filter(e -> e <= 0).max().orElse(-1));

  }
  //连续抢劫两家相邻就会报警 问抢到的最大值
  public int rob(int[] nums) {
    int[] dp = new int[nums.length + 1];
    dp[0] = 0;
    dp[1] = nums[0];
    for (int i = 2; i <= nums.length; i++) {
      dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
    }
    return dp[nums.length];
  }

  //完全平方数
  public int numSquares(int n) {
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      for(int j = 1; i - j * j >= 0; j++){
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }
    return dp[n];
  }

  public int numSquaresQ(int n){
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    queue.offer(n);
    visited.add(n);
    int count = 0;
    while (!queue.isEmpty()){
      count++;
      for (int i = 0; i < queue.size(); i++) {
        int cur = queue.poll();
        for (int j = 1; j * j <= cur; j++) {
          int temp = cur - j * j;
          if(temp == 0){
            return count;
          }
          if(!visited.contains(temp)){
            queue.offer(temp);
          }
          visited.add(temp);
        }
      }
    }
    return count;
  }

  //零钱兑换 难 动规整不明白
  static int k = 0;
  public static int coinChange(int[] coins, int amount) {
//    k = amount;
//    int i = 0;
//    int count = 0;
//    while (k > 0){
//      i = Arrays.stream(coins).filter(e -> e <= k).max().orElse(-1);
//      if(i == -1){
//        return -1;
//      }
//      k = k - i;
//      count++;
//    }
    memo = new int[amount];

    dfs(coins, amount, 0);
    if(res == Integer.MAX_VALUE){
      return -1;
    }

//    System.out.println(count);
    // 13 1 2 5
    // 5
    // 8
    // 5
    // 3
    // 2
    // 1
    return res;
  }

  static int res = Integer.MAX_VALUE;
  static int[] memo;
  private static void dfs(int[] coins, int amount, int count) {
    if(amount < 0){
      return;
    }
    if(amount == 0){
      res = Math.min(res, count);
    }
    for (int i = 0; i < coins.length; i++) {
      dfs(coins, amount - coins[i], count + 1);
    }
  }

}
