package com.struct.Tree;

public class TreeNodeSearch {
  int val;
  Object value;
  TreeNodeSearch left;
  TreeNodeSearch right;

  public TreeNodeSearch(Object value) {
    this.value = value;
  }

  public TreeNodeSearch(int val, Object value) {
    this.val = val;
    this.value = value;
  }

  public TreeNodeSearch(int val, Object value, TreeNodeSearch left, TreeNodeSearch right) {
    this.val = val;
    this.value = value;
    this.left = left;
    this.right = right;
  }


}
