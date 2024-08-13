package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class mergeTwoListNodeFromRemoveAToB {
  static class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
      this.val = val;
    }

    public ListNode(int val, ListNode next) {
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

  public static void main(String[] args) {
    mergeInBetween(new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))))), 2, 5, new ListNode(10001, new ListNode(10002, new ListNode(10003, null))));
  }

  //定义两个指针头指向头 尾指向尾就好了
  public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    ListNode aL = null;
    ListNode bL = null;
    ListNode head = list1;
    while (--a > 0 && --b > 0) {
      list1 = list1.next;
    }
    aL = list1;
    while (b-- > 0){
      list1 = list1.next;
    }
    bL = list1;
    aL.next = list2;
    while (list2.next != null){
      list2 = list2.next;
    }
    list2.next = bL.next;
    System.out.println(head);
    return head;
  }
}
