package com.suanfa.test;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test09 {

    static class Node {
        int type; // 0 白 1 黑 9 气
        boolean flag; //有没有棋子

        public Node() {
            this.type = 9;
            this.flag = false;
        }

        public Node(int type) {
            this.type = type;
            this.flag = false;
        }

        public Node(int type, boolean flag) {
            this.type = type;
            this.flag = flag;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                    .append(type == 1 ? "🖤" : "🤍")
                    .toString();
        }
    }
    //围棋的气
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<List<Integer>> resB = new ArrayList<>();//黑棋位置
        List<List<Integer>> resW = new ArrayList<>();//白棋位置
        int count = 2;
        int kb = 0;
        int kw = 0;
        String[] black = in.nextLine().split(" ");
        String[] withe = in.nextLine().split(" ");
        if (black.length % 2 != 0 && withe.length % 2 != 0) {
            return;
        }
        while (kb < black.length - 1) {
            List<Integer> ansB = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                ansB.add(Integer.parseInt(black[kb + i]));
            }
            resB.add(ansB);
            kb += 2;
        }
        while (kw < withe.length - 1) {
            List<Integer> ansW = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                ansW.add(Integer.parseInt(withe[kw + i]));
            }
            resW.add(ansW);
            kw += 2;
        }
        Node[][] init = init(resB, resW);
        for (int i = 0; i < init.length; i++) {
            for (int j = 0; j < init[0].length; j++) {
                System.out.printf("%s ", !init[i][j].flag ? "🧡" : init[i][j]);
            }
            System.out.println();
        }
        findTarget(init);
    }

    private static Node[][] init(List<List<Integer>> black, List<List<Integer>> withe){
        Node[][] nodes = new Node[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                for (int k = 0; k < black.size(); k++) {
                    if(black.get(k).get(0) == i && black.get(k).get(1) == j) {
                        nodes[i][j] = new Node(1, true);
                    }
                }
                for (int k = 0; k < withe.size(); k++) {
                    if(withe.get(k).get(0) == i && withe.get(k).get(1) == j) {
                        nodes[i][j] = new Node(0, true);
                    }
                }
            }
        }
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if(nodes[i][j] == null) {
                    nodes[i][j] = new Node();
                }
            }
        }
        return nodes;
    }

    static int countB = 0;
    static int countW = 0;
    private static void findTarget(Node[][] nodes){
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                Node node = nodes[i][j];
                if(node.flag && node.type == 1){
                    //同时判断重复
                    //判断角落 2
                    if(i == 0 && j == 0) {
                        if(nodes[0][1].type != 1 && !nodes[0][1].flag) {
                            countB++;
                            nodes[0][1] = new Node(1);
                        }
                        if(nodes[1][0].type != 1 && !nodes[1][0].flag) {
                            countB++;
                            nodes[1][0] = new Node(1);
                        }
                    }
                    else if(i == 18 && j == 0) {
                        if(nodes[17][0].type != 1 && !nodes[17][0].flag) {
                            countB++;
                            nodes[17][0] = new Node(1);
                        }
                        if(nodes[18][1].type != 1 && !nodes[18][1].flag) {
                            countB++;
                            nodes[18][1] = new Node(1);
                        }
                    }
                    else if(i == 0 && j == 18) {
                        if(nodes[0][17].type != 1 && !nodes[0][17].flag) {
                            countB++;
                            nodes[0][17] = new Node(1);
                        }
                        if(nodes[1][18].type != 1 && !nodes[1][18].flag) {
                            countB++;
                            nodes[1][18] = new Node(1);
                        }
                    }
                    else if(i == 18 && j == 18) {
                        if(nodes[18][17].type != 1 && !nodes[18][17].flag) {
                            countB++;
                            nodes[18][17] = new Node(1);
                        }
                        if(nodes[17][18].type != 1 && !nodes[17][18].flag) {
                            countB++;
                            nodes[17][18] = new Node(1);
                        }
                    }
                    else {
                        //判断边上 3
                        if ((i == 0 && j != 0 && j != 18) || (i == 18 && j != 0 && j != 18)  || (j == 0 && i != 0 && i == 18) || (j == 18 && i != 0 && i != 18)) {
                            if (i == 0) {
                                if (nodes[i + 1][j].type != 1 && !nodes[i + 1][j].flag) {
                                    countB++;
                                    nodes[i + 1][j] = new Node(1);
                                }
                                if (nodes[i][j - 1].type != 1 && !nodes[i][j - 1].flag) {
                                    countB++;
                                    nodes[i][j - 1] = new Node(1);
                                }
                                if (nodes[i][j + 1].type != 1 && !nodes[i][j + 1].flag) {
                                    countB++;
                                    nodes[i][j + 1] = new Node(1);
                                }
                            }
                            if(i == 18) {
                                if (nodes[i - 1][j].type != 1 && !nodes[i - 1][j].flag) {
                                    countB++;
                                    nodes[i - 1][j] = new Node(1);
                                }
                                if (nodes[i][j - 1].type != 1 && !nodes[i][j - 1].flag) {
                                    countB++;
                                    nodes[i][j - 1] = new Node(1);
                                }
                                if (nodes[i][j + 1].type != 1 && !nodes[i][j + 1].flag) {
                                    countB++;
                                    nodes[i][j + 1] = new Node(1);
                                }
                            }
                            if (j == 0) {
                                if (nodes[i][j + 1].type != 1 && !nodes[i][j + 1].flag) {
                                    countB++;
                                    nodes[i][j + 1] = new Node(1);
                                }
                                if (nodes[i - 1][j].type != 1 && !nodes[i - 1][j].flag) {
                                    countB++;
                                    nodes[i - 1][j] = new Node(1);
                                }
                                if (nodes[i + 1][j].type != 1 && !nodes[i + 1][j].flag) {
                                    countB++;
                                    nodes[i + 1][j] = new Node(1);
                                }
                            }
                            if (j == 18) {
                                if (nodes[i][j - 1].type != 1 && !nodes[i][j - 1].flag) {
                                    countB++;
                                    nodes[i][j - 1] = new Node(1);
                                }
                                if (nodes[i - 1][j].type != 1 && !nodes[i - 1][j].flag) {
                                    countB++;
                                    nodes[i - 1][j] = new Node(1);
                                }
                                if (nodes[i + 1][j].type != 1 && !nodes[i + 1][j].flag) {
                                    countB++;
                                    nodes[i + 1][j] = new Node(1);
                                }
                            }
                        }
                        else {
                            //判断中间 4
                            if(nodes[i + 1][j].type != 1 && !nodes[i + 1][j].flag) {
                                countB++;
                                nodes[i + 1][j] = new Node(1);
                            }
                            if (nodes[i][j + 1].type != 1 && !nodes[i][j + 1].flag) {
                                countB++;
                                nodes[i][j + 1] = new Node(1);
                            }
                            if (nodes[i - 1][j].type != 1 && !nodes[i - 1][j].flag) {
                                countB++;
                                nodes[i - 1][j] = new Node(1);
                            }
                            if (nodes[i][j - 1].type != 1 && !nodes[i][j - 1].flag) {
                                countB++;
                                nodes[i][j - 1] = new Node(1);
                            }
                        }
                    }

                }
                if(node.flag && node.type == 0){
                    //同时判断重复
                    //判断角落 2
                    if(i == 0 && j == 0) {
                        if(nodes[0][1].type != 0 && !nodes[0][1].flag) {
                            countW++;
                            nodes[0][1] = new Node(0);
                        }
                        if(nodes[1][0].type != 0 && !nodes[1][0].flag) {
                            countW++;
                            nodes[1][0] = new Node(0);
                        }
                    }
                    else if(i == 18 && j == 0) {
                        if(nodes[17][0].type != 0 && !nodes[17][0].flag) {
                            countW++;
                            nodes[17][0] = new Node(0);
                        }
                        if(nodes[18][1].type != 0 && !nodes[18][1].flag) {
                            countW++;
                            nodes[18][1] = new Node(0);
                        }
                    }
                    else if(i == 0 && j == 18) {
                        if(nodes[0][17].type != 0 && !nodes[0][17].flag) {
                            countW++;
                            nodes[0][17] = new Node(0);
                        }
                        if(nodes[1][18].type != 0 && !nodes[1][18].flag) {
                            countW++;
                            nodes[1][18] = new Node(0);
                        }
                    }
                    else if(i == 18 && j == 18) {
                        if(nodes[18][17].type != 0 && !nodes[18][17].flag) {
                            countW++;
                            nodes[18][17] = new Node(0);
                        }
                        if(nodes[17][18].type != 0 && !nodes[17][18].flag) {
                            countW++;
                            nodes[17][18] = new Node(0);
                        }
                    }
                    else {
                        //判断边上 3
                        if ((i == 0 && j != 0 && j != 18) || (i == 18 && j != 0 && j != 18)  || (j == 0 && i != 0 && i != 18) || (j == 18 && i != 0 && i != 18)) {
                            if (i == 0) {
                                if (nodes[i + 1][j].type != 0 && !nodes[i + 1][j].flag) {
                                    countW++;
                                    nodes[i + 1][j] = new Node(0);
                                }
                                if (nodes[i][j - 1].type != 0 && !nodes[i][j - 1].flag) {
                                    countW++;
                                    nodes[i][j - 1] = new Node(0);
                                }
                                if (nodes[i][j + 1].type != 0 && !nodes[i][j + 1].flag) {
                                    countW++;
                                    nodes[i][j + 1] = new Node(0);
                                }
                            }
                            if(i == 18) {
                                if (nodes[i - 1][j].type != 0 && !nodes[i - 1][j].flag) {
                                    countW++;
                                    nodes[i - 1][j] = new Node(0);
                                }
                                if (nodes[i][j - 1].type != 0 && !nodes[i][j - 1].flag) {
                                    countW++;
                                    nodes[i][j - 1] = new Node(0);
                                }
                                if (nodes[i][j + 1].type != 0 && !nodes[i][j + 1].flag) {
                                    countW++;
                                    nodes[i][j + 1] = new Node(0);
                                }
                            }
                            if (j == 0) {
                                if (nodes[i][j + 1].type != 0 && !nodes[i][j + 1].flag) {
                                    countW++;
                                    nodes[i][j + 1] = new Node(0);
                                }
                                if (nodes[i - 1][j].type != 0 && !nodes[i - 1][j].flag) {
                                    countW++;
                                    nodes[i - 1][j] = new Node(0);
                                }
                                if (nodes[i + 1][j].type != 0 && !nodes[i + 1][j].flag) {
                                    countW++;
                                    nodes[i + 1][j] = new Node(0);
                                }
                            }
                            if (j == 18) {
                                if (nodes[i][j - 1].type != 0 && !nodes[i][j - 1].flag) {
                                    countW++;
                                    nodes[i][j - 1] = new Node(0);
                                }
                                if (nodes[i - 1][j].type != 0 && !nodes[i - 1][j].flag) {
                                    countW++;
                                    nodes[i - 1][j] = new Node(0);
                                }
                                if (nodes[i + 1][j].type != 0 && !nodes[i + 1][j].flag) {
                                    countW++;
                                    nodes[i + 1][j] = new Node(0);
                                }
                            }
                        }
                        else {
                            //判断中间 4
                            if(nodes[i + 1][j].type != 0 && !nodes[i + 1][j].flag) {
                                countW++;
                                nodes[i + 1][j] = new Node(0);
                            }
                            if (nodes[i][j + 1].type != 0 && !nodes[i][j + 1].flag) {
                                countW++;
                                nodes[i][j + 1] = new Node(0);
                            }
                            if (nodes[i - 1][j].type != 0 && !nodes[i - 1][j].flag) {
                                countW++;
                                nodes[i - 1][j] = new Node(0);
                            }
                            if (nodes[i][j - 1].type != 0 && !nodes[i][j - 1].flag) {
                                countW++;
                                nodes[i][j - 1] = new Node(0);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(countW);
        System.out.println(countB);
    }
}
