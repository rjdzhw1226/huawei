package com.struct.Stack;

import java.util.Iterator;

public class ArrayStack<E> implements StackMine<E>, Iterable<E>{

  private E[] array;
  private int top;

  /*
    底       顶
    0  1  2  3
    a  b
         top
   */
  public ArrayStack(int capacity) {
    this.array = (E[])new Object[capacity];
  }

  /**
   * 压栈
   * @param value
   * @return
   */
  @Override
  public boolean push(E value) {
    if(isFull()) {
      return false;
    }
    array[top++] = value;
    return true;
  }

  /**
   * 弹栈
   * @return
   */
  @Override
  public E pop() {
    if(isEmpty()) {
      return null;
    }
    return array[--top];
  }

  /**
   * 取栈顶元素
   * @return
   */
  @Override
  public E peek() {
    if(isEmpty()) {
      return null;
    }
    return array[top - 1];
  }

  /**
   * 判空
   * @return
   */
  @Override
  public boolean isEmpty() {
    return top == 0;
  }

  /**
   * 判满
   * @return
   */
  @Override
  public boolean isFull() {
    return top == array.length;
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   * @return an Iterator.
   */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      int p = top;
      @Override
      public boolean hasNext() {
        return p > 0;
      }

      @Override
      public E next() {
        return array[--p];
      }
    };
  }
}
