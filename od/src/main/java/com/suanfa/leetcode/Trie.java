package com.suanfa.leetcode;

//前缀树
class Trie {
  static class Node{
    Node[] children;     // 子节点列表
    boolean isEnd;       // 标记是否尾节点

    public Node(){
      children = new Node[26];
      isEnd = false;
    }
  }

  private Node root;

  public Trie() {
    root = new Node();
  }

  public void insert(String word) {
    Node node = root;
    int n = word.length();
    for (int i = 0; i < n; i++) {
      int id = word.charAt(i) - 'a';
      if(node.children[id] == null){
        node.children[id] = new Node();
      }
      node = node.children[id];
    }
    node.isEnd = true;
  }

  public boolean search(String word) {
    Node node = searchPrefix(word);
    return node != null && node.isEnd;
  }

  public boolean startsWith(String prefix) {
    return searchPrefix(prefix) != null;
  }

  private Node searchPrefix(String word){
    Node node = root;
    int n = word.length();
    for(int i = 0; i < n; i++){
      node = node.children[word.charAt(i) - 'a'];
      if(node == null){
        return null;
      }
    }
    return node;
  }
}
