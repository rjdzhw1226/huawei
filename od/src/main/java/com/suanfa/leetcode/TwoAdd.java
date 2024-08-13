package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Stack;

public class TwoAdd {

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
    ListNode listNode = TwoAddTail(new ListNode(2, new ListNode(4, new ListNode(3, null))), new ListNode(5, new ListNode(6, new ListNode(4, null))));
    System.out.println(listNode);
  }

  public static ListNode TwoAddHead(ListNode fir, ListNode sec){
    int n1 = findNum(fir);
    int n2 = findNum(sec);
    int n = n1 + n2;
    //头插法
    ListNode newNode = null;
    while (n != 0){
      int val = n % 10;
      ListNode listNode = new ListNode(val);
      n = n / 10;
      if(newNode == null){
        newNode = listNode;
        continue;
      }
      listNode.next = newNode;
      newNode = listNode;
    }
    return newNode;
  }

  public static ListNode TwoAddTail(ListNode fir, ListNode sec){
    int n1 = findNum(fir);
    int n2 = findNum(sec);
    int n = n1 + n2;
    //尾插法
    ListNode newNode = null;
    while (n != 0){
      int val = n % 10;
      ListNode listNode = new ListNode(val);
      n = n / 10;
      if(newNode == null){
        newNode = listNode;
        continue;
      }
      ListNode cur = newNode;
      while (cur.next != null){
        cur = cur.next;
      }
      cur.next = listNode;
    }
    return newNode;
  }

  private static int findNum(ListNode fir) {
    Stack<Integer> stack = new Stack<>();
    while (fir != null){
      stack.push(fir.val);
      fir = fir.next;
    }
    String str = "";
    while (!stack.isEmpty()){
      str += stack.pop() + "";
    }
    return Integer.parseInt(str);
  }


}
