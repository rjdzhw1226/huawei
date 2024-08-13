package com.suanfa.gpthelp;// Java

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> dic = new HashMap<>();
        while (scanner.hasNext()) {
            int val = scanner.nextInt();
            dic.put(val, dic.getOrDefault(val, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : dic.entrySet()) {
            int m = entry.getKey();
            int n = entry.getValue();
            ans += Math.ceil((double) n / (m + 1)) * (m + 1);
        }
        System.out.println(ans);
    }
}
