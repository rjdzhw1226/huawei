package com.suanfa.gpthelp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {12, 5, 6, 21};
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root = huffmanTree.creTree(arr);
        huffmanTree.preOrder(root);
    }
}

class HuffmanTree {

    public Node creTree(int[] aArr) {

        List<Node> list = new ArrayList<>(); //用于存放数组元素

        //将数组放存放list中
        for (int element : aArr) {
            list.add(new Node(element));
        }

        while (list.size() > 1) { //循环创建树
            Collections.sort(list); //从小到大排序

            //从list中从小取出两个节点
            Node left = list.get(0);
            Node right = list.get(1);

            //初始化小树根节点
            Node root = new Node(left.getElement() + right.getElement()); //小树根节点为左右子树节点element值的和

            //构建小树
            root.setLeft(left);
            root.setRight(right);

            list.add(root); //将小树根节点再次添加到list中
            //移除集合中已经参与构建过树的节点
            list.remove(left);
            list.remove(right);

//            list.remove(0);
//            list.remove(0);  //取出两个队头元素 也可

        }
        return list.get(0);
    }

    //前序遍历
    public void preOrder(Node aRoot) {
        if (aRoot != null) {
            aRoot.preOrder();
        } else {
            System.out.println("此树为空, 无法完成前序遍历！");
        }
    }
}

class Node implements Comparable<Node> {
    private int element; //节点的权
    private Node left; //节点的左子树
    private Node right; //节点的右子树

    //构造器
    public Node(int aElement) {
        this.element = aElement;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    //前序遍历
    public void preOrder() {
        System.out.print(this + " ");
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    @Override
    public String toString() {
        return element + "";
    }


    @Override
    public int compareTo(Node o) {
        return this.getElement() - o.getElement(); //从小大到排序
    }
}

