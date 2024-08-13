package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//App防沉迷
public class Test30{
    static List<String> queue = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        in.nextLine();
        String[] str = new String[count];
        for (int i = 0; i < count; i++) {
            str[i] = in.nextLine();
        }
        String next = in.next();
        for (int i = 0; i < str.length; i++) {
            if(queue.size() == 0){
                queue.add(str[i]);
            } else {
                boolean flag = true;
                List<List<Integer>> res = new ArrayList<>();
                List<Integer> mine = new ArrayList<>();
                for (int j = 0; j < queue.size(); j++) {
                    int u = 0;
                    if ((u = check(queue.get(j), str[i])) >= 0) {
                        if(u == 0){
                            List<Integer> list = new ArrayList<>();
                            list.add(0, j);
                            list.add(1, i);
                            res.add(list);
                        }
                        if(u == 1){
                            mine.add(i);
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }
                if(res.size() > 0 && flag){
                    //优先级高
                    for (int i1 = 0; i1 < res.size(); i1++) {
                        queue.remove(res.get(i1).get(0).intValue());
                        queue.add(res.get(i1).get(0).intValue(), str[res.get(i1).get(1).intValue()]);
                    }
                } else {
                    if(flag && mine.size() > 0){
                        mine = mine.stream().distinct().collect(Collectors.toList());
                        for (int i1 = 0; i1 < mine.size(); i1++) {
                            queue.add(str[mine.get(i1).intValue()]);
                        }
                    }
                }
            }
        }
        findApp(next);
    }

    private static void findApp(String time) {
        int time1 = str2time(time);
        boolean f1 = false;
        for (String s : queue) {
            if (str2time(s.split(" ")[2]) <= time1 && str2time(s.split(" ")[3]) > time1) {
                System.out.println(s.split(" ")[0]);
                f1 = true;
                break;
            }
        }
        if(!f1) {
            System.out.println("NA");
        }
    }

    private static int check(String s, String s1) {
        Integer pri = Integer.parseInt(s.split(" ")[1]);// 16:06 16:45
        int timeB = str2time(s.split(" ")[2]);
        int timeEnd = str2time(s.split(" ")[3]);

        Integer pri1 = Integer.parseInt(s1.split(" ")[1]);// 05:50 09:47
        int timeB1 = str2time(s1.split(" ")[2]);
        int timeEnd1 = str2time(s1.split(" ")[3]);

        if(timeB1 < timeEnd){
            if(timeEnd1 > timeB){
                //优先级高
                if(pri1 > pri){
                    return 0;
                    //优先级相同
                } else if (pri1 == pri) {
                    return -1;
                    //优先级低
                } else {
                    return -1;
                }
            } else {
                return 1;
            }
            //不交叉
        } else {
            return 1;
        }
    }

    private static int str2time(String st){
        int hour = Integer.parseInt(st.split(":")[0]) * 60;
        int min = Integer.parseInt(st.split(":")[1]);
        return hour + min;
    }


}
