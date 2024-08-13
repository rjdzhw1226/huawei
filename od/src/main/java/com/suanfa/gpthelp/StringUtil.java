package com.suanfa.gpthelp;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static List<String> generateCombinations(String str) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsHelper(str, 0, "", combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(String str, int index, String current, List<String> combinations) {
        if (index == str.length()) {
            combinations.add(current.trim()); // 添加生成的组合到列表中，去除首尾空格
            return;
        }
        System.out.println(current);
        // 递归生成组合，每次选择当前字符或插入空格
        generateCombinationsHelper(str, index + 1, current + str.charAt(index), combinations); // 选择当前字符
        generateCombinationsHelper(str, index + 1, current + " " + str.charAt(index), combinations); // 插入空格
    }

    public static void main(String[] args) {
        String input = "abcd"; // 例如，输入字符串为 "abc"
        List<String> combinations = generateCombinations(input);
        System.out.println(combinations); // 输出所有组合
    }
}
