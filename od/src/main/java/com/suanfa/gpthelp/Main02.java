package com.suanfa.gpthelp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main02 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("[r 89 90", "d 45 67", "f 23 45]");
        
        List<Integer> sums = input.stream()
                .map(str -> Arrays.asList(str.split(" ")))
                .map(list -> list.stream().filter(s -> s.matches("\\d+")).mapToInt(Integer::parseInt).sum())
                .collect(Collectors.toList());
        
        System.out.println("每个字符串中数字之和：" + sums);
    }
}
