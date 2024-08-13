package com.suanfa.test;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;
import java.util.stream.Collectors;
//生成哈夫曼树
public class Test25 {
    static class TreeHu implements Comparable<TreeHu>{
        int val;
        TreeHu lef;
        TreeHu rig;

        public TreeHu() {
        }

        public TreeHu(int val) {
            this.val = val;
        }

        public TreeHu(int val, TreeHu lef, TreeHu rig) {
            this.val = val;
            this.lef = lef;
            this.rig = rig;
        }
        public void preOrder() {
            System.out.print(this.val + " ");
            if (this.lef != null) {
                this.lef.preOrder();
            }
            if (this.rig != null) {
                this.rig.preOrder();
            }
        }

        public void midOrder() {
            if (this.lef != null) {
                this.lef.midOrder();
            }
            System.out.print(this.val + " ");
            if (this.rig != null) {
                this.rig.midOrder();
            }
        }


        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("val", val)
                    .append("lef", lef)
                    .append("rig", rig)
                    .toString();
        }

        @Override
        public int compareTo(TreeHu o) {
            return this.val - o.val;
        }
    }

    static List<TreeHu> tree = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        int[] array = new int[i];
        for (int j = 0; j < i; j++) {
            array[j] = in.nextInt();
        }
        Test25 t = new Test25();
        TreeHu treeHu = t.initTree(array);
        treeHu.preOrder();
//        array = Arrays.stream(array).sorted().toArray();
//        huffManTree(array);
//        List<TreeHu> collect = tree.stream().sorted((o1, o2) -> {return o2.val - o1.val;}).collect(Collectors.toList());


    }

    private TreeHu initTree(int[] array) {
        List<TreeHu> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            list.add(new TreeHu(array[i]));
        }

        while (list.size() > 1){
            Collections.sort(list);

            TreeHu lef = list.get(0);
            TreeHu rig = list.get(1);

            TreeHu root = new TreeHu(lef.val + rig.val);

            root.lef = lef;
            root.rig = rig;

            list.add(root);
            list.remove(lef);
            list.remove(rig);
        }
        return list.get(0);
    }

    private static void huffManTree(int[] array) {
        if(array.length <= 1){
            return;
        }
        array = Arrays.stream(array).sorted().toArray();
        int temp = array[0] + array[1];
        tree.add(new TreeHu(temp, new TreeHu(array[0]), new TreeHu(array[1])));
        int[] arrayNew = new int[array.length - 2];
        System.arraycopy(array, 2, arrayNew, 0, arrayNew.length);
        int search = Arrays.binarySearch(arrayNew, temp);
        List<Integer> collect = Arrays.stream(arrayNew).boxed().collect(Collectors.toList());
        if (search < 0) {
            if(search == -1) {
                //搜索值不是数组元素，且小于数组内元素，索引值为 – 1
                collect.add(0, temp);
            } else if (search == -(arrayNew.length + 1)) {
                //搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1)
                int size = collect.size();
                collect.add(size - 1, temp);
            } else {
                //搜索值不是数组元素，且在数组范围内 得 - 插入点索引值 - 1
                collect.add(-(search + 1), temp);
            }
        } else if (search >= 0) {
            //搜索值是数组元素，从0开始计数，得搜索值的索引值
            collect.add(search + 1, temp);
        }
        arrayNew = collect.stream().mapToInt(Integer::intValue).toArray();
        huffManTree(arrayNew);
    }

    //待纠正
    private static TreeHu huffManTree(int[] array, TreeHu tree) {
        if(array.length <= 1){
            System.out.println(tree);
            return null;
        }
        array = Arrays.stream(array).sorted().toArray();
        int temp = array[0] + array[1];
        int[] arrayNew = new int[array.length - 2];
        System.arraycopy(array, 2, arrayNew, 0, arrayNew.length);
        int search = Arrays.binarySearch(arrayNew, temp);
        List<Integer> collect = Arrays.stream(arrayNew).boxed().collect(Collectors.toList());
        if (search < 0) {
            if(search == -1) {
                //搜索值不是数组元素，且小于数组内元素，索引值为 – 1
                collect.add(0, temp);
            } else if (search == -(arrayNew.length + 1)) {
                //搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1)
                int size = collect.size();
                collect.add(size - 1, temp);
            } else {
                //搜索值不是数组元素，且在数组范围内 得 - 插入点索引值 - 1
                collect.add(-(search + 1), temp);
            }
        } else if (search >= 0) {
            //搜索值是数组元素，从0开始计数，得搜索值的索引值
            collect.add(search + 1, temp);
        }
        arrayNew = collect.stream().mapToInt(Integer::intValue).toArray();
        TreeHu node = new TreeHu(temp);
        node.lef = new TreeHu(array[0]);
        if(tree == null){
            node.rig = new TreeHu(array[1]);
        } else {
            node.rig = tree;
        }
        huffManTree(arrayNew, node);
        return tree;
    }
}
