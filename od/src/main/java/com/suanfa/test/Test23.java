package com.suanfa.test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//机场航班调度程序
public class Test23 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] split = line.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i].substring(0, 2) + " " + split[i].substring(2);
            split[i] = s;
        }
        List<String> collect = Arrays.stream(split).sorted((o1, o2) -> {
            return o1.split(" ")[0].compareTo(o2.split(" ")[0]) == 0 ? Integer.parseInt(o1.split(" ")[1]) - Integer.parseInt(o2.split(" ")[1]) : o1.split(" ")[0].compareTo(o2.split(" ")[0]);
        }).map(e -> e.replace(" ", "")).collect(Collectors.toList());
        System.out.println(collect);
    }
}
