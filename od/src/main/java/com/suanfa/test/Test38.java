package com.suanfa.test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//数组去重和排序
public class Test38 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] array = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        Hashtable<Integer, String> hash = new Hashtable<>();

        for (int i = 0; i < array.length; i++) {
            if(hash.get(array[i]) == null){
                hash.put(array[i], 1 + " " + i);
            } else {
                hash.put(array[i], (Integer.parseInt(hash.get(array[i]).split(" ")[0]) + 1) + " " + i);
            }
        }

//        System.out.println(hash);

        for (Map.Entry<Integer, String> Entry : hash.entrySet().stream().sorted((o1, o2) -> {
            int i = Integer.parseInt(o2.getValue().split(" ")[0]);
            int j = Integer.parseInt(o1.getValue().split(" ")[0]);
            int i1 = Integer.parseInt(o1.getValue().split(" ")[1]);
            int j1 = Integer.parseInt(o2.getValue().split(" ")[1]);
            return i - j == 0 ? i1 - j1 : i - j;
        }).collect(Collectors.toList())) {
            System.out.println(Entry.getKey());
        }
    }
}
