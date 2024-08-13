package com.struct.Dqueue;

import java.util.Iterator;

/**
 * 使用数组实现双端队列 考虑内存释放问题
 * @param <E>
 */
public class TwoQueueArray<E> implements Dqueue<E> ,Iterable<E>{

  static int inc(int i, int length){
    if(i + 1 >= length){
      return 0;
    }
    return i + 1;
  }

  static int dec(int i, int length){
    if(i - 1 < 0){
      return length - 1;
    }
    return i - 1;
  }

  E[] array;
  int head;
  int tail;

  public TwoQueueArray(int capacity) {
    array = (E[]) new Object[capacity + 1];
  }

  @Override
  public boolean offerFirst(E e) {
    return false;
  }

  @Override
  public boolean offerLast(E e) {
    return false;
  }

  @Override
  public E pollFirst() {
    return null;
  }

  @Override
  public E pollLast() {
    return null;
  }

  @Override
  public E peekFirst() {
    return null;
  }

  @Override
  public E peekLast() {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return head == tail;
  }

  @Override
  public boolean isFull() {
    if(tail > head){
      return tail - head == array.length - 1;
    }else if (tail < head){
     return head - tail == 1;
    }else {
      return false;
    }
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<E> iterator() {
    return null;
  }
}
