package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class mergeTwoListNode {
  public static class ListNode {
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

  public static void main(String[] args) {
    System.out.println(mergeTwoLists(makeAListNode(new int[]{1, 2, 3, 4}, "back"), makeAListNode(new int[]{0, 2, 5, 6}, "back")));
  }

  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    if(list1.val > list2.val){
      list2.next = mergeTwoLists(list1, list2.next);
      return list2;
    } else {
      list1.next = mergeTwoLists(list1.next, list2);
      return list1;
    }
  }

  public static ListNode makeAListNode(int[] nums, String method){
    ListNode head = null;
    if ("fir".equals(method)) {
      for (int i = 0; i < nums.length; i++) {
        ListNode cur = new ListNode(nums[i]);
        if(head == null){
          head = cur;
          continue;
        }
        cur.next = head;
        head = cur;
      }
    } else {
      for (int i = 0; i < nums.length; i++) {
        ListNode cur = new ListNode(nums[i]);
        if(head == null){
          head = cur;
          continue;
        }
        ListNode listNode = head;
        while (listNode.next != null){
          listNode = listNode.next;
        }
        listNode.next = cur;
      }
    }
    return head;
  }
}
