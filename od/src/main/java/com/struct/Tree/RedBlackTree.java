package com.struct.Tree;

public class RedBlackTree {
  enum Color{
    RED, BLACK
  }

  private Node root;

  private static class Node{
    int key;
    Object value;
    Node left;
    Node right;
    Node parent;
    Color color = Color.RED;

    public Node(int key) {
      this.key = key;
    }

    public Node(int key, Color color) {
      this.key = key;
      this.color = color;
    }

    public Node(int key, Object value) {
      this.key = key;
      this.value = value;
    }

    public Node(int key, Color color, Node left, Node right) {
      this.key = key;
      this.color = color;
      this.left = left;
      this.right = right;
      if (left != null) {
        left.parent = this;
      }
      if (right != null) {
        right.parent = this;
      }
    }

    boolean isLeftChild(){
      return parent != null && parent.left == this;
    }

    Node uncle(){
      if(parent == null || parent.parent == null){
        return null;
      }
      if(parent.isLeftChild()){
        return parent.parent.right;
      } else {
        return parent.parent.left;
      }
    }

    Node sibling(){
      if(parent == null){
        return null;
      }
      if(this.isLeftChild()){
        return parent.right;
      } else {
        return parent.left;
      }
    }
  }

  boolean isRed(Node node){
    return node != null && node.color == Color.RED;
  }

  boolean isBlack(Node node){
    return node == null || node.color == Color.BLACK;
  }

  private void rightRotate(Node node){
    Node parent = node.parent;
    Node yellow = node.left;
    Node green = node.right;
    if(green != null){
      green.parent = node;
    }
    yellow.parent = parent;
    yellow.right = node;
    node.left = green;
    node.parent = yellow;
    if(parent == null){
      root = yellow;
    } else if(parent.left == node){
      parent.left = yellow;
    } else {
      parent.right = yellow;
    }

  }

  private void leftRotate(Node node){
    Node parent = node.parent;
    Node yellow = node.right;
    Node green = yellow.left;
    if (green != null) {
      green.parent = node;
    }
    yellow.left = node;
    yellow.parent = parent;
    node.right = green;
    node.parent = yellow;
    if (parent == null) {
      root = yellow;
    } else if (parent.left == node) {
      parent.left = yellow;
    } else {
      parent.right = yellow;
    }
  }

  public void put(int key, Object value){
    Node p = root;
    Node parent = null;
    while(p != null){
      if(key < p.key){
        p = p.left;
      } else if(p.key < key){
        p = p.right;
      } else {
        p.value = value;
        return;
      }
    }
    Node inserted = new Node(key, value);
    if(parent == null){
      root = inserted;
    } else if(key < parent.key){
      parent.left = inserted;
      inserted.parent = parent;
    } else {
      parent.right = inserted;
      inserted.parent = parent;
    }
    fixRedRed(inserted);
  }
  void fixRedRed(Node x){
    if(x == root){
      x.color = Color.BLACK;
      return;
    }

    if(isBlack(x.parent)){
      return;
    }

    Node parent = x.parent;
    Node uncle = x.uncle();
    Node grandparent = parent.parent;
    if(isRed(uncle)){
      parent.color = Color.BLACK;
      uncle.color = Color.BLACK;
      grandparent.color = Color.RED;
      fixRedRed(grandparent);
      return;
    }

    if (parent.isLeftChild() && x.isLeftChild()){
      parent.color = Color.BLACK;
      grandparent.color = Color.RED;
      rightRotate(grandparent);
    } else if(parent.isLeftChild()){
      leftRotate(parent);
      x.color = Color.BLACK;
      grandparent.color = Color.RED;
      rightRotate(grandparent);
    } else if(!x.isLeftChild()){
      parent.color = Color.BLACK;
      grandparent.color = Color.RED;
      leftRotate(grandparent);
    } else {
      rightRotate(parent);
      x.color = Color.BLACK;
      grandparent.color = Color.RED;
      leftRotate(grandparent);
    }

  }

  public Node find(int key){
    Node p = root;
    while(p != null){
      if(key < p.key){
        p = p.left;
      } else if(p.key < key){
        p = p.right;
      } else {
        return p;
      }
    }
    return null;
  }

  Node findReplace(Node deleted){
    if(deleted.left == null && deleted.right == null){
      return null;
    }
    if(deleted.left == null){
      return deleted.right;
    }
    if(deleted.right == null){
      return deleted.left;
    }
    Node s = deleted.right;
    while(s.left != null){
      s= s.left;
    }
    return s;
  }

  public void remove(int key){

  }





}
