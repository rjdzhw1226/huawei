package com.suanfa.test;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//悄悄话
public class Test20 {
    static class Tree {
        int val;
        Tree lef;
        Tree rig;

        public Tree() {

        }

        public Tree(int val) {
            this.val = val;
        }

        public Tree(int val, Tree lef, Tree rig) {
            this.val = val;
            this.lef = lef;
            this.rig = rig;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("val", val)
                    .append("lef", lef)
                    .append("rig", rig)
                    .toString();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Tree tree = initTree(array, 1);
//        System.out.println(tree);
        findMaxTime(tree, 0);
        System.out.println(ans.stream().max((o1, o2) -> o1 - o2).orElse(0));

    }

    static List<Integer> ans = new ArrayList<>();
    private static void findMaxTime(Tree tree, int val) {
        if(tree == null){
            ans.add(val);
            return;
        }
        tree.val = tree.val + val;
        findMaxTime(tree.lef, tree.val);
        findMaxTime(tree.rig, tree.val);
    }

    private static Tree initTree(int[] array, int i) {
       if(i > array.length){
           return null;
       }
       if(array[i - 1] == -1){
           return null;
       }
       Tree root = new Tree(array[i - 1]);
       root.lef = initTree(array, 2 * i);
       root.rig = initTree(array, 2 * i + 1);
       return root;
    }


}
