package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//二叉树路径的和
public class treePathSum {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  List<List<Integer>> res = new ArrayList<>();
  LinkedList<Integer> ans = new LinkedList<>();

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    dfs(root, targetSum);
    return res;
  }

  private void dfs(TreeNode root, int targetSum) {
    if(root == null){
      return;
    }
    ans.add(root.val);
    targetSum -= root.val;
    if(targetSum == 0 && root.left == null && root.right == null){
      res.add(new ArrayList<>(ans));
    }
    dfs(root.left, targetSum);
    dfs(root.right, targetSum);
    ans.removeLast();
  }

}
