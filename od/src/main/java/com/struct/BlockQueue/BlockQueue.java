package com.struct.BlockQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue {

  private final String[] array = new String[10];
  private int tail = 0;
  private int size = 0;
  ReentrantLock lock = new ReentrantLock();
  Condition condition = lock.newCondition();

  public void offer(String e) throws InterruptedException{
    lock.lockInterruptibly();
    try {
      while(isFull()){
        condition.await();
      }
      array[tail] = e;
      if(++tail == array.length){
        tail = 0;
      }
      size++;
    } finally {
      lock.unlock();
    }
  }

  private boolean isFull(){
    return size == array.length;
  }


}
