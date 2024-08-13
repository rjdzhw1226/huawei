package com.suanfa.leetcode;

import java.util.Hashtable;

public class LRU {
  class DoubleNode {
    int key;
    int value;
    DoubleNode pre;
    DoubleNode next;

    public DoubleNode() {
    }

    public DoubleNode(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  private Hashtable<Integer, DoubleNode> hashCache = new Hashtable<>();
  private int size;
  private int capacity;

  private DoubleNode head;
  private DoubleNode tail;

  public LRU(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    head = new DoubleNode();
    tail = new DoubleNode();
    head.next = tail;
    tail.pre = head;
  }

  public int get(int key){
    DoubleNode node = hashCache.get(key);
    if (node == null){
      return -1;
    }
    moveToHead(node);
    return node.value;
  }

  public void put(int key, int value){
    DoubleNode node = hashCache.get(key);
    if(node == null){
      DoubleNode newNode = new DoubleNode(key, value);
      hashCache.put(key, newNode);
      addToHead(newNode);
      ++size;
      if(size > capacity){
        DoubleNode tail1 = removeTail();
        hashCache.remove(tail1.key);
        --size;
      }
    } else {
      node.value = value;
      moveToHead(node);
    }
  }

  private void addToHead(DoubleNode node){
    node.pre = head;
    node.next = head.next;
    head.next.pre = node;
    head.next = node;
  }

  private void removeNode(DoubleNode node){
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  private void moveToHead(DoubleNode node) {
    removeNode(node);
    addToHead(node);
  }

  private DoubleNode removeTail(){
    DoubleNode res = tail.pre;
    removeNode(res);
    return res;
  }
}
