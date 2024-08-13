package com.struct.PriorityQueue;

import com.struct.Queue.QueueMine;

public class PriorityQueue2 <E extends Priority> implements QueueMine<E> {

  Priority[] array;

  int size;

  public PriorityQueue2(int capacity) {
    array = new Priority[capacity];
  }

  /**
   * 队列尾插入值
   * 入堆新元素，加入到数组末尾child
   * 不断比较新加元素和父节点的优先级
   * 如果父节点优先级低 则向下移动 找到下一个parent
   * 直至父节点优先级更高或child等于0
   * @param value 待插入值
   * @return
   */
  @Override
  public boolean offer(E value) {
    if(isFull()){
      return false;
    }
    int child = size++;
    int parent = (child - 1) / 2;
    while(child > 0 && value.priority() > array[parent].priority()){
      array[child] = array[parent];
      child = parent;
      parent = (child - 1) / 2;
    }
    array[child] = value;
    return true;
  }

  /**
   * 移除队列头的值
   *
   * @return
   */
  @Override
  public E poll() {
    if(isEmpty()){
      return null;
    }
    swap(0, size - 1);
    size--;
    Priority e = array[size];
    array[size] = null;
    drop(0);
    return (E) e;
  }

  private void drop(int parent){
    int left = 2 * parent + 1;
    int right = left + 1;
    int max = parent;
    if(left > 0 && array[left].priority() > array[max].priority()){
      max = left;
    }
    if(right > 0 && array[right].priority() > array[max].priority()){
      max = right;
    }
    if(max != parent){
      swap(max, parent);
      drop(max);
    }
  }

  private void swap(int i, int j){
    Priority t = array[i];
    array[i] = array[j];
    array[j] = t;
  }

  /**
   * 获取队列头的值
   *
   * @return
   */
  @Override
  public E peek() {
    if(isEmpty()){
      return null;
    }
    return (E) array[0];
  }

  /**
   * 判空
   *
   * @return
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 判满
   *
   * @return
   */
  @Override
  public boolean isFull() {
    return size == array.length;
  }
}
