package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NodeHeadToTail {
  public static void main(String[] args) {

  }

  static class ListNode {
    int val;
    ListNode next;
    public ListNode (int val){
      this.val = val;
      this.next = null;
    }
    public ListNode (int val, ListNode next){
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("val", val)
        .append("next", next)
        .toString();
    }
  }
  public boolean hasCycleQ(ListNode head){
    ListNode cur = head;
    while (cur != null){
      if(cur.val == 200000){
        return true;
      }
      cur.val = 200000;
      cur = cur.next;
    }
    return false;
  }

  // 1 - 2 - 3 - 4 - 5
  //判断链表是否有环
  public static boolean hasCycle(ListNode head) {
    if(head == null){
      return false;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null){
      fast = fast.next.next;
      slow = slow.next;
      if(fast == slow){
        return true;
      }
    }
    return false;
  }

  public ListNode hasCycleQ1(ListNode head) {
    if(head == null){
      return null;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null){
      fast = fast.next.next;
      slow = slow.next;
      if(fast == slow){
        return fast;
      }
    }
    return null;
  }

  //判断链表的环并输出该节点索引
  public int detectCycle(ListNode head) {
    int count = 0;
    ListNode cycle = hasCycleQ1(head);
    if(cycle == null){
      return -1;
    }
    while (head != null){
      if(head == cycle){
        break;
      }
      count++;
      head = head.next;
    }
    return count;
  }
}
