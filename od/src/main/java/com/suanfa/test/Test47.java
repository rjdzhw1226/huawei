package com.suanfa.test;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//最富裕的小家庭 树
public class Test47 {
    static class TreeNodeFamily {
        int key;
        int val;
        List<TreeNodeFamily> next;

        public TreeNodeFamily() {
        }

        public TreeNodeFamily(int key, int val, List<TreeNodeFamily> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public List<TreeNodeFamily> getNext() {
            return next;
        }

        public void setNext(List<TreeNodeFamily> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                    .append("key", key)
                    .append("val", val)
                    .append("next", next)
                    .toString();
        }

        public static TreeNodeFamily find(int key, TreeNodeFamily root){
            TreeNodeFamily newNode = new TreeNodeFamily();
            if (root != null){
                if(root.key == key){
                    return root;
                }
                if(root.next.size() > 0){
                    for (TreeNodeFamily family : root.next) {
                        if(key == family.key){
                            newNode = family;
                            return newNode;
                        } else {
                            find(key, family);
                        }
                    }
                }
            }
            return newNode;
        }

    }
    //题目描述
    //在一颗树中，每个节点代表一个家庭成员，节点的数字表示其个人的财富值，
    // 一个节点及其直接相连的子节点被定义为一个小家庭现给你一颗树，请计算出最富裕的小家庭的财富和。
    //输入描述
    //第一行为一个数 N，表示成员总数，成员编号 1~N。1≤N≤1000第二行为 N 个空格分隔的数，表示编号 1~N 的成员的财富值。
    // 0≤财富值≤1000000接下来 N -1 行，每行两个空格分隔的整数(N1, N2)，表示 N1 是 N2 的 父节点
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        String[] strings = new String[count];
        for (int i = 0; i < count; i++) {
            int j = in.nextInt();
            strings[i] = String.valueOf(j);
        }
        boolean flag = false;
        TreeNodeFamily family = null;
        while (in.hasNext()) {
            if(!flag){
                flag = true;
                int father = in.nextInt();
                int child = in.nextInt();
                List<TreeNodeFamily> list = new ArrayList<>();
                list.add(new TreeNodeFamily(child, Integer.parseInt(strings[child - 1]), new ArrayList<TreeNodeFamily>()));
                family = new TreeNodeFamily(father, Integer.parseInt(strings[father - 1]), list);
                if(child == count){
                    break;
                }
            } else {
                int father = in.nextInt();
                int child = in.nextInt();
                TreeNodeFamily.find(father, family).next.add(new TreeNodeFamily(child, Integer.parseInt(strings[child - 1]), new ArrayList<TreeNodeFamily>()));
                System.out.println(family);
                if(child == count){
                    break;
                }
            }
        }
        //从树里找相加最大的值
        if(family != null){
            findMaxValue(family);
            findNext(family);
        }
        System.out.println(res.stream().max((o1, o2) -> o1 - o2));
    }

    static List<Integer> res = new ArrayList<>();

    private static void findNext(TreeNodeFamily root) {
        for (TreeNodeFamily family : root.next) {
            findMaxValue(family);
            findNext(family);
        }
    }

    private static void findMaxValue(TreeNodeFamily family) {
        List<Integer> ans = new ArrayList<>();
        ans.add(family.getVal());
        if(family.next.size() > 0){
            int k = 0;
            while (k < family.next.size()) {
                ans.add(family.next.get(k).getVal());
                k++;
            }
        }
        res.add(ans.stream().mapToInt(e -> e).sum());
    }
}
