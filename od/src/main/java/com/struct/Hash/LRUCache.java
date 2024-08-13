package com.struct.Hash;

class LRUCache {
  class Node{
    int hash;
    int key;
    int value;
    Node next;

    public Node(int hash, int key, int value, Node next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
  int size = 0;
  Node[] queue = new Node[size];

  public LRUCache(int capacity) {
    queue = new Node[capacity];
  }

  public int getHash(int key){
    return key & (queue.length - 1);
  }

  public int get(int key) {
    if(queue.length == 0){
      return -1;
    } else {
      if(queue[getHash(key)] == null){
        return -1;
      }
      Node node = queue[getHash(key)];
      Node prev = null;
      while (node != null){
        if(node.key == key){
          int value = node.value;
          if(node.next != null){
            if(prev == null){
              queue[getHash(key)] = node.next;
            } else {

            }
          }
          return value;

        }
        prev = node;
      }
    }
    return 0;
  }

  public void put(int key, int value) {

  }

}
