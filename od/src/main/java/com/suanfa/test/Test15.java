package com.suanfa.test;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Scanner;

//三叉搜索树的高度
public class Test15 {
    static class TreeNode {
        int val;
        int height;
        TreeNode mid;
        TreeNode rig;
        TreeNode lef;

        public TreeNode() {
            this.val = -1;
        }
        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, int height) {
            this.val = val;
            this.height = height;
        }

        public TreeNode(int val, TreeNode mid, TreeNode rig, TreeNode lef) {
            this.val = val;
            this.mid = mid;
            this.rig = rig;
            this.lef = lef;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                    .append("val", val)
                    .append("height", height)
                    .append("mid", mid)
                    .append("rig", rig)
                    .append("lef", lef)
                    .toString();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        TreeNode root = new TreeNode();
        for (int i = 0; i < count; i++) {
            int anInt = in.nextInt();
            findAndSet(root, anInt, 0);
        }
//        findTree(root);
        System.out.println(max);
    }
    static int max = 0;
    private static void findTree(TreeNode root) {
        if (root != null){
            if(root.height > max){
                max = root.height;
            }
        } else {
            return;
        }
        findTree(root.lef);
        findTree(root.mid);
        findTree(root.rig);
    }

    private static void findAndSet(TreeNode root, int anInt, int height) {
        if(root.val == -1){
            root.height = height + 1;
            root.val = anInt;
            max = 1;
        } else {
            if(height >= max){
                max = height + 1;
            }
            if(root.val - 500 > anInt) {
                if(root.lef != null){
                    findAndSet(root.lef, anInt, root.lef.height);
                } else {
                    root.lef = new TreeNode(anInt, root.height + 1);
                }
            } else if (root.val + 500 < anInt) {
                if(root.rig != null){
                    findAndSet(root.rig, anInt, root.rig.height);
                } else {
                    root.rig = new TreeNode(anInt, root.height + 1);
                }
            } else {
                if(root.mid != null){
                    findAndSet(root.mid, anInt, root.mid.height);
                } else {
                    root.mid = new TreeNode(anInt, root.height + 1);
                }
            }

        }
    }
}
