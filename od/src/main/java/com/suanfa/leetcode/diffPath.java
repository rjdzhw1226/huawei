package com.suanfa.leetcode;

//不同路径 只能往下或者往右走
public class diffPath {

  public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      dp[i][0] = 1;
    }
    for (int i = 0; i < n; i++) {
      dp[0][i] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    return dp[m - 1][n - 1];
  }

  //超时用一个数组保存起来然后做状态判断
  public int uniquePathsQ(int m, int n){
    return dfs(m - 1, n - 1);
  }

  private int dfs(int i, int i1) {
    if(i <= 0 || i1 <= 0){
      return 1;
    }
    return dfs(i, i1 - 1) + dfs(i - 1 , i1);
  }
}
