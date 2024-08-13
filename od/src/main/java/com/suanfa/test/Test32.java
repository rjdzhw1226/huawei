package com.suanfa.test;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
//精准核酸检测 并查集
public class Test32 {
    static class unionFind{
        int[] father;
        int count;

        public unionFind() {
        }

        public unionFind(int n){
            this.father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
            this.count = n;
        }

        public int find(int k){
            if(k != father[k]){
                this.father[k] = this.find(father[k]);
                return this.father[k];
            }
            return k;
        }

        public void updateFa(int i, int j){
            int fi = this.find(i);
            int fc = this.find(j);
            if(fc != fi){
                this.father[fc] = fi;
                this.count--;
            }
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("father", father)
                    .append("count", count)
                    .toString();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());
        String next = in.nextLine();
        String[] strings = new String[count];
        for (int i = 0; i < count; i++) {
            strings[i] = in.nextLine();
        }

        unionFind un = new unionFind(count);

        int[][] map = new int[count][count];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(strings[i].split(",")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if(map[i][j] == 1){
                    un.updateFa(i, j);
                }
            }
        }

        int[] countN = new int[count];
        for (int i = 0; i < count; i++) {
            int i1 = un.find(i);
            countN[i1]++;
        }

        int res = 0;
        int[] array;
        if(!next.contains(",")){
            array = new int[]{Integer.parseInt(next)};
        } else {
            array = Arrays.stream(next.split(",")).mapToInt(Integer::parseInt).toArray();
        }

        HashSet<Integer> hash = new HashSet<>();
        for (int i : array) {
            int ii = un.find(i);
            if(hash.contains(ii)){
                continue;
            }
            hash.add(ii);

            res = res + countN[ii];
        }
        System.out.println(res - array.length);
    }
}
