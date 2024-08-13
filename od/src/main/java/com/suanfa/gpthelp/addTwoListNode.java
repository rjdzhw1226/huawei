package com.suanfa.gpthelp;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class addTwoListNode {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
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
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    ListNode newList = new ListNode(0);
    ListNode nextpoint = new ListNode(0);
    if (l1 != null && l2 != null) {
      carry = l1.val + l2.val;
      nextpoint = addTwoNumbers(l1.next, l2.next);
    }
    if (l2 == null && l1 != null) {
      carry = l1.val;
      nextpoint = addTwoNumbers(l1.next, null);
    }
    if (l1 == null && l2 != null) {
      carry = l2.val;
      nextpoint = addTwoNumbers(null, l2.next);
    }
    if (l1 == null && l2 == null) {
      return new ListNode(0);
    }
    if (carry >= 10) {
      carry = carry - 10;
      nextpoint = addTwoNumbers(nextpoint, new ListNode(1));
    }
    //非常重要，若0是最后一个单出来的，不挂在链上
    if (nextpoint.val!=0||nextpoint.next!=null) {
      newList.next = nextpoint;
    }
    newList.val = carry;
    return newList;
  }

}
