package com.struct.Tree;

public class tree {
  node root;

  public node find(int id){
    node currentNode = root;
    while(currentNode.iData != id){
      if(currentNode == null){
        return null;
      }
      if(id < currentNode.iData){
        currentNode = root.leftChild;
      }else {
        currentNode = root.rightChild;
      }
    }
    return currentNode;
  }

  public void insert(int id, double dd){
    node nod = new node();
    nod.iData =id;
    nod.fData =dd;
    if(root == null){
      root = nod;
    }else {
      node currentNode = root;
      node parent;
      while(true){
        parent = currentNode;
        //左子树
        if(id < currentNode.iData){
          currentNode = currentNode.getLeftChild();
          if(currentNode == null){
            parent.leftChild = nod;
            return;
          }
        }else {
          currentNode = currentNode.getRightChild();
          if(currentNode == null){
            parent.rightChild = nod;
            return;
          }
        }
      }
    }
  }

  private static void inOrder(node RootNode){
    if(RootNode != null){
      inOrder(RootNode.leftChild);
      System.out.println(RootNode.iData + "");
      inOrder(RootNode.rightChild);
    }
  }

  private static void beforeOrder(node RootNode){
    if(RootNode != null){
      System.out.println(RootNode.iData + "");
      beforeOrder(RootNode.leftChild);
      beforeOrder(RootNode.rightChild);
    }
  }

  private static void backOrder(node RootNode){
    if(RootNode != null){
      backOrder(RootNode.leftChild);
      backOrder(RootNode.rightChild);
      System.out.println(RootNode.iData + "");
    }
  }

  public node minNum(){
    node current, last = null;
    current = root;
    while (current != null){
      last = current;
      current = current.leftChild;
    }
    return last;
  }

  public node maxNum(){
    node current, last = null;
    current = root;
    while (current != null){
      last = current;
      current = current.rightChild;
    }
    return last;
  }

  public boolean delete(int key){
    node current = root;
    node parent = root;
    boolean isLeftChild = true;

    while(current.iData != key){
      parent = current;
      if(key < current.iData){
        isLeftChild = true;
        current = current.leftChild;
      }else{
        isLeftChild = false;
        current = current.rightChild;
      }
      if(current == null){
        return false;
      }
    }

    if(current.leftChild == null && current.rightChild == null){
      if(current == root){
        root = null;
      }else if(isLeftChild){
        parent.leftChild = null;
      } else {
        parent.rightChild = null;
      }
    } else if(current.rightChild == null){
      if(current == root){
        root = current.leftChild;
      } else if(isLeftChild){
        parent.leftChild = null;
      } else {
        parent.rightChild = null;
      }
    } else {
      node successor = getSuccessor(current);
      if(current == root){
        root = successor;
      } else if(isLeftChild){
        parent.leftChild = successor;
      } else {
        parent.rightChild = successor;
      }
      successor.leftChild = current.leftChild;
    }
    return true;
  }

  private node getSuccessor(node delNode){
    node successorParent = delNode;
    node successor = delNode;
    node current = delNode.rightChild;
    while(current != null){
      successorParent =successor;
      successor =current;
      current =current.leftChild;
    }
    if(successor != delNode.rightChild){
      successorParent.leftChild = successor.rightChild;
      successor.rightChild = delNode.rightChild;
    }
    return successor;
  }

}
