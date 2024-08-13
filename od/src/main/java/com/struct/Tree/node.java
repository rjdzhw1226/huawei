package com.struct.Tree;

/**
 * 线性表 队列 栈 链表 数组 一对一
 * 树 b树 b+树 红黑树 avl树 二叉树 二叉搜索树 一对多
 * 图 无向图 有向图 多对多
 * 完全无向图 n(n - 1) / 2 条边
 * 完全无向有向图 n(n - 1) 条边
 * 图的存储 邻接矩阵、邻接表、逆邻接表和十字链表 逆邻接表 是有向图别的顶点指向顶点的表示
 */

public class node {
  int iData;
  double fData;
  node leftChild;
  node rightChild;

  public int getiData() {
    return iData;
  }

  public void setiData(int iData) {
    this.iData = iData;
  }

  public double getfData() {
    return fData;
  }

  public void setfData(double fData) {
    this.fData = fData;
  }

  public node getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(node leftChild) {
    this.leftChild = leftChild;
  }

  public node getRightChild() {
    return rightChild;
  }

  public void setRightChild(node rightChild) {
    this.rightChild = rightChild;
  }
}
