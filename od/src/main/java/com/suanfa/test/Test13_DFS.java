package com.suanfa.test;

import java.util.HashSet;
import java.util.Scanner;

//小华地图寻宝_dfs
public class Test13_DFS {
    static int kg = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        System.out.println(m + " " + n + " " + k);
        int[][] dp = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
        findGolden(0, m, 0, n, k, dp);
        System.out.println(kg);
    }
    static HashSet<Integer> visited = new HashSet();

    private static void findGolden(int x, int m, int y, int n, int k, int[][] dp) {
        if(x < 0 || x >= m || y < 0 || y >= n || visited.contains(x * n + y) || manyNumSum(x) + manyNumSum(y) > k){
            return;
        }
        kg++;
        visited.add(x * n + y);
        for (int[] ints : dp) {
            x += ints[0];
            y += ints[1];
            findGolden(x, m, y, n, k, dp);
        }
    }

    private static int manyNumSum(int x){
        int sum = 0;
        while (x > 0){
            sum += x % 10;
            x = x / 10;
        }
        return sum;
    }
}
