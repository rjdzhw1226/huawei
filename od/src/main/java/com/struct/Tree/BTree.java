package com.struct.Tree;

import java.util.Arrays;

public class BTree {
  /*
  * B树的特性
  * 度 孩子数
  * 阶 孩子最大值
  *
  * 每个节点最多有m个孩子 其中m为B树的阶
  * 除根节点和叶子节点外 其它每个节点至少有ceil(m/2)个孩子
  * 若根节点不是叶子节点 则至少有两个孩子
  * 所有叶子节点都在同一层
  * 每个非叶子节点由n个关键字和n+1个指针组成 ceil(m/2) - 1 <= n <= m -1
  * 关键字按非降序排列 即节点中的第i个关键字大于等于第i-1个关键字
  * 指针p[i]指向关键字值位于第i个关键字和第i+1个关键子之间的子树
  * */
  static class Node{
    int[] keys; //关键字
    Node[] children; //孩子
    int keyNumber; //关键字数量
    boolean leaf = true; //是否叶子节点
    int t; //树的最小度数

    public Node(int t) {
      this.t = t;
      this.children = new Node[2*t];
      this.keys = new int[2*t-1];
    }

    @Override
    public String toString() {
      return Arrays.toString(Arrays.copyOfRange(keys,0, keyNumber));
    }

    Node get(int key){
      int i = 0;
      while(i < keyNumber){
        if(keys[i] == key){
          return this;
        }
        if(keys[i] > key){
          break;
        }
        i++;
      }
      if(leaf){
        return null;
      }
      return children[i].get(key);
    }

    void insert(int key, int index){
      /*for (int i = keyNumber - 1; i >= index; i--) {
        keys[i + 1] = keys[i];
      }*/
      System.arraycopy(keys, index, keys,index + 1,keyNumber - index);
      keys[index] = key;
      keyNumber++;
    }

    void insertChild(Node child, int index){
      System.arraycopy(children, index, children, index + 1, keyNumber - index);
      children[index] = child;
    }
  }

  Node root;

  int t;

  final int MinKeyNumber;
  final int MaxKeyNumber;

  public BTree(){
    this(2);
  }

  public BTree(int t){
    this.t = t;
    root = new Node(t);
    MaxKeyNumber = (t << 1) - 1;
    MinKeyNumber = t - 1;
  }

  public boolean contains(int key){
    return root.get(key) != null;
  }

  public void put(int key){
    doPut(root, key);
  }

  private void doPut(Node node, int key){
    int i = 0;
    while(i < node.keyNumber){
      if(node.keys[i] == key){
        return;
      }
      if(node.keys[i] > key){
        break;
      }
      i++;
    }
    if(node.leaf){
      node.insert(key,i);
    } else {
      doPut(node.children[i], key);
    }
  }

  private void split(Node left, Node parent, int index){
    if (parent == null){
      Node newRoot = new Node(t);
      newRoot.leaf = false;
      newRoot.insertChild(left,0);
      this.root = newRoot;
      parent =newRoot;
    }
    Node right = new Node(t);
    right.leaf = left.leaf;
    System.arraycopy(left.keys, t, right.keys, 0, t - 1);
    if(!left.leaf){
      System.arraycopy(left.children,t,right.children,0,t);
    }
    right.keyNumber = t - 1;
    left.keyNumber = t - 1;
    int mid = left.keys[t - 1];
    parent.insert(mid, index);
    parent.insertChild(right, index + 1);
  }






}
