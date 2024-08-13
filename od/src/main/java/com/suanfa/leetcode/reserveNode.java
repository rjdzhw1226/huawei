package com.suanfa.leetcode;

public class reserveNode {
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
  //反转链表
  //1 - 2 - 3 - 4 - 5
  //1 - null
  //2 - 1 - null
  //
  public ListNode reverseList(ListNode head) {
    ListNode listNode = null;
    while (head != null){
      ListNode next = head.next;
      head.next = listNode;
      listNode = head;
      head = next;
    }
    return listNode;
  }
}
