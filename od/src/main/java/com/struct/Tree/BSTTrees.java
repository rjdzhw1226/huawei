package com.struct.Tree;

public class BSTTrees<T extends Comparable<T>>{
  TreeNodeSearchT<T> root;

  public Object get(T key){
    TreeNodeSearchT<T> node = root;
    while (node != null){
      int result = key.compareTo(node.val);
      if (result > 0) {
        node = node.right;
      } else if (result < 0) {
        node = node.left;
      } else {
        return node.value;
      }
    }
    return null;
  }
}
