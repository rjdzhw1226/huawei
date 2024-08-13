package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//剩余银饰的重量
public class Test17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        List<Integer> weights = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int weight = in.nextInt();
            weights.add(weight);
        }


        int k = 3;
        int b = 0;
        List<Integer> collect;
        while (weights.size() >= 3){
            collect = weights.stream().sorted((o1, o2) -> o2 - o1).skip(b).limit(k).collect(Collectors.toList());
            System.out.println(collect);
            findThree(collect, weights);
        }
//        System.out.println(weights);
        if (weights.size() == 0){
            System.out.println(0);
        } else if(weights.size() == 1){
            System.out.println(weights.get(0));
        } else {
            System.out.println(weights.stream().max((o1, o2) -> o2 - o1));
        }

    }

    private static void findThree(List<Integer> collect, List<Integer> sort) {
        if(collect.size() < 3){
            return;
        }
        Integer fir = collect.get(2);
        Integer sec = collect.get(1);
        Integer thd = collect.get(0);
        if(fir == sec && sec == thd){

        } else if (fir == sec && sec != thd) {
            if (thd - sec != 0) {
                sort.add(thd - sec);
            }
        } else if (fir != sec && sec == thd) {
            if (sec - fir != 0) {
                sort.add(sec - fir);
            }

        } else if (fir != sec && sec != thd) {
            if(Math.abs((thd - sec) - (sec - fir)) != 0){
//                int[] array = sort.stream().mapToInt(Integer::intValue).toArray();
//                Arrays.binarySearch(array, Math.abs((thd - sec) - (sec - fir)));
                sort.add(Math.abs((thd - sec) - (sec - fir)));
            }
        }
        sort.remove(fir);
        sort.remove(sec);
        sort.remove(thd);
    }
}
