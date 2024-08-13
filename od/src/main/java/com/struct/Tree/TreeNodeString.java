package com.struct.Tree;

public class TreeNodeString {
  public String val;
  public TreeNodeString left;
  public TreeNodeString right;

  public TreeNodeString() {
  }

  public TreeNodeString(String val) {
    this.val = val;
  }

  public TreeNodeString(String val, TreeNodeString left, TreeNodeString right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
