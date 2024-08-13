package com.suanfa.leetcode;

import java.util.*;

public class TreeEvery {
  public static class TreeNode {
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

  //逐层访问二叉树的每个节点
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      List<Integer> level = new ArrayList<>();
      for (int i = 1; i <= queue.size(); ++i) {
        TreeNode poll = queue.poll();
        level.add(Objects.requireNonNull(poll).val);
        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
      }
      res.add(level);
    }
    return res;
  }

  public void Order(TreeNode root){
    if (root != null){
      //前序遍历
      System.out.println(root.val);
      Order(root.left);
      //中序遍历
//      System.out.println(root.val);
      Order(root.right);
      //后序遍历
//      System.out.println(root.val);
    }
  }

  public void OrderUr(TreeNode root){
    if(root == null){
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    while (root != null || !stack.isEmpty()){
      if(root != null){
        //前序
        System.out.println(root.val);
        stack.push(root);
        root = root.left;
      } else {
        //中序
//        System.out.println(root.val);
        root = stack.pop().right;
      }
    }
  }

  //后序
  public void OrderUrBack(TreeNode root){
    if(root == null){
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pre = null;
    while (root!= null || !stack.isEmpty()){
      if(root != null){
        stack.push(root);
        root = root.left;
      } else {
        root = stack.pop().right;
        if(root != null && root != pre){
          stack.push(root);
          root = root.left;
        } else {
          pre = stack.pop();
          System.out.println(pre.val);
          root = null;
        }
      }
    }
  }
}
