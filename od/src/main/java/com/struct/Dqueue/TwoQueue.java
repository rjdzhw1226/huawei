package com.struct.Dqueue;

import java.util.Iterator;

/**
 * 双端队列
 * @param <E>
 */
public class TwoQueue<E> implements Dqueue<E>, Iterable<E>{

  static class Node<E>{
    Node<E> prev;
    E value;
    Node<E> next;

    public Node(Node<E> prev, E value, Node<E> next) {
      this.prev = prev;
      this.value = value;
      this.next = next;
    }
  }

  int capacity;
  int size;
  Node<E> snetinel = new Node<>(null,null,null);

  public TwoQueue(int capacity) {
    this.capacity = capacity;
    snetinel.next = snetinel;
    snetinel.prev = snetinel;
  }

  //a b
  @Override
  public boolean offerFirst(E e) {
    if(isFull()){
      return false;
    }
    Node<E> a = snetinel;
    Node<E> b = snetinel.next;
    Node<E> added = new Node<>(a,e,b);
    a.next = added;
    b.prev = added;
    size++;
    return true;
  }

  //a added b
  @Override
  public boolean offerLast(E e) {
    if(isFull()){
      return false;
    }
    Node<E> a = snetinel.prev;
    Node<E> b = snetinel;
    Node<E> added = new Node<>(a,e,b);
    a.next = added;
    b.prev = added;
    size++;
    return true;
  }

  //a removed b
  @Override
  public E pollFirst() {
    if(isEmpty()){
      return null;
    }
    Node<E> a = snetinel;
    Node<E> removed = snetinel.next;
    Node<E> b = removed.next;
    a.next = b;
    b.prev = a;
    size--;
    return removed.value;
  }

  @Override
  public E pollLast() {
    if(isEmpty()){
      return null;
    }
    Node<E> removed = snetinel.prev;
    Node<E> a = removed.prev;
    Node<E> b = snetinel;
    a.next = b;
    b.prev = a;
    size--;
    return removed.value;
  }

  @Override
  public E peekFirst() {
    if(isEmpty()){
      return null;
    }
    return snetinel.next.value;
  }

  @Override
  public E peekLast() {
    if(isEmpty()){
      return null;
    }
    return snetinel.prev.value;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

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
      Node<E> p = snetinel.next;
      @Override
      public boolean hasNext() {
        return p != snetinel;
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
