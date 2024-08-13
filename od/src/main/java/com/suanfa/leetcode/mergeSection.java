package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class mergeSection {
    //合并区间
    public static void main(String[] args) {
        int[][] merge = merge(new int[][]{{1, 3}, {8, 10}, {2, 6}, {15, 18}, {0, 4}});
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }
    public static int[][] merge(int[][] intervals) {
        int[][] res = new int[intervals.length][2];
        //排序
        List<int[]> collect = Arrays.stream(intervals).sorted((o1, o2) -> o1[0] - o2[0]).collect(Collectors.toList());
        int idx = -1;
        for (int[] ints : collect) {
            //第一个或者不重合 放入
            if(idx == -1 || ints[0] > res[idx][1]){
                res[++idx] = ints;
            //合并
            } else {
                res[idx][1] = Math.max(res[idx][1], ints[1]);
            }
        }
        return res;
    }

    public static boolean checkSection(int[][] intervals){
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < intervals[i - 1][1]){
                return false;
            }
        }
        return true;
    }
}
