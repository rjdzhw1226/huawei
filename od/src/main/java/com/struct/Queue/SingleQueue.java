package com.struct.Queue;

/**
 * <ul>
 *   <li>栈实现队列</li>
 * </ul>
 */
public class SingleQueue {

  ArrayQueue<Integer> queue = new ArrayQueue<>(100);

  private int size = 0;

  public void push(int x){
    queue.offer(x);
    for (int i = 0; i < size; i++) {
      queue.offer(queue.poll());
    }
    size++;
  }

  public int pop(){
    size--;
    return queue.poll();
  }

  public int top(){
    return queue.peek();
  }

  public boolean empty(){
    return queue.isEmpty();
  }


}
