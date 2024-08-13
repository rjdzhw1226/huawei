package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//会议室占用时间
public class Test29 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        in.nextLine();
        for (int j = 0; j < i; j++) {
            list.add(Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
        }
        boolean flag = false;
        System.out.println(list);
//        findChange(list, 0, 1);
        for (int i1 = 0; i1 < list.size() - 1; i1++) {
            for (int j = i1 + 1; j < list.size(); j++) {
                list = list.stream().sorted((o1, o2) -> {
                    return o1.get(0) - o2.get(0) == 0 ? o1.get(1) - o2.get(1) : o1.get(0) - o2.get(0);
                }).collect(Collectors.toList());
                List<Integer> fir = list.get(i1);
                List<Integer> sec = list.get(j);
                if(sec.get(0) <= fir.get(1)){
                    List<Integer> res = new ArrayList<>();
                    res.add(0,Math.min(fir.get(0), sec.get(0)));
                    res.add(1,Math.max(fir.get(1), sec.get(1)));
                    list.remove(fir);
                    list.remove(sec);
                    list.add(res);
                    flag = true;
                    break;
                }
            }
            if(flag){
                i1 = -1;
                flag = false;
            }
        }
        System.out.println(list);

    }

    //递归解
    private static void findChange(List<List<Integer>> list, int cur, int nex) {
        //排序

        List<Integer> fir = list.get(cur);
        List<Integer> sec = list.get(nex);
        if(sec.get(0) >= fir.get(1)){
            list.remove(fir);
            list.remove(sec);

        }


    }
}
