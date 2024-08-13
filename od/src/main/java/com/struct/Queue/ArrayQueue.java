package com.struct.Queue;

import java.util.Iterator;

/**
 * 环形数组实现
 * @param <E>
 */
public class ArrayQueue<E> implements QueueMine<E>, Iterable {

  private E[] array;
  private int head = 0;
  private int tail = 0;

  @SuppressWarnings("all")
  public ArrayQueue(int capacity) {
    array = (E[]) new Object[capacity + 1];
  }


  /**
   * 队列尾插入值
   *
   * @param value 待插入值
   * @return
   */
  @Override
  public boolean offer(E value) {
    if (isFull()) {
      return false;
    }
    array[tail] = value;
    tail = (tail + 1) % array.length;
    return true;
  }

  /**
   * 移除队列头的值
   *
   * @return
   */
  @Override
  public E poll() {
    if (isEmpty()) {
      return null;
    }
    E value = array[head];
    head = (head + 1) % array.length;
    return value;
  }

  /**
   * 获取队列头的值
   *
   * @return
   */
  @Override
  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return array[head];
  }

  /**
   * 判空
   *
   * @return
   */
  @Override
  public boolean isEmpty() {
    return head == tail;
  }

  /**
   * 判满
   *
   * @return
   */
  @Override
  public boolean isFull() {
    return (tail + 1) % array.length == head;
  }


  @Override
  public Iterator iterator() {
    return new Iterator() {
      int p = head;

      @Override
      public boolean hasNext() {
        return p != tail;
      }

      @Override
      public Object next() {
        E value = array[p];
        p = (p + 1) % array.length;
        return value;
      }
    };
  }
}
