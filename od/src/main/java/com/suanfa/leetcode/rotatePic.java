package com.suanfa.leetcode;

import java.util.Arrays;

public class rotatePic {
  public static void main(String[] args) {
    rotate(new int[][]{
      {1,2,3},
      {3,4,5},
//      {8,0,1},
      {6,7,9}
    });
  }
  //给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
  //你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
  public static void rotate(int[][] matrix) {
    swaps(matrix);
    for (int i = 0; i < matrix.length; i++) {
      for (int j = i; j < matrix.length; j++) {
        int tem = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tem;
      }
    }
    for (int[] ints : matrix) {
      System.out.println(Arrays.toString(ints));
    }
  }

  public static void swaps(int[][] grid){
    int n = grid.length - 1;
    for (int i = 0; i <= grid.length / 2; i++) {
      int[] temp = grid[i];
      grid[i] = grid[n - i];
      grid[n - i] = temp;
    }
  }
}
