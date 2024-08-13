package com.struct.Tree;

import com.struct.Queue.LinkListQueue;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(TreeNode left, int val, TreeNode right) {
    this.left = left;
    this.val = val;
    this.right = right;
  }

  TreeNode tree1 = new TreeNode(
    new TreeNode(
      new TreeNode(4),
      2,
      new TreeNode(5)
    ),
    1,
    new TreeNode(
      new TreeNode(6),
      3,
      new TreeNode(7)
    )
  );

  TreeNode tree2 = new TreeNode(
    new TreeNode(
      new TreeNode(null,2,null),
      4,
      null
    ),
    1,
    new TreeNode(
      new TreeNode(null,5,null),
      3,
      new TreeNode(null,6,null)
    )
  );
  //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
  LinkListQueue<TreeNode> queue = new LinkListQueue<>();

  LinkedList<TreeNode> stack = new LinkedList<>();

  public void findByLevel(){
    queue.offer(tree1);
    while (!queue.isEmpty()){
      TreeNode node = queue.poll();
      System.out.println(node);
      if(node.left != null){
        queue.offer(node.left);
      }
      if(node.right != null){
        queue.offer(node.right);
      }
    }
  }

  public void findOrder(){
    TreeNode curr = tree2;
    while (curr != null || !stack.isEmpty()){
      if(curr != null){
        System.out.println(curr.val);
        stack.push(curr);
        curr = curr.left;
      } else {
        TreeNode pop = stack.pop();
        System.out.println(pop);
        curr = pop.right;
      }
    }
  }

  public void findOrderBack(){
    TreeNode curr = tree2;

    while (curr != null || !stack.isEmpty()){
      if(curr != null){
        System.out.println(curr.val);
        stack.push(curr);
        curr = curr.left;
      } else {
        TreeNode peek = stack.peek();
        TreeNode pop = null;
        if(peek.right == null || peek.right == pop){
          pop = stack.pop();
          System.out.println(pop.val);
        } else{
          curr = peek.right;
        }
      }
    }
  }

  public void findMiddleOrder(TreeNode root){
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()){
      TreeNode poll = queue.poll();
      System.out.println(poll.val);
      if(poll.left != null){
        queue.offer(poll.left);
      }
      if(poll.right != null){
        queue.offer(poll.right);
      }
    }
  }

  private static void swaps(TreeNode node){
    if(node == null){
      return;
    }
    TreeNode temp = node.left;
    node.left = node.right;
    node.right = temp;

    swaps(node.left);
    swaps(node.right);
  }

}
