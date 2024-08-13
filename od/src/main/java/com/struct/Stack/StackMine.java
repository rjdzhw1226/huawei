package com.struct.Stack;

public interface StackMine<E> {

  /**
   * 压栈
   * @param value
   * @return
   */
  boolean push(E value);

  /**
   * 弹栈
   * @return
   */
  E pop();

  /**
   * 取栈顶元素
   * @return
   */
  E peek();

  /**
   * 判空
   * @return
   */
  boolean isEmpty();

  /**
   * 判满
   * @return
   */
  boolean isFull();
}
