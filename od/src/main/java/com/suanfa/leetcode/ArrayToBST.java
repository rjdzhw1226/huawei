package com.suanfa.leetcode;

import com.struct.Tree.TreeNode;

import java.util.*;

public class ArrayToBST {
  static class TreeNode {
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
  }

  class ListNode{
    int val;
    ListNode next;
    public ListNode(){

    }
    public ListNode(int val){
      this.val = val;
    }

    public ListNode(int val, ListNode next){
      this.val = val;
      this.next = next;
    }
  }
  public static void main(String[] args) {
    System.out.println(minimumSum(new int[]{5, 4, 8, 7, 10, 2}));
  }
  public static int minimumSum(int[] nums) {
    if(nums.length < 3){
      return -1;
    }

    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      int count = i + 1;
      while(count != nums.length){
        if(nums[i] < nums[count]){
          for (int j = count + 1; j < nums.length; j++) {
            if(nums[count] > nums[j]){
              System.out.printf("%d, %d, %d", nums[i], nums[count], nums[j]);
              System.out.println();
              ans.add(nums[i] + nums[count] + nums[j]);
            }
          }
        }
        count++;
      }
    }

    return ans.size() > 0 ? ans.stream().min(Comparator.comparingInt(o -> o)).get() : -1;
  }

  //数组构建二叉搜索树
  public TreeNode sortedArrayToBST(int[] nums) {
    return dfs(nums, 0, nums.length - 1);
  }

  private TreeNode dfs(int[] nums, int i, int i1) {
    if(i > i1){
      return null;
    }
    int mid = i + (i1 - i) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = dfs(nums, i, mid - 1);
    root.right = dfs(nums, mid + 1, i1);
    return root;
  }

  //广度优先找树的最大深度
  public int findMaxDeep(TreeNode root){
    Queue<TreeNode> queue = new LinkedList<>();
    if(root == null){
      return 0;
    }
    queue.add(root);
    int count = 0;
    while (!queue.isEmpty()){
      Queue<TreeNode> temp = new LinkedList<>();
      for (TreeNode treeNode : queue) {
        if(treeNode.left != null){
          temp.add(treeNode.left);
        }
        if(treeNode.right != null){
          temp.add(treeNode.right);
        }
      }
      queue = temp;
      count++;
    }
    return count;
  }

  static int deep = 0;
  static int m = 0;

  //深度优先找树的最大深度
  public int findMaxDeepDFS(TreeNode root){
    dfsDeepth(root);
    return m;
  }

  private void dfsDeepth(TreeNode root) {
    if(root == null){
      return;
    }
    deep++;
    m = Math.max(deep, m);
    dfsDeepth(root.left);
    dfsDeepth(root.right);
    deep--;
  }

  //树的翻转
  public TreeNode reverseTree(TreeNode root){
    if(root == null){
      return null;
    }
    TreeNode left = root.left;
    root.left = root.right;
    root.right = left;

    reverseTree(root.left);
    reverseTree(root.right);
    return root;
  }

  //链表的翻转
  public ListNode reverseListNode(ListNode head){
    ListNode fir = null;
    while (head != null){
      ListNode next = head.next;
      head.next = fir;
      fir = head;
      head = next;
    }
    return fir;
  }

  //二叉树展开为链表
  static Stack<Integer> ans = new Stack<>();

  public void flatten(TreeNode root) {
    firOrder(root);
    ListNode newHead = null;
    while (!ans.isEmpty()){
      ListNode listNode = new ListNode(ans.pop());
      if(newHead == null){
        newHead = listNode;
      } else {
        listNode.next = newHead;
        newHead = listNode;
      }
    }
    System.out.println(newHead);
  }

  static List<Integer> res = new ArrayList<>();

  public void flattenT(TreeNode root){
    firOrder(root);
    int count = 0;
    ListNode newHead = null;
    while (count < res.size()){
      ListNode listNode = new ListNode(res.get(count));
      if(newHead == null){
        newHead = listNode;
      } else {
        ListNode cur = newHead;
        while (cur.next != null){
          cur = cur.next;
        }
        cur.next = listNode;
      }
      count++;
    }
  }

  private void firOrder(TreeNode root) {
    if(root == null){
      return;
    }
    res.add(root.val);
    ans.push(root.val);
    firOrder(root.left);
    firOrder(root.right);
  }
}
