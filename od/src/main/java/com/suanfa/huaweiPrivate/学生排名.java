package com.suanfa.huaweiPrivate;

import java.util.*;

public class 学生排名 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<Integer, List<Integer>> hash = new HashMap<>();
        int[] array = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int count = array[0];
        int[] result = new int[array.length - 1];
        System.arraycopy(array, 1, result, 0, result.length);
        for (int i = 0; i < result.length - 1; i++) {
            if(i % 2 == 0) {
                if (hash.get(result[i]) == null) {
                    List<Integer> a = new ArrayList<>();
                    a.add(result[i + 1]);
                    hash.put(result[i], a);
                } else {
                    List<Integer> list = hash.get(result[i]);
                    list.add(result[i + 1]);
                    hash.put(result[i], list);
                }
            }
        }
        System.out.println(hash);
    }
}
