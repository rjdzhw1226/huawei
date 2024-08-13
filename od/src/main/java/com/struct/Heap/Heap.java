package com.struct.Heap;

import java.util.Arrays;

public class Heap {
  int[] array;
  int size;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Heap(int capacity){
    this.array = new int[capacity];
  }

  public Heap(int[] nums){
    this.array = nums;
    this.size = nums.length;
    heapify();
  }

  /**
   * 堆排序
   * @param args
   */
  public static void main(String[] args) {
    int[] array = {2, 3, 4, 7, 1, 5, 13, 10, 9, 6};
    Heap hp = new Heap(array);
    System.out.println(Arrays.toString(hp.array));
    while(hp.size > 1){
      hp.swap(0, hp.size - 1);
      hp.size--;
      hp.down(0);
    }

    System.out.println(Arrays.toString(hp.array));
  }
  /**
   * 建堆算法
   */
  private void heapify(){
    //找最后一个非叶子节点 索引0为起点
    int index = (size >> 1) - 1;
    for (int i = index; i >= 0; i--) {
      down(i);
    }
  }

  /**
   * 下潜方法
   * @param i 索引
   */
  private void down(int i) {
    int left = (i << 1) + 1;
    int right = left + 1;
    int max = i;
    if (left < size && array[left] > array[max]) {
      max = left;
    }
    if (right < size && array[right] > array[max]) {
      max = right;
    }
    if(max != i){
      swap(max,i);
      down(max);
    }
  }

  /**
   * 下潜方法(非递归)
   * @param i 索引
   */
  private void downUnR(int i){
    while(true) {
      int left = (i << 1) + 1;
      int right = left + 1;
      int max = i;
      if (left < size && array[left] > array[max]) {
        max = left;
      }
      if (right < size && array[right] > array[max]) {
        max = right;
      }
      if(max == i){
        break;
      }
      swap(max,i);
      i = max;
    }
  }

  /**
   * 上浮方法
   * @param i 值
   */
  public void up(int i){
    int child = size;
    while (child > 0){
      int p = (child - 1) >> 1;
      if(i > array[p]){
        array[child] = array[p];
      } else {
        break;
      }
      child = p;
    }
    array[child] = i;
  }

  private void swap(int max, int i) {
    int temp = array[max];
    array[max] = array[i];
    array[i] = temp;
  }

  public int peek(){
    return array[new Integer(0)];
  }

  public int poll(){
    int top = array[0];
    swap(0,size - 1);
    size--;
    down(0);
    return top;
  }

  public int poll(int index){
    int top = array[index];
    swap(index, size - 1);
    size--;
    down(index);
    return top;
  }

  public void replace(int replaced){
    array[0] = replaced;
    down(0);
  }

  public boolean offer(int offered){
    if(size == array.length){
      return false;
    }
    up(offered);
    size++;
    return true;
  }
}
