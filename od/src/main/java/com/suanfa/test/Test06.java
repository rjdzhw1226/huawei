package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//智能成绩表
public class Test06 {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int subCount = in.nextInt();
        //加一行堵在这
        in.nextLine();
        String[] sub = in.nextLine().split(" ");
        
        List<String> res = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String sc = in.nextLine();
            res.add(sc);
        }
        boolean flag = false;
        int key = 0;
        String sort = in.nextLine();
        for (String s : sub) {
            if(s.equals(sort)){
                flag = true;
                break;
            }
            key++;
        }
        List<String> ans = new ArrayList<>();
        if(flag){
            //按科目排序
            int finalKey = key;
            List<String> collect = res.stream()
                    .sorted((o1, o2) -> {
                return Integer.parseInt(o2.split(" ")[finalKey + 1]) - Integer.parseInt(o1.split(" ")[finalKey + 1]) == 0 ? o2.split(" ")[0].compareTo(o1.split(" ")[0]) : Integer.parseInt(o2.split(" ")[finalKey + 1]) - Integer.parseInt(o1.split(" ")[finalKey + 1]);
            }).collect(Collectors.toList());
            for (int i = 0; i < collect.size(); i++) {
                String[] split = collect.get(i).split(" ");
                ans.add(split[0]);
            }
        } else {
            //按总分排序
            List<String[]> collect = res.stream()
                    .map(e -> e.split(" "))
                    .sorted((o1, o2) -> {
                return new Integer(Arrays.stream(o2).skip(1).mapToInt(Integer::parseInt).sum()) - new Integer(Arrays.stream(o1).skip(1).mapToInt(Integer::parseInt).sum()) == 0 ? o2[0].compareTo(o1[0]) : new Integer(Arrays.stream(o2).skip(1).mapToInt(Integer::parseInt).sum()) - new Integer(Arrays.stream(o1).skip(1).mapToInt(Integer::parseInt).sum());
            }).collect(Collectors.toList());
            collect.forEach(e -> {
                ans.add(e[0]);
            });
        }
        System.out.println(ans);
    }
}
