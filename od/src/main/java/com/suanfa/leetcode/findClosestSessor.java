package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class findClosestSessor {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode (){

    }
    public TreeNode (int val, TreeNode left, TreeNode right){
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null || root == p || root == q){
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if(left == null){
      return right;
    }
    if(right == null){
      return left;
    }
    return root;
  }

  public static void main(String[] args) {
    System.out.println(isValidBST(new TreeNode(10, new TreeNode(2,
      new TreeNode(1,
        new TreeNode(0, null, null), new TreeNode(0, null, null)),
      new TreeNode(1, null, null)),
      new TreeNode(5, null, null))));
    /*findTreeBSTKMin(
      new TreeNode(1,
        new TreeNode(2,
          null,
          new TreeNode(5, null, null)
        ),
        new TreeNode(3,
          null,
          new TreeNode(4,
            new TreeNode(9,
              null,
              new TreeNode(11, null,null)
            ),
            new TreeNode(7,
              null,
              null)
          )
        )
      ), 1
    );
    System.out.println(res);*/
  }
  //二叉树的右视图
  static List<Integer> ans = new ArrayList<>();
  public static List<Integer> rightSightView(TreeNode root){
    dfs(root, 0);
    return ans;
  }

  private static void dfs(TreeNode root, int i) {
    if(root == null){
      return;
    }
    if(i == ans.size()) {
      ans.add(root.val);
    }
    dfs(root.right, i + 1);
    dfs(root.left, i + 1);
  }

  static List<Integer> res = new ArrayList<>();

  //寻找二叉搜索树中的第k个最小节点
  public static int findTreeBSTKMin(TreeNode root, int k){
    find(root);
    return res.get(k - 1);
  }

  private static void find(TreeNode root) {
    if(root != null){
      find(root.left);
      res.add(root.val);
      find(root.right);
    }
  }

  static boolean flag = true;
  //验证二叉搜索树
  public static boolean isValidBST(TreeNode root) {
    //每个节点的左子树只包含小于当前节点值的节点
    //每个节点的右子树只包含大于当前节点的节点
    if (root == null){
      return flag;
    }
    flag = flag && findLeft(root) && findRight(root);
    if(!flag){
      return false;
    }
    isValidBST(root.left);
    isValidBST(root.right);
    return flag;
  }

  public static boolean isValidBSTQ(TreeNode root){
    if(root == null){
      return true;
    }
    if(root.left == null && root.right == null){
      return true;
    }
    if(root.left == null && root.right != null){
      return isValidBSTQ(root.right) && judge(root);
    }
    if(root.right == null && root.left != null){
      return isValidBSTQ(root.left) && judge(root);
    }
    return isValidBSTQ(root.left) && isValidBSTQ(root.right) && judge(root) && judge(root);
  }

  private static boolean judge(TreeNode root) {
    TreeNode pt = root.left;
    while (pt.right != null){
      pt = pt.right;
    }
    return pt.val > root.val;
  }

  private static boolean findRight(TreeNode root) {
    int val = root.val;

    while (root.right != null){
      root = root.right;
      if(root.val >= val){
        return false;
      }
    }
    return false;
  }

  //
  private static boolean findLeft(TreeNode root) {
    int val = root.val;

    while (root.left != null){
      root = root.left;
      if(root.val >= val){
        return false;
      }
    }
    return true;
  }


}
