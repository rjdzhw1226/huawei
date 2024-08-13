package com.struct.ListNode;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * <p>单链表</p>
 */
public class SinglyLinkedListMine implements Iterable<Integer>{
  private Node head = null;

  /**
   * 当某个类使用到了外部类的成员变量时就不能加static
   * @return
   */

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      Node p = head;
      @Override
      public boolean hasNext() { //是否有下一个元素
        return p != null;
      }

      @Override
      public Integer next() { //返回当前元素值,并指向下一个元素
        int v = p.value;
        p = p.next;
        return v;
      }
    };
  }

  /**
   * <h3>@author 任嘉冬</h3>
   * <p>节点类</p>
   */
  private static class Node{
    int value;
    Node next;

    public Node(int value, Node next){
      this.value = value;
      this.next = next;
    }
  }

  /**
   * <h3>@author 任嘉冬</h3>
   * <h3>@param value</h3>
   * <p>头插法</p>
   */
  public void addFirst(int value){
    head = new Node(value, head);
  }

  /**
   * 搜索最后一个节点
   * @return
   */
  private Node findLast(){
    if(head == null){
      return null;
    }
    Node p;
    for (p = head; p != null; p = p.next){

    }
    return p;
  }

  /**
   * @param value
   * 尾插法
   */
  public void addTail(int value){
    Node last = findLast();
    if(last == null){
      addFirst(value);
      return;
    }
    last.next = new Node(value, null);
  }

  /**
   * <h3>@author 任嘉冬</h3>
   * <h3>@param value</h3>
   * <p>遍历链表</p>
   *
   */
  public void loop1(Consumer<Integer> consumer){
    Node p = head;
    while(p != null){
      consumer.accept(p.value);
      p = p.next;
    }
  }

  /**
   * <h3>@author 任嘉冬</h3>
   * <h3>@param value</h3>
   * <p>遍历链表</p>
   */
  public void loop2(Consumer<Integer> consumer){
    for (Node p = head; p != null; p = p.next){
      consumer.accept(p.value);
    }
  }

  /**
   * 按索引返回
   * @param index
   * @return
   */
  private Node findNode(int index){
    int i = 0;
    for (Node p = head; p != null; p = p.next,i++){
      if(i == index){
        return p;
      }
    }
    return null;
  }

  public int get(int index) {
    Node node = findNode(index);
    if(node == null){
      throw new IllegalArgumentException(String.format("index [%d] 不合法%n",index));
    }
    return node.value;
  }

  /**
   * 对索引位置插入元素
   */
  public void insert(int index, int value){
    if(index == 0){
      addFirst(value);
      return;
    }
    Node prev = findNode(index - 1);
    if(prev == null){
      throw new IllegalArgumentException(String.format("index [%d] 不合法%n",index));
    }
    prev.next = new Node(value,prev.next);

  }

  public void removeFirst(){
    if(head == null){
      throw new IllegalArgumentException("链表为空不能删除");
    }
    head = head.next;
  }

  public void remove(int index){
    if(index == 0){
      removeFirst();
      return;
    }
    Node prev = findNode(index - 1);
    if(prev == null){
      throw new IllegalArgumentException("节点为空不能删除");
    }
    Node removed = prev.next;
    if(removed == null){
      throw new IllegalArgumentException("节点为空不能删除");
    }
    prev.next = removed.next;
  }
}
