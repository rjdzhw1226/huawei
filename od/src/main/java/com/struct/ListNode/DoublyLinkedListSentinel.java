package com.struct.ListNode;

import java.util.Iterator;

/**
 * 双链表
 */
public class DoublyLinkedListSentinel implements Iterable<Integer>{
  static class Node{
    Node prev;
    int value;
    Node next;

    public Node(Node next, int value, Node prev){
      this.prev = prev;
      this.value = value;
      this.next = next;
    }
  }
  private Node head;
  private Node tail;

  public DoublyLinkedListSentinel(){
    head = new Node(null,666,null);
    tail = new Node(null,888,null);
    head.next = tail;
    tail.prev = head;
  }

  /**
   * 查找节点
   * @param index
   * @return
   */
  private Node findNode(int index){
    int i = -1;
    for(Node p = head; p != tail; p = p.next, i++){
      if(i == index){
        return p;
      }
    }
    return null;
  }

  /**
   * 插入节点
   * @param index
   * @param value
   */
  public void insert(int index, int value){
    Node prev = findNode(index - 1);
    if(prev == null){
      throw new IllegalArgumentException("index值非法");
    }
    Node next = prev.next;
    Node inserted = new Node(prev, value, next);
    prev.next = inserted;
    next.prev = inserted;
  }

  /**
   * 删除节点
   * @param index
   */
  public void remove(int index){
    Node prev = findNode(index - 1);
    if(prev == null){
      throw new IllegalArgumentException("index值非法");
    }
    Node removed = prev.next;
    if(removed == tail){
      throw new IllegalArgumentException("index值非法");
    }
    Node next = removed.next;
    prev.next = next;
    next.prev = prev;
  }

  /**
   * 插入第一个
   * @param value
   */
  public void addFirst(int value){
    insert(0,value);
  }

  /**
   * 删除第一个
   */
  public void removeFirst(){
    remove(0);
  }

  /**
   * 尾插
   * @param value
   */
  public void addLast(int value){
    Node last = tail.prev;
    Node added = new Node(last, value, tail);
    last.next = added;
    tail.prev = added;
  }

  /**
   * 尾删
   */
  public void removeLast(){
    Node removed = tail.prev;
    if(removed == head){
      throw new IllegalArgumentException("index值非法");
    }
    Node prev = removed.prev;
    prev.next = tail;
    tail.prev = prev;
  }

  /**
   * 迭代器
   * @return
   */
  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      Node p = head.next;
      @Override
      public boolean hasNext() {
        return p != tail;
      }

      @Override
      public Integer next() {
        int value = p.value;
        p = p.next;
        return value;
      }
    };
  }
}
