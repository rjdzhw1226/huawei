package com.suanfa.leetcode;

import java.util.Arrays;
//两个字符串 第一个要变成第二个的最少步数 只能插入 删除 修改
public class changeWordsMin {
  public static void main(String[] args) {
    System.out.println(minDistanceUp("house", "rose"));
  }

  public int minDistanceDown(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();
    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 0; i < len1 + 1; i++) {
      for (int j = 0; j < len2 + 1; j++) {
        if(i == 0 && j == 0){
          dp[i][j] = 0;
        } else if(i == 0){
          dp[i][j] = j;
        } else if(j == 0){
          dp[i][j] = i;
        } else {
          int re = dp[i][j - 1] + 1;
          int is = dp[i - 1][j] + 1;
          int up = 0;
          if(word1.charAt(i - 1) == word2.charAt(j - 1)){
            up = dp[i - 1][j - 1];
          } else {
            up = dp[i - 1][j - 1] + 1;
          }
          dp[i][j] = Math.min(Math.min(re, is), up);
        }
      }
    }
    return dp[len1][len2];
  }

  public static int minDistanceUp(String word1, String word2){
    int len1 = word1.length();
    int len2 = word2.length();
    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 1; i < (len2 + 1); i++) {
      dp[0][i] = dp[0][i - 1] + 1;
    }
    for (int i = 1; i < (len1 + 1); i++) {
      dp[i][0] = dp[i - 1][0] + 1;
    }
    for (int i = 1; i < len1 + 1; i++) {
      for (int j = 1; j < len2 + 1; j++) {
        if(word1.charAt(i - 1) == word2.charAt(j - 1)){
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
        }
      }
    }
    return dp[len1][len2];
  }
}
