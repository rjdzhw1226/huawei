package com.suanfa.leetcode;

import com.struct.ListNode.ListNode;

import java.util.Stack;

//排序链表
public class sortListNode {
  public static void main(String[] args) {
    sortListNode st = new sortListNode();
    st.swapPairs(new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, null))))));
  }
  public ListNode sortNodes(ListNode root){
    Stack<Integer> stack = new Stack<>();
    while (root != null){
      stack.push(root.val);
      root = root.next;
    }
    stack.sort((o1, o2) -> o1 - o2);
    ListNode node = null;
    while (!stack.isEmpty()){
      ListNode listNode = new ListNode(stack.pop());
      if(node == null){
        node = listNode;
        continue;
      }
      listNode.next = node;
      node = listNode;
    }
    System.out.println(node);
    return node;
  }

  //两两交换链表中的节点 非递归
  public ListNode swapPairs(ListNode head) {
    ListNode node = head;
    while (head != null && head.next != null){
      ListNode cur = head;
      ListNode next = head.next;
      int temp = cur.val;
      cur.val = next.val;
      next.val = temp;
      head = head.next.next;
    }
    System.out.println(node);
    return node;
  }

  //递归解法
  public ListNode swapPairsQ(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    ListNode next = head.next;
    head.next = swapPairsQ(next.next);
    next.next = head;
    return next;
  }
}
