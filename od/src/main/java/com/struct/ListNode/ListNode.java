package com.struct.ListNode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode(int x, ListNode nextNode) { val = x; next = nextNode;}

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
      .append("val", val)
      .append("next", next)
      .toString();
  }

  static class list{
    ListNode head;

    public list(ListNode head){
      this.head = head;
    }

    public void addFirst(ListNode first){
      first.next = head;
      head = first;
    }

    public ListNode removeFirst(){
      ListNode first = head;
      if(first != null){
        head = first.next;
      }
      return first;
    }
  }

  public ListNode reserveList(ListNode o1){
      ListNode n1 = null;
      ListNode p = o1;
      while (p != null){
        n1 = new ListNode(p.val, n1);
        p = p.next;
      }
      return n1;
  }

  public ListNode reserveList2(ListNode o1){
    list l1 = new list(o1);
    list l2 = new list(null);
    while (true){
      ListNode listNode = l1.removeFirst();
      if(listNode == null){
        break;
      }
      l2.addFirst(listNode);
    }
    return l2.head;
  }

  public ListNode reserveList4(ListNode o1){
    if(o1 == null || o1.next == null){
      return o1;
    }
    ListNode o2 = o1.next;
    ListNode n1 = o1;
    while (o2 != null){
      o1.next = o2.next;
      o2.next = n1;
      n1 = o2;
      //重新将o2置回老链表位置进行下一次移动
      o2 = o1.next;
    }
    return n1;
  }

  public ListNode reserveList3(ListNode p){
      if(p == null || p.next == null){
        return p;
      }
      ListNode listNode = reserveList3(p.next);
      p.next.next = p;
      p.next = null;
      return listNode;
  }

  public ListNode removeNode(ListNode head, int val){
      ListNode s = new ListNode(-1,head);
      ListNode s1 = s;
      ListNode s2 = s.next;
      while (s2 != null){
        if(s2.val == val){
          s1.next = s2.next;
          s2 = s2.next;
        }else {
          s1 = s1.next;
          s2 = s2.next;
        }
      }
      return s.next;
    }

  /**
   * 递归调用
   * @param p
   * @param n
   * @return
   */
  private int recursion(ListNode p, int n){
      if(p == null){
        return 0;
      }
      int nth = recursion(p.next, n);
      System.out.println(p.val + " " + nth);
      if(nth == n){
        p.next = p.next.next;
      }
      return nth + 1;
  }

  /**
   * 双指针
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n){
    ListNode s = new ListNode(-1,head);
    ListNode p1 = s;
    ListNode p2 = s;
    for (int i = 0; i < n + 1; i++) {
      p2 = p2.next;
    }
    while (p2 != null){
      p1 = p1.next;
      p2 = p2.next;
    }
    p1.next = p1.next.next;
    return s.next;
  }

  public ListNode sort(ListNode current, ListNode head){
    if(current == null){
      return head;
    }
    if(current.val > current.next.val){
      int temp = current.next.val;
      current.next.val = current.val;
      current.val = temp;
    }
    return sort(current.next,head);
  }


  /**
   * 递归
   * <p>重复则返回next 不重复则返回自己但是更新 </p>
   * @param head
   * @return
   */
  public ListNode deleteDuplicate(ListNode head){
    if(head == null || head.next == null){
      return head;
    }
    if(head.val == head.next.val){
      return deleteDuplicate(head.next);
    }else {
      head.next = deleteDuplicate(head.next);
      return head;
    }
  }

  public ListNode deleteDuplicate1(ListNode p){
    if(p == null || p.next == null){
      return p;
    }
    if(p.val == p.next.val){
      ListNode x = p.next.next;
      while (x != null && x.val == p.val){
        x = x.next;
      }
      return deleteDuplicate1(x);
    }else {
      p.next = deleteDuplicate1(p.next);
      return p;
    }
  }

  public ListNode mergeTwoList(ListNode head1, ListNode head2){
    ListNode s = new ListNode(-1, null);
    ListNode newNode = s;
    while (head1 != null && head2 != null){
      if(head1.val < head2.val){
        newNode.next = head1;
        newNode = newNode.next;
        head1 = head1.next;
      }else {
        newNode.next = head2;
        newNode = newNode.next;
        head2 = head2.next;
      }
    }
    if(head1 != null){
      newNode.next = head1;
    }
    if(head2 != null){
      newNode.next = head2;
    }
    return s.next;
  }

  public ListNode mergeKLists(ListNode[] lists){
    if(lists.length == 0){
      return null;
    }
    return splitList(lists,0,lists.length -1);
  }

  private ListNode splitList(ListNode[] lists, int i, int j) {
    if(i == j){
      return lists[i];
    }
    int m = (i + j) >>> 1;
    ListNode left = splitList(lists, i, m);
    ListNode right = splitList(lists, m + 1, j);
    return mergeTwoList(left,right);
  }

  /**
   * 回文链表
   * @param head
   * @return
   */
  public boolean isPalindrome(ListNode head){
    ListNode middle = middle(head);
    ListNode newNode = reserve(middle);
    while (newNode != null){
      if (newNode.val != head.val) {
        return false;
      }
      newNode = newNode.next;
      head = head.next;
    }
    return true;
  }

  private ListNode reserve(ListNode middle) {
    ListNode n1 = null;
    while (middle != null){
      //暂存旧头的下一个节点
      ListNode temp = middle.next;
      //指向新链表头节点
      middle.next = n1;
      //新头节点赋值
      n1 = middle;
      //把旧头换到第二个
      middle = temp;
    }
    return n1;
  }

  /**
   * 寻环入口
   * @param head
   * @return
   */
  public ListNode findCycleEntry(ListNode head){
    ListNode h = head;
    ListNode t = head;
    while (h != null && h.next != null){
      t = t.next;
      h = h.next.next;
      if(h == t){
         t = head;
         while (true){
           if(t == h){
             return h;
           }
           t = t.next;
           h = h.next;
         }
      }
    }
    return null;
  }
  private static ListNode middle(ListNode head) {
    ListNode p1 = head;
    ListNode p2 = head;
    while (p2 != null && p2.next != null){
      p1 = p1.next;
      p2 = p2.next.next;
    }
    return p1;
  }

  /**
   * 判环算法
   */
  public boolean hasCycle(ListNode head){
    ListNode h = head;
    ListNode t = head;
    while (h != null && h.next != null){
      t = t.next;
      h = h.next.next;
      if(h == t){
        return true;
      }
    }
    return false;
  }

  /**
   * 合并有序数组的两个区间<br>
   * i iend 第一个有序数组起止 j jend 第二个有序数组起止
   */
  public void mergeArrray(int[] a1, int i, int iEnd, int[] a2, int j, int jEnd, int k){
    if(i > iEnd){
      System.arraycopy(a1,j,a2,k,jEnd - j + 1);
      return;
    }
    if(j > jEnd){
      System.arraycopy(a1,i,a2,k,iEnd - i + 1);
      return;
    }
    if (a1[i] < a1[j]) {
      a2[k] = a1[i];
      mergeArrray(a1,i+1, iEnd, a2, j, jEnd ,k+1);
    }else {
      a2[k] = a1[j];
      mergeArrray(a1,i, iEnd, a2, j+1, jEnd ,k+1);
    }
  }

  /**
   * 方法二
   * @param a1
   * @param i
   * @param iEnd
   * @param a2
   * @param j
   * @param jEnd
   */
  public void mergeArrray1(int[] a1, int i, int iEnd, int[] a2, int j, int jEnd){
    int k = 0;
    while (i <= iEnd && j < jEnd){
      if(a1[i] < a1[j]){
        a2[k] = a1[i];
        i++;
      }else {
        a2[k] = a1[j];
        j++;
      }
      k++;
    }
    if(i > iEnd){
      System.arraycopy(a1,j,a2,k,jEnd - j + 1);
    }
    if(j > jEnd){
      System.arraycopy(a1,i,a2,k,iEnd - i + 1);
    }
  }
}
