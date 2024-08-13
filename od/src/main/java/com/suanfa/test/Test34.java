package com.suanfa.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//测试用例执行计划
public class Test34 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int count = Integer.parseInt(line.split(" ")[0]);
        int testC = Integer.parseInt(line.split(" ")[1]);
        int[] proi = new int[count];
        for (int i = 0; i < count; i++) {
            proi[i] = Integer.parseInt(in.nextLine());
        };
        String[] str = new String[testC];
        for (int i = 0; i < testC; i++) {
            str[i] = in.nextLine();
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            int sum = Arrays.stream(str[i].split(" ")).map(e -> {
                int j = Integer.parseInt(e);
                return proi[j - 1];
            }).mapToInt(Integer::intValue).sum();
            map.put(i+1, sum);
        }

        map.entrySet().stream().sorted((o1, o2) -> {
            return o2.getValue() - o1.getValue() == 0 ? o1.getKey() - o2.getKey() : o2.getValue() - o1.getValue();
        }).map(e -> e.getKey()).collect(Collectors.toList()).forEach(System.out::println);

    }
}
