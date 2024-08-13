package com.suanfa.gpthelp;

import java.util.ArrayList;
import java.util.List;

public class NumberDecomposition {

    public static List<Integer> decompose(int number) {
        List<Integer> primeFactors = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(number); i++) {
            while (number % i == 0) {
                primeFactors.add(i);
                number /= i;
            }
        }
        if (number > 1) {
            primeFactors.add(number);
        }
        return primeFactors;
    }

    public static void main(String[] args) {
        int number = 12;
        System.out.println("分解后的质因数：" + decompose(number));
    }
}