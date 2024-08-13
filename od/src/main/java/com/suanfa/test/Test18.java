package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//最多购买宝石数目
public class Test18 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] ints = new int[count];
        for (int i = 0; i < count; i++) {
           ints[i] = in.nextInt();
        }
        int money = in.nextInt();

        int len = 0;
        int b = 0;
        int e = 0;
        //使用集合
        List<Integer> sum = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        while (e < ints.length && b < ints.length) {
            if(sum(sum) < money){
                sum.add(ints[e]);
                e++;
                //尾部处理
                if(e == ints.length){
                    while (sum.size() > 0) {
                        if(sum(sum) < money) {
                            break;
                        } else if (sum(sum) > money) {
                            sum.remove(0);
                        } else {
                            res.add(sum);
                            break;
                        }
                    }
                }
            } else if (sum(sum) > money) {
                sum.remove(new Integer(ints[b]));
                b++;
            } else {
                res.add(new ArrayList<>(sum));
                sum.remove(0);
                b++;
            }
        }
        System.out.println(res.stream().max((o1, o2) -> o1.size() - o2.size()).orElse(new ArrayList<>()));

    }

    private static int sum(List<Integer> sum){
        return sum.stream().mapToInt(Integer::intValue).sum();
    }
}
