package com.suanfa.leetcode;

//随机链表的复制 todo
public class RandomNode {
  class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public Node copyRandomList(Node head) {
    return new Node(0);
  }

}
