package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//全排列 todo
public class allGroup {
    static List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        permute(new int[]{3,2,1});
    }
    public static List<List<Integer>> permute(int[] nums) {
        dfsMethod(nums, 0, new LinkedList<Integer>());
        System.out.println(res);
        return new ArrayList<>();
    }

    private static void dfsMethod(int[] nums, int i, LinkedList<Integer> list) {
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
        }

        for (int j = i; j < nums.length; j++) {
            list.add(nums[j]);
            dfsMethod(nums, i + 1, list);
            list.removeLast();
        }
    }
}
