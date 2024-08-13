package com.suanfa.leetcode;

import java.util.Arrays;

public class MaxPlusSign {
  public static void main(String[] args) {
    MaxPlusSign ms = new MaxPlusSign();
    System.out.println(ms.orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
  }

  public int orderOfLargestPlusSign(int n, int[][] mines) {
    int[][] path = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int[][] value = init(mines, n);
    int max = 0;
    int x = 0, y = 0, z = 0, r = 0;
    for (int i = 0; i < value.length; i++) {
      for (int j = 0; j < value[0].length; j++) {
        if(!(i == 0 || i == (n - 1) || j == 0 || j == (n - 1))){
          if(value[i][j] == 1){
            int tx = i;
            int ty = j;
            while (i > 0){
              if (value[i + path[1][0]][j] == 1) {
                x++;
              }
              i = i + path[1][0];
            }
            i = tx;
            while (j > 0){
              if(value[i][j + path[3][1]] == 1){
                y++;
              }
              j = j + path[3][1];
            }
            j = ty;
            while (i < n - 1){
              if(value[i + path[0][0]][j] == 1){
                z++;
              }
              i = i + path[0][0];
            }
            i = tx;
            while (j < n - 1){
              if(value[i][j + path[2][1]] == 1){
                r++;
              }
              j = j + path[2][1];
            }
            max = Math.max(max, Math.min(Math.min(Math.min(x, y), z), r));
            x = 0; y = 0; z = 0; r = 0;
          }
        }
      }
    }
    return max;
  }

  private int[][] init(int[][] mines, int n) {
    int[][] v = new int[n][n];
    for (int[] ints : v) {
      Arrays.fill(ints, 1);
    }
    for (int[] mine : mines) {
      v[mine[0]][mine[1]] = 0;
    }
    return v;
  }
}
