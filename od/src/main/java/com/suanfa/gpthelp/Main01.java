package com.suanfa.gpthelp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main01 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("r 89 90", "d 45 67", "f 23 45");

        List<Integer> sums = input.stream()
                .map(str -> str.split(",")[0].split(" ")[1]) // 提取数字部分
                .mapToInt(Integer::parseInt) // 转换为整数
                .boxed()
                .collect(Collectors.toList());

        List<String> nonNumericStrings = input.stream()
                .map(str -> str.split(",")[0].split(" ")[0]) // 提取非数值的字符串
                .collect(Collectors.toList());

        Collections.sort(sums); // 对数字之和进行排序

        System.out.println("数字之和排序后：" + sums);
        System.out.println("非数值字符串集合：" + nonNumericStrings);
    }
}
