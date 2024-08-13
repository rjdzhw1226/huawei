package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        String s = null;
        System.out.println(s.substring(0));
    }
    static class node {
        node parent;
        String name;
        int val;
        List<node> child;

        public node() {
        }

        public node(String name, int val){
            this.name = name;
            this.val = val;
        }

        public void add(node cur){
            this.child.add(cur);
        }

        public void changeParent(node next){
            this.parent = next;
        }

        @Override
        public String toString() {
            return "node{" +
                    "parent=" + parent +
                    ", name='" + name + '\'' +
                    ", val=" + val +
                    ", child=" + child +
                    '}';
        }
    }

    //ipad笔试题
    public static void findSimilar(int[][] nums){
        List<Integer> ans = new ArrayList<>();
        boolean flag = false;
        node nodes = new node();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if(nums[i][j] != 0){
                    //如果遍历到一个值不为零

                    flag = true;
                }
            }
        }
    }
}
