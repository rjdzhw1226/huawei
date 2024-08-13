package com.suanfa.test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

//小华地图寻宝_bfs
public class Test13_BFS {

    static int m;
    static int n;
    static int k;
    static int[][] dp = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        k = in.nextInt();
        System.out.println(m + " " + n + " " + k);

        if(m == 0 || n == 0) {
            System.out.println(0);
        } else {
            System.out.println(findGolden());
        }

    }

    static HashSet<Integer> visited = new HashSet<>();
    private static int findGolden() {
        LinkedList<Integer> queue = new LinkedList<>();
        int kg = 1;
        visited.add(0);
        queue.add(0);

        while (queue.size() != 0) {
            int cur = queue.poll();

            int x = cur / n;
            int y = cur % n;

            for (int[] ints : dp) {
                x += ints[0];
                y += ints[1];

                if(x < 0 || x >= m || y < 0 || y >= n){
                    continue;
                }
                if(manyNumSum(x) + manyNumSum(y) > k){
                    continue;
                }
                int point = x * n + y;
                if(visited.contains(point)){
                    continue;
                }
                visited.add(point);
                kg++;
                queue.addLast(point);
            }
        }
        return kg;
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
