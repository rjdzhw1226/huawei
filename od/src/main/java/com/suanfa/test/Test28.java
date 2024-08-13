package com.suanfa.test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//求幸存数之和
public class Test28 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> collect = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        int i = in.nextInt();
        int j = in.nextInt();

        int cur = 0;
        while (j < collect.size()){
            cur += i;
            if(cur > (collect.size() - 1)){
                cur = (cur - (collect.size() - 1)) - 1;
            }
            if(cur == (collect.size() - 1)){
                collect.remove(0);
            } else {
                collect.remove(cur + 1);
            }
        }
        System.out.println(collect.stream().mapToInt(Integer::intValue).sum());
    }

}
