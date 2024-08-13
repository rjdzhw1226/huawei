package com.suanfa.gpthelp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionGenerator {

    // Cache to store already computed results
    private Set<String> seenExpressions = new HashSet<>();

    public List<String> generateExpressions(int[] nums, int target) {
        List<String> result = new ArrayList<>();
        generateExpressionsHelper(nums, target, "", 0, 0, 0, result);
        return result;
    }

    private void generateExpressionsHelper(int[] nums, int target, String expr, int pos, long eval, long multed, List<String> result) {
        if (pos == nums.length) {
            if (eval == target) {
                if (!seenExpressions.contains(expr)) {
                    result.add(expr);
                    seenExpressions.add(expr);
                }
            }
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[pos] == 0) break; // Avoid leading zero numbers
            long curr = nums[i];

            if (pos == 0) {
                // First number, pick it without any operator
                generateExpressionsHelper(nums, target, expr + curr, i + 1, curr, curr, result);
            } else {
                generateExpressionsHelper(nums, target, expr + "+" + curr, i + 1, eval + curr, curr, result);
                generateExpressionsHelper(nums, target, expr + "-" + curr, i + 1, eval - curr, -curr, result);
                generateExpressionsHelper(nums, target, expr + "*" + curr, i + 1, eval - multed + multed * curr, multed * curr, result);
                if (curr != 0) {
                    generateExpressionsHelper(nums, target, expr + "/" + curr, i + 1, eval - multed + multed / curr, multed / curr, result);
                }
            }
        }
    }

    public static void main(String[] args) {
        ExpressionGenerator generator = new ExpressionGenerator();
        int[] nums = {2, 4, 8}; // Available numbers
        int target = 100; // Target number

        List<String> expressions = generator.generateExpressions(nums, target);

        if (expressions.isEmpty()) {
            System.out.println("No valid expressions found.");
        } else {
            for (String expr : expressions) {
                System.out.println(expr);
            }
        }
    }
}
