package com.suanfa.test;

import java.util.*;
import java.util.stream.Collectors;

//分披萨 缓存放哪里？
public class Test22_To_Be_Continue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] ints = new int[count];
        for (int i = 0; i < count; i++) {
            ints[i] =in.nextInt();
        }
//        System.out.println(Arrays.toString(ints));
        int[] sub = new int[2];
        sub[0] = Integer.MAX_VALUE;
        sub[1] = Integer.MAX_VALUE;

        findSumBy2(Arrays.stream(ints).boxed().collect(Collectors.toList()), sub, 1, 0);
        System.out.println(eatL.stream().mapToInt(Integer::intValue).sum());
    }

    static Hashtable<String, Integer> hash = new Hashtable<>();
    static List<Integer> eatL = new ArrayList<>();
    static List<Integer> monthL = new ArrayList<>();
    private static void findSumBy2(List<Integer> sum, int[] sub, int eat, int month) {
        if(check(sum)){
            return;
        }
//        if(sum.size() == 0){
//            return;
//        }
        if(sum.size() < 2){
            if(eat == 1) {
                eatL.add(sum.get(0));
            } else if(month == 1){
                monthL.add(sum.get(0));
            }
            sum.remove(0);
            sum.add(0, Integer.MIN_VALUE);
            findSumBy2(sum, sub, eat, month);
        }
        else {
            if(sub[0] == Integer.MAX_VALUE && sub[1] == Integer.MAX_VALUE){
                Integer max = sum.stream().max((o1, o2) -> o1 - o2).orElse(0);
                int f = sum.indexOf(max);
                if(eat == 1) {
                    eatL.add(max);
                    eat = 0;
                    month = 1;
                } else if(month == 1){
                    monthL.add(max);
                    eat = 1;
                    month = 0;
                }
                int temp = 0;
                if ((temp = sum.indexOf(max)) == 0) {
                    sub[0] = sum.size() - 1;
                    sub[1] = temp + 1;
                } else if ((temp = sum.indexOf(max)) == sum.size() - 1) {
                    sub[0] = temp - 1;
                    sub[1] = 0;
                } else {
                    sub[0] = sum.indexOf(max) - 1;
                    sub[1] = sum.indexOf(max) + 1;
                }
                sum.remove(max);
                sum.add(f, Integer.MIN_VALUE);
                findSumBy2(sum, sub, eat, month);
            }
            else {
                int m = sum.get(sub[0]) >= sum.get(sub[1]) ? sum.get(sub[0]) : sum.get(sub[1]);
                int of = sum.indexOf(m);
                if(eat == 1) {
                    eatL.add(m);
                    eat = 0;
                    month = 1;
                } else if(month == 1){
                    monthL.add(m);
                    eat = 1;
                    month = 0;
                }
                if(sum.get(sub[0]) == m){
                    if(of == 0){
                        sub[0] = sum.size() - 1;
                    } else {
                        sub[0] = of - 1;
                    }
                } else if (sum.get(sub[1]) == m) {
                    if(of == sum.size() - 1){
                        sub[1] = 0;
                    } else {
                        sub[1] = of + 1;
                    }
                }
                sum.remove(sum.get(of));
                sum.add(of, Integer.MIN_VALUE);
                findSumBy2(sum, sub, eat, month);
            }
        }

    }

    private static boolean check(List<Integer> sum) {
        for (int i = 0; i < sum.size(); i++) {
            if (sum.get(i) != Integer.MIN_VALUE) {
                return false;
            }
        }
        return true;
    }
}
