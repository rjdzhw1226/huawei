package com.suanfa.leetcode;

public class isLand {
  public static void main(String[] args) {
    System.out.println(numIslands(new int[][]{
      {1, 0, 0, 0, 1},
      {1, 0, 0, 1, 1},
      {0, 0, 1, 1, 0},
      {1, 0, 1, 0, 1},
      {0, 1, 1, 0, 1}}
    ));
  }

  public static int numIslands(int[][] grid) {
    int[][] path = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    if(grid.length == 0 || grid[0].length == 0){
      return 0;
    }
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if(grid[i][j] == 1){
          dfs(grid, i, j, path);
          count++;
        }
      }
    }
    return count;
  }

  private static void dfs(int[][] grid, int i, int j, int[][] path) {
    if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1){
      return;
    }
    if(grid[i][j] != 1){
      return;
    }
    grid[i][j] = 2;
    for (int[] ints : path) {
      dfs(grid, i + ints[0], j + ints[1], path);
    }
  }
}
