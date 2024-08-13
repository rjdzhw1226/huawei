package com.struct.BlockQueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue1<E> implements BlockingQueue<E> {

  private final E[] array;
  private int head;
  private int tail;
  private AtomicInteger size;

  private ReentrantLock headlock =new ReentrantLock();
  private ReentrantLock taillock =new ReentrantLock();
  private Condition headWaits = headlock.newCondition();
  private Condition tailWaits = taillock.newCondition();

  public BlockQueue1(int capacity) {
    array = (E[]) new Object[capacity];
  }

  private boolean isEmpty(){
    return size.get() == 0;
  }

  private boolean isFull(){
    return size.get() == array.length;
  }

  @Override
  public void offer(E e) throws InterruptedException {
    int c;
    taillock.lockInterruptibly();
    try {
      while (isFull()){
        tailWaits.await();
      }
      array[tail] = e;
      if(++tail == array.length){
        tail = 0;
      }
      c = size.getAndIncrement();
      if(c + 1 < array.length){
        tailWaits.signal();
      }
    } finally {
      taillock.unlock();
    }

    if(c == 0){
      headlock.lock();
      try {
        headWaits.signal();
      } finally {
        headlock.unlock();
      }
    }
  }

  @Override
  public boolean offer(E e, long timeout) throws InterruptedException {
    taillock.lockInterruptibly();
    try {
      long nanos = TimeUnit.MILLISECONDS.toNanos(timeout);
      while (isFull()){
        if(nanos <= 0) {
          return false;
        }
        nanos = tailWaits.awaitNanos(nanos);
      }
      array[tail] = e;
      if(++tail == array.length){
        tail = 0;
      }
      size.getAndIncrement();
    } finally {
      taillock.unlock();
    }

    headlock.lock();
    try {
      headWaits.signal();
    } finally {
      headlock.unlock();
    }
    return true;
  }

  @Override
  public E poll() throws InterruptedException {
    E e;
    int c;
    headlock.lockInterruptibly();
    try {
      while(isEmpty()){
        headWaits.await();
      }
      e = array[head];
      array[head] = null;
      if(++head == array.length){
        head = 0;
      }
      c = size.getAndDecrement();
      if (c > 1) {
        headWaits.signal();
      }
    } finally {
      headlock.unlock();
    }

    if(c == array.length){
      taillock.lock();
      try {
        tailWaits.signal();
      } finally {
        taillock.unlock();
      }
    }

    return e;
  }
}
