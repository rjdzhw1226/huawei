package com.suanfa.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

//求满足条件的最长子串的长度 todo
public class Test40 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int lef = 0;
        int countNum = 0; //字母个数
        int lastIndex = -1; //上一个字母索引
        List<Integer> lens = new ArrayList<>();
        Pattern patternN = Pattern.compile("[0-9]+");
        Pattern patternC = Pattern.compile("[A-z]+");
        if (!(patternN.matcher(line).find() && patternC.matcher(line).find())) {
            System.out.println(-1);
            return;
        }

//        for (int rig = 0; rig < line.length(); rig++) {
//            if(line.charAt(rig) < '0' || line.charAt(rig) > '9') {
//                if(lastIndex < 0){
//                    lef = 0;
//                } else {
//                    lef = lastIndex + 1;
//                }
//
//            }
//            lens.add(rig - lef + 2);
//            lastIndex = rig;
//        }

//        System.out.println(lens);
        System.out.println(getResult(line));
        //System.out.println(lens.stream().max((o1, o2) -> o1 - o2).orElse(0));
    }

    public static int getResult(String str){
        int max = -1;
        int l = 0;
        int r = 0;
        LinkedList<Integer> letterIdx = new LinkedList<>();

        while (r < str.length()){
            char c = str.charAt(r);

            if(isLetter(c)){
                letterIdx.add(r);

                if (letterIdx.size() > 1) {
                    l = letterIdx.removeFirst() + 1;
                }
                if (r == l) {
                    r++;
                    continue;
                }
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
