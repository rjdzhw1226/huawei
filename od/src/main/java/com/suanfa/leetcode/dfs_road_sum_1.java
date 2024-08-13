package com.suanfa.leetcode;

import java.util.Arrays;

public class dfs_road_sum_1 {
  private int m;
  private int n;
  private int[][] memo;
  public int minPathSum(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    memo = new int[m][n];
    for (int i = 0; i < m; i++) {
      Arrays.fill(memo[i], -1);
    }
    return dfs(grid, 0, 0);
  }
  public static void main(String[] args) {
//    int[][] ints = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
    int[][] ints = new int[][]{{1,2,3},{4,5,6}};
    System.out.println(minPathSumQ(ints));
  }

  public static int minPathSumQ(int[][] grid){
    int x = grid.length;
    int y = grid[0].length;
    int[][] f = new int[x][y];
    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        if(i == 0 && j == 0){
          f[i][j] = grid[i][j];
        } else {
          int t = i - 1 >= 0 ? f[i - 1][j]  + grid[i][j] : Integer.MAX_VALUE;
          int l = j - 1 >= 0 ? f[i][j - 1]  + grid[i][j] : Integer.MAX_VALUE;
          f[i][j] = Math.min(t, l);
        }
      }
    }
    for (int[] ints : f) {
      System.out.println(Arrays.toString(ints));
    }
    return f[x - 1][y - 1];
  }

  public static int dpFind(int[][] grid){
    int[][] res = new int[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if(i == 0 && j == 0){
          res[i][j] = grid[i][j];
        } else if(i == 0){
          res[i][j] = res[i][j - 1] + grid[i][j];
        } else if(j == 0){
          res[i][j] = res[i - 1][j] + grid[i][j];
        } else {
          res[i][j] = Math.min(res[i - 1][j], res[i][j - 1]) + grid[i][j];
        }
      }

    }
    return res[grid.length - 1][grid[0].length - 1];

  }

  private int dfs(int[][] grid, int i, int j) {
    if(i < 0 || i >= m || j < 0 || j >= n){
      return Integer.MAX_VALUE;
    }
    if(memo[i][j] > -1){
      return memo[i][j];
    }
    if(i == m - 1 && j == n - 1){
      return grid[m - 1][n - 1];
    }
    int right = dfs(grid, i, j + 1);
    int left = dfs(grid, i + 1, j);
    int min = Math.min(right, left) + grid[i][j];
    memo[i][j] = min;
    return min;
  }
}
