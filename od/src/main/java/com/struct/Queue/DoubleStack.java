package com.struct.Queue;

import com.struct.Stack.ArrayStack;

/**
 * <ul>
 *   <li>双栈队列</li>
 * </ul>
 */
public class DoubleStack {

  ArrayStack<Integer> s1 = new ArrayStack<>(100);
  ArrayStack<Integer> s2 = new ArrayStack<>(100);

  public void push(int x){
    s2.push(x);
  }

  public int pop(){
    if(s1.isEmpty()){
      while(!s2.isEmpty()){
        s1.push(s2.pop());
      }
    }
    return s1.pop();
  }

  public int peek(){
    if(s1.isEmpty()){
      while(!s2.isEmpty()){
        s1.push(s2.pop());
      }
    }
    return s1.peek();
  }

  public boolean isEmpty(){
    return s1.isEmpty() && s2.isEmpty();
  }
}
