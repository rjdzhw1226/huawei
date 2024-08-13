package com.struct.Queue;

import java.util.Iterator;

public class ArrayQueue01 <E> implements QueueMine<E>, Iterable<E>{

  public static void main(String[] args) {
    printPa(12);
  }
  public static void printPa(int line){
    for (int i = 1; i <= line; i++) {
      for (int j = 0; j < i; j++) {
        System.out.print(" 爬");
      }
      System.out.println();
    }
  }

  private final E[] array;
  int head = 0;
  int tail = 0;

  public ArrayQueue01(int capacity) {
    if((capacity & capacity - 1) != 0){
      throw new IllegalArgumentException("capacity must be 2^n");
    }
    //log2(x) 2为底的对数
    capacity = 1 << ((int)(Math.log10(capacity - 1) / Math.log10(2)) + 1);
    array = (E[]) new Object[capacity];
  }

  /**
   * 队列尾插入值
   *
   * @param value 待插入值
   * @return
   */
  @Override
  public boolean offer(E value) {
    return false;
  }

  /**
   * 移除队列头的值
   *
   * @return
   */
  @Override
  public E poll() {
    return null;
  }

  /**
   * 获取队列头的值
   *
   * @return
   */
  @Override
  public E peek() {
    return null;
  }

  /**
   * 判空
   *
   * @return
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * 判满
   *
   * @return
   */
  @Override
  public boolean isFull() {
    return false;
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator iterator() {
    return null;
  }
}
