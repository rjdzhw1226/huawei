package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class crashListNode {
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

//  public static void main(String[] args) {
//    getIntersectionNode()
//  }

  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode B = headB;
    while (headA != null){
      while (headB != null){
        if(headA == headB){
          return headA;
        }
        headB = headB.next;
      }
      headB = B;
      headA = headA.next;
    }
    return null;
  }


}
