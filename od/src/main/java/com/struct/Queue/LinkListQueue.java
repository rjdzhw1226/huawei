package com.struct.Queue;

import java.util.Iterator;

public class LinkListQueue<E> implements QueueMine<E>, Iterable<E> {
  @Override
  public String toString() {
    return "LinkListQueue{" +
      "head=" + head +
      ", tail=" + tail +
      ", size=" + size +
      ", capacity=" + capacity +
      '}';
  }

  public static class Node<E> {
    E value;
    Node<E> next;

    public Node(E value, Node<E> next){
      this.value = value;
      this.next = next;
    }
  }

  /**
   * 头节点
   */
  Node<E> head = new Node<>(null, null);

  /**
   * 尾节点
   */
  Node<E> tail = head;

  //节点数
  int size;

  //容量
  int capacity;
  public LinkListQueue() {
    tail.next = head;
  }

  /**
   * 队列尾插入值
   * @param value 待插入值
   * @return
   */
  @Override
  public boolean offer(E value) {
    Node<E> added = new Node<>(value, head);
    tail.next = added;
    tail = added;
    return true;
  }

  /**
   * 移除队列头的值
   * @return
   */
  @Override
  public E poll() {
    if(isEmpty()){
      return null;
    }
    Node<E> first = head.next;
    head.next = first.next;
    if(first == tail){
      tail = head;
    }
    return first.value;
  }

  /**
   * 获取队列头的值
   * @return
   */
  @Override
  public E peek() {
    if(isEmpty()){
      return null;
    }
    return head.next.value;
  }

  /**
   * 判空
   * @return
   */
  @Override
  public boolean isEmpty() {
    return head == tail;
  }

  /**
   * 判满
   * @return
   */
  @Override
  public boolean isFull() {
    return false;
  }

  /**
   * 迭代器
   * @return an Iterator.
   */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      Node<E> p = head.next;
      @Override
      public boolean hasNext() {
        return p.next != null;
      }

      @Override
      public E next() {
        E value = p.value;
        return value;
      }
    };
  }
}
