package com.suanfa.gpthelp;

import com.suanfa.leetcode.RandomNode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Hashtable;

class Solution {
  static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val, Node next, Node random) {
      this.val = val;
      this.next = next;
      this.random = random;
    }

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }

    @Override
    public String toString() {
      return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("val", val)
        .append("next", next)
        .append("random", random)
        .toString();
    }
  }

  public static void main(String[] args) {
    Solution so = new Solution();
    Node three = new Node(3, null, new Node(0));
    Node two = new Node(2, three, three);
    Node one = new Node(1, two, three);
    so.copyRandomListQ(one);
  }

  public Node copyRandomListQ(Node head){
    Hashtable<Node, Node> hash = new Hashtable<>();
    while (head != null) {
      hash.put(head, head.random);
      head = head.next;
    }
    System.out.println(hash);
    return new Node(0);
  }

  public Node copyRandomList(Node head) {
    Node cur = head;
    Node dum = new Node(0);
    Node pre = dum;
    while (cur != null) {
      Node node = new Node(cur.val); // 复制节点 cur
      pre.next = node;               // 新链表的 前驱节点 -> 当前节点
      // pre.random = "???";         // 新链表的 「 前驱节点 -> 当前节点 」 无法确定
      cur = cur.next;                // 遍历下一节点
      pre = node;                    // 保存当前新节点
    }
    return dum.next;
  }
}
