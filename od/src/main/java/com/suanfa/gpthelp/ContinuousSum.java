package com.suanfa.gpthelp;

import java.util.ArrayList;
import java.util.List;

public class ContinuousSum {
    public static List<List<Integer>> findContinuousSums(int n) {
        List<List<Integer>> result = new ArrayList<>();
        
        for (int start = 1; start <= n / 2; start++) {
            int sum = start;
            int current = start + 1;
            List<Integer> sequence = new ArrayList<>();
            sequence.add(start);
            
            while (sum < n) {
                sequence.add(current);
                sum += current;
                current++;
            }
            
            if (sum == n) {
                result.add(new ArrayList<>(sequence));
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int n = 15;
        List<List<Integer>> sums = findContinuousSums(n);
        
        if (sums.isEmpty()) {
            System.out.println("No continuous sums found for " + n);
        } else {
            System.out.println("Continuous sums for " + n + ":");
            for (List<Integer> sum : sums) {
                System.out.println(sum);
            }
        }
    }
}
