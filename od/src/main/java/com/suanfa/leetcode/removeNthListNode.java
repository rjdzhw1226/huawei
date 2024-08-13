package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class removeNthListNode {
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
    removeNthFromEnd(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))), 2);
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    Stack<Integer> stack = new Stack<>();

    while (head != null){
      stack.push(head.val);
      head = head.next;
    }
    int index = 1;
    ListNode newHead = null;
    while (!stack.isEmpty()){
      if(index == n){
        index++;
        stack.pop();
      } else {
        ListNode listNode = new ListNode(stack.pop());
        index++;
        if(newHead == null){
          newHead = listNode;
          continue;
        }
        listNode.next = newHead;
        newHead = listNode;
      }
    }
    System.out.println(newHead);
    return newHead;
  }
}
