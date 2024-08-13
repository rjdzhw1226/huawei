package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//多段线段数据压缩
public class Test33 {
    static List<List<Integer>> ans = new ArrayList<>();

    static List<List<Integer>> rem = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (array.length % 2 != 0) {
            return;
        }
        for (int i = 0; i < array.length - 1; i += 2) {
            List<Integer> list = new ArrayList<>();
            list.add(0, array[i]);
            list.add(1, array[i + 1]);
            ans.add(list);
        }
        check();

        List<Integer> collect = rem.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        System.out.println(collect);

    }

    private static void check() {
        rem.add(0, ans.get(0));
        for (int i = 1; i < ans.size() - 1; i++) {
            if(nodefound(ans.get(i - 1), ans.get(i), ans.get(i + 1))){
                rem.add(ans.get(i));
            }
        }
        rem.add(ans.get(ans.size() - 1));
    }

    private static boolean nodefound(List<Integer> b, List<Integer> m, List<Integer> a) {
        int x = b.get(0) - m.get(0);
        int y = b.get(1) - m.get(1);
        int x1 = m.get(0) - a.get(0);
        int y1 = m.get(1) - a.get(1);
        int maxb = Math.max(Math.abs(x), Math.abs(y));
        int maxa = Math.max(Math.abs(x1), Math.abs(y1));
        if((x1 / maxa) == (x / maxb) && (y1 / maxa) == (y / maxb)){
            return false;
        } else {
            return true;
        }
    }
}
