package com.struct.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringJoiner;

public class TreeNodeLeetcode {
  static class TreeNode {
    public int level;
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode() {
    }

    public TreeNode(int level, int val, TreeNode left, TreeNode right) {
      this.level = level;
      this.val = val;
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
        .add("level=" + level)
        .add("val=" + val)
        .add("left=" + left)
        .add("right=" + right)
        .add("parent=" + parent)
        .toString();
    }

    public static void main(String[] args) {
      TreeNode treeNode = new TreeNode(0, 3,
        new TreeNode(1, 5,
          new TreeNode(2, 6, null, null),
          new TreeNode(2, 2,
            new TreeNode(3, 7, null, null),
            new TreeNode(3, 4, null, null))
        ),
        new TreeNode(1, 1,
          new TreeNode(2, 0, null, null),
          new TreeNode(2, 8, null, null)
        )
      );
//		System.out.println(find(treeNode, 3, 5));
      closeParent(treeNode, 7, 4);
    }

    //--XX:+TraceClassLoading 类的加载日志 --XX:+TraceClassUnloading 类的卸载日志
    public static void closeParent(TreeNode node, int v1, int v2){
      find(node, v1, v2);
      Integer v11 = map.get("v1").level;
      Integer v111 = map.get("v1").val;
      Integer v22 = map.get("v2").level;
      Integer v222 = map.get("v2").val;
      if(!v11.equals(v22)){
        //一上一下 层级高的节点
        if(v11 > v22){
          System.out.println(map.get("v1").val);
        } else {
          System.out.println(map.get("v2").val);
        }
      } else {
        //同一级 共同父节点
        while (node != null || !stack.isEmpty()){
          TreeNode temp = node;
          if(node != null){
            stack.push(node);
            node = node.left;
            if(temp.left != null && temp.right != null){
              if(temp.left.val == v111 && temp.right.val == v222){
                System.out.println(temp.val);
              }
              if(temp.left.val == v222 && temp.right.val == v111){
                System.out.println(temp.val);
              }
            }
          } else {
            TreeNode pop = stack.pop();
            node = pop.right;
          }

        }

      }
    }
    static Map<String, TreeNode> map = new HashMap<>();
    private static Map<String, TreeNode> find(TreeNode node, int v1, int v2) {
      if (node != null){
        if(node.val == v1){
          map.put("v1", node);
        }
        if(node.val == v2){
          map.put("v2", node);
        }
        find(node.left, v1, v2);
        find(node.right, v1, v2);
      }
      return map;
    }

    /*
     *    3
     *   / \
     *  5   1
     * / \ / \
     * 2 4 9 7
     *
     * */
    static Stack<TreeNode> stack = new Stack<>();
    static Stack<TreeNode> stackCopy = new Stack<>();
    public static TreeNode init(TreeNode root){
      //构建父节点
      TreeNode cur = root;
      TreeNode copy = root;

      while (!(cur.left == null && cur.right == null) || !stack.isEmpty()){
        TreeNode temp = copy;
        if (!(cur.left == null && cur.right == null)){
          stack.push(cur);
          stackCopy.push(copy);
          cur = cur.left;
          copy = copy.left;
        } else {
          TreeNode pop = stack.pop();
          TreeNode popCopy = stackCopy.pop();
          cur = pop.right;
          copy = popCopy.right;
        }
        copy.parent = temp;
      }
      return copy;
    }

  }


}
