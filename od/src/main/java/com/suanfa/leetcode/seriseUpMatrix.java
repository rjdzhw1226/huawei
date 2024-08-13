package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class seriseUpMatrix {

  static List<Integer> res = new ArrayList<>();

  //严格递增的矩阵
  public int maxIncreasingCells(int[][] mat) {
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        dfs(i, j, mat, mat[i][j]);
      }
    }
    return 0;
  }

  private void dfs(int i, int j, int[][] mat, int val) {
    if(i < 0 || j < 0 || i > mat.length - 1 || j > mat[0].length - 1){
      System.out.println();
      return;
    }
    for (int v : mat[i]) {
      if(v > val){

      }
    }
  }
}
