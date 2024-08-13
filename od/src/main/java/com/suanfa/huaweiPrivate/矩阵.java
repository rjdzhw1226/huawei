package com.suanfa.huaweiPrivate;

import java.util.*;

public class 矩阵 {
    static int[][] path = new int[][]{{1, 0}, {0, 1}};
    static List<List<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = array[0];
        int col = array[1];
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            matrix[i] = Arrays.copyOf(Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(), col);
        }
        List<Integer> sum = new LinkedList<>();
        dfs(0, 0, row, col, matrix, sum);
//        System.out.println(dfsR(0, 0, row, col, matrix, 0));
    }

    private static void dfs(int i, int j, int row, int col, int[][] matrix, List<Integer> sum) {
        if (i == row - 1 && j == col - 1) {
            System.out.println(sum);
            return;
        }
        for (int[] ints : path) {
            int x = ints[0] + i;
            int y = ints[1] + j;
            if (x < row && y < col) {
                sum.add(matrix[x][y]);
                dfs(x, y, row, col, matrix, sum);
                sum.remove(sum.size() - 1);
            }
        }
    }

    private static int dfsR(int i, int j, int row, int col, int[][] matrix, int sum) {
        if (i > row - 1 || j > col - 1) {
            return sum;
        }
        return Math.max(dfsR(i + 1, j, row, col, matrix, sum + matrix[i][j]), dfsR(i, j + 1, row, col, matrix, sum + matrix[i][j]));
    }
}
