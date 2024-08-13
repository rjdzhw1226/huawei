package com.suanfa.test;

import java.util.*;
import java.util.stream.Collectors;
//堆内存分配
public class Test35 {
    static int[] memery = new int[100];
    static boolean flag = false;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = Integer.parseInt(in.nextLine());
        List<String> str = new ArrayList<>();
        while (in.hasNextLine()){
            String line = in.nextLine();
            if(line.length() == 0){
                break;
            }
            str.add(line);
        }
        init(str);
        if(flag){
            System.out.println(-1);
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int len = 1;
        for (int i = 0; i < memery.length; i += len) {
            if(memery[i] != 1){
                len = check(i, map);
            } else {
                len = 1;
            }
        }

//        System.out.println(Arrays.toString(memery));
        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream().sorted((o1, o2) -> {
            return o1.getKey() - o2.getKey();
        }).collect(Collectors.toList());

//        System.out.println(collect);
        Map<Integer, Integer> res = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : collect) {
            int diff = Integer.MAX_VALUE;
            if (entry.getValue() >= size) {
                if(entry.getValue() - size < diff){
                    diff = entry.getValue() - size;
                }
                res.put(entry.getKey(), diff);
            }
        }
//        System.out.println(res);
        System.out.println(res.entrySet().stream().sorted((o1, o2) -> o1.getValue() - o2.getValue() == 0 ? o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue()).collect(Collectors.toList()).get(0).getKey());
    }

    private static int check(int i, Map<Integer, Integer> map) {
        int len = 0;
        int count = i;
        while (count < memery.length && memery[count] == 0){
            count++;
            len++;
        }
        map.put(i, len);
        return len;
    }

    private static void init(List<String> str) {
        for (String s : str) {
            int i1 = Integer.parseInt(s.split(" ")[1]);
            int i2 = Integer.parseInt(s.split(" ")[0]);
            if(i1 + i2 > 100){
                flag = true;
                break;
            }
            for (int i = 0; i < i1; i++) {
                memery[i2 + i] = 1;
            }
        }
    }
}
