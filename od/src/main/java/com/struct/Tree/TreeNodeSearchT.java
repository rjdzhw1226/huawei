package com.struct.Tree;

public class TreeNodeSearchT<T extends Comparable<T>> {
  T val;
  Object value;
  TreeNodeSearchT<T> left;
  TreeNodeSearchT<T> right;

  public TreeNodeSearchT(T val) {
    this.val = val;
  }

  public TreeNodeSearchT(T val, Object value) {
    this.val = val;
    this.value = value;
  }

  public TreeNodeSearchT(T val, Object value, TreeNodeSearchT<T> left, TreeNodeSearchT<T> right) {
    this.val = val;
    this.value = value;
    this.left = left;
    this.right = right;
  }
}
