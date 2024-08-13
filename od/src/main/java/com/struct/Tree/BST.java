package com.struct.Tree;

public class BST {
  TreeNodeSearch root;

  public BST() {
  }

  public BST(TreeNodeSearch root) {
    this.root = root;
  }

  /**
   *
   * <h3>二叉搜索树递归Get方法</h3>
   * @param key 根据值获取节点
   * @return {@link com.struct.Tree.TreeNodeSearch#value}
   */
  public Object get(int key){
    return doGet(root, key);
  }

  /**
   * <h3>二叉搜索树非递归Get方法</h3>
   * @param key 根据值获取节点
   * @return {@link com.struct.Tree.TreeNodeSearch#value}
   */
  public Object getDoNotRecursion(int key){
    TreeNodeSearch node = root;
    while (node != null){
      if(key < node.val){
        node = node.left;
      } else if (key > node.val) {
        node = node.right;
      } else {
        return node.value;
      }
    }
    return null;
  }

  private Object doGet(TreeNodeSearch node, int key){
    if(node == null){
      return null;
    }
    if (key < node.val) {
      return doGet(node.left, key);
    } else if (key > node.val) {
      return doGet(node.right, key);
    } else {
      return node.value;
    }
  }

  /**
   * <h3>二叉搜索树最小值方法</h3>
   * @return {@link com.struct.Tree.TreeNodeSearch#value}
   */
  public Object min(){
    return domin(root);
  }
  /**
   * <h3>二叉搜索树非递归最小值方法</h3>
   * @return {@link com.struct.Tree.TreeNodeSearch#value}
   */
  public Object minDoNotRecursion(){
    if(root == null){
      return null;
    }
    TreeNodeSearch node = root;
    while (node.left != null){
      node = node.left;
    }
    return node.value;
  }

  private Object domin(TreeNodeSearch node){
    if(node == null){
      return null;
    }
    if(node.left == null){
      return node.value;
    }
    return domin(node.left);
  }
  /**
   * <h3>二叉搜索树最大值方法</h3>
   * @return {@link com.struct.Tree.TreeNodeSearch#value}
   */
  public Object max(){
    return domax(root);
  }

  private Object domax(TreeNodeSearch node) {
    if(node == null){
      return null;
    }
    if(node.right == null){
      return node.value;
    }
    return domax(node.right);
  }

  /**
   * <h3>存储关键字和对应值</h3>
   * @param key 键
   * @param value 值
   */
  public void put(int key, Object value){
    TreeNodeSearch node = root;
    TreeNodeSearch parent = null;
    while (node != null){
      parent = node;
      if(key < node.val){
        node = node.left;
      } else if (node.val < key) {
        node = node.right;
      } else {
        node.value = value;
        return;
      }
    }
    if(parent == null){
      root = new TreeNodeSearch(key, value);
      return;
    }
    TreeNodeSearch nodeSearch = new TreeNodeSearch(key, value);
    if(nodeSearch.val > parent.val){
      parent.right = nodeSearch;
    } else if (parent.val > nodeSearch.val) {
      parent.left = nodeSearch;
    }
  }

  /**
   * <h3>二叉搜索树的前任值</h3>
   * @param key 键
   * @return {@link com.struct.Tree.TreeNodeSearch#value}
   */
  public Object successor(int key){
    TreeNodeSearch node = root;
    while (node != null){
      if(key < node.val){
        node = node.left;
      } else if (node.val < key) {
        node = node.right;
      } else {
        break;
      }
    }

    if(node == null){
      return null;
    }
    if(node.left != null){
      return max(node.left);
    } else {

    }
    return null;
  }

  private Object max(TreeNodeSearch node) {
    if(node == null){
      return null;
    }
    TreeNodeSearch p = node;
    while (p.right != null){
      p = p.right;
    }
    return p.value;
  }

  public Object predecessor(int key){

    return null;
  }

  public Object delete(int key){

    return null;
  }
}
