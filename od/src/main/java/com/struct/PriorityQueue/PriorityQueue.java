package com.struct.PriorityQueue;

import com.struct.Queue.QueueMine;

public class PriorityQueue <E extends Priority> implements QueueMine<E> {

  Priority[] array;

  int size;

  public PriorityQueue(int capacity) {
    array = new Priority[capacity];
  }

  /**
   * 队列尾插入值
   * @param value 待插入值
   * @return
   */
  @Override
  public boolean offer(E value) {
    if(isFull()){
      return false;
    }
    array[size] = value;
    size++;
    return true;
  }

  /**
   * 移除队列头的值
   * @return
   */
  @Override
  public E poll() {
    if(isEmpty()){
      return null;
    }
    int max = find();
    E e = (E) array[max];
    remove(max);
    return e;
  }

  private void remove(int index) {
    if(index < size - 1){
      System.arraycopy(array, index + 1, array, index, size - index - 1);
    }
    size--;
    array[size] = null;
  }


  /**
   * 获取队列头的值
   * @return
   */
  @Override
  public E peek() {
    if(isEmpty()){
      return null;
    }
    int max = find();
    return (E) array[max];
  }

  /**
   * 判空
   * @return
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 判满
   * @return
   */
  @Override
  public boolean isFull() {
    return size == array.length;
  }

  public int find(){
    int m = 0;
    int i = 1;
    for (int j = 0; j < size; j++) {
      if(array[i].priority() > array[m].priority()){
        m = i;
        if(i < size){
          i++;
        }
      }
    }
    return m;
  }

  public void insert(E value){
    int i = size - 1;
    while (i >= 0 && array[i].priority() < value.priority()) {
      array[i + 1] = array[i];
      i--;
    }
    array[i + 1] = value;
  }
}
