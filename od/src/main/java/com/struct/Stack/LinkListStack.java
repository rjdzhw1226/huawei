package com.struct.Stack;

import java.util.Iterator;

public class LinkListStack<E> implements StackMine<E> , Iterable<E>{

  static class Node<E>{
    E value;
    Node<E> next;

    public Node(E value, Node<E> next){
      this.value = value;
      this.next = next;
    }
  }

  private int capacity;
  private int size;
  private Node<E> head = new Node<>(null,null);

  public LinkListStack(int capacity) {
    this.capacity = capacity;
  }

  /**
   * 压栈
   * @param value
   * @return
   */
  @Override
  public boolean push(E value) {
    if(isFull()){
      return false;
    }
    head.next = new Node<>(value, head.next);
    size++;
    return true;
  }

  /**
   * 弹栈
   * @return
   */
  @Override
  public E pop() {
    if(isEmpty()){
      return null;
    }
    Node<E> first = head.next;
    head.next = first.next;
    size--;
    return first.value;
  }

  /**
   * 取栈顶元素
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
    return size == 0;
  }

  /**
   * 判满
   * @return
   */
  @Override
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   * @return an Iterator.
   */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      Node<E> p = head.next;
      @Override
      public boolean hasNext() {
        return p != null;
      }

      @Override
      public E next() {
        E value = p.value;
        p = p.next;
        return value;
      }
    };
  }
}
