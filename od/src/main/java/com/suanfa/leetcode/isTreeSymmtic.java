package com.suanfa.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class isTreeSymmtic {
  class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){

    }
    public TreeNode(int val){
      this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right){
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
  public boolean isSymmetric(TreeNode root) {
    if(root == null){
      return true;
    }
    return dfs(root.left, root.right);
  }

  private boolean dfs(TreeNode left, TreeNode right) {
    if(left == null && right == null){
      return true;
    }
    if(left == null || right == null){
      return false;
    }
    if(left.val != right.val){
      return false;
    }
    return dfs(left.left, right.right) && dfs(left.right, right.left);
  }

  public boolean getResult(TreeNode root){
    if(root == null || (root.left == null && root.right == null)){
      return true;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root.left);
    queue.add(root.right);
    while (queue.size() > 0){
      TreeNode left = queue.poll();
      TreeNode right = queue.poll();
      if(left == null && right == null){
        continue;
      }
      if(left == null || right == null){
        return false;
      }
      if(left.val != right.val){
        return false;
      }
      queue.add(left.left);
      queue.add(right.right);
      queue.add(left.right);
      queue.add(right.left);
    }
    return true;
  }
}
