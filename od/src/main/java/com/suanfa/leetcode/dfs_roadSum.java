package com.suanfa.leetcode;
//最小路径和 todo 求和的地方应该有问题 再改改
public class dfs_roadSum {
  public static void main(String[] args) {
//    int[][] ints = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
    int[][] ints = new int[][]{{1,2,3},{4,5,6}};
    System.out.println(minPathSum(ints));
  }
  static int min = Integer.MAX_VALUE;

  public static int minPathSum(int[][] grid) {
    int[][] path = new int[][]{{0, 1},{1, 0}};
    int sum = grid[0][0];
    findPath(grid, path, sum, 0, 0);
    return min;
  }

  private static void findPath(int[][] grid, int[][] path, int sum, int row, int col) {
   if(col == grid[0].length - 1 && row == grid.length - 1) {
     System.out.println("行：" + row + " 列：" + col + "总和：" + sum);
     if(sum < min){
       min = sum;
     }
     return;
   }
   if(col == grid[0].length - 1){
     findPath(grid, path, sum + grid[row][col], row + path[1][0], col + path[1][1]);
   } else {
     findPath(grid, path, sum + grid[row][col], row + path[0][0], col + path[0][1]);
   }
   if(row == grid.length - 1){
     findPath(grid, path, sum + grid[row][col], row + path[0][0], col + path[0][1]);
   } else {
     findPath(grid, path, sum + grid[row][col], row + path[1][0], col + path[1][1]);
   }

//    findPath(grid, path, sum + grid[row][col], row + path[1][0], col + path[1][1]);
//    findPath(grid, path, sum + grid[row][col], row + path[0][0], col + path[0][1]);
  }
}
