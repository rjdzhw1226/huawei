package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//数的分解
public class Test14_DP {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        i = in.nextInt();
        ins = init(i);

        List<Integer> sum1 = new ArrayList<>();
        List<Integer> sum2 = new ArrayList<>();
        findNext(i, "0", "0", 0);
//        findNext(i, sum1, sum2, 0);
        System.out.println(resS.stream().distinct().collect(Collectors.toList()));
    }

    static List<Integer> res = new ArrayList<>();
    static List<String> resS = new ArrayList<>();
    static int k = 0;
    static int i;
    static int[] ins;

    private static int[] init(int i) {
        int[] ans = new int[i];
        for (int j = 1; j <= i; j++) {
            ans[j - 1] = j;
        }
        return ans;
    }

//    private static void findNext(int sumInt, List<Integer> sum1, List<Integer> sum2, int index) {
//        if(index == ins.length) {
//            return;
//        }
//        if(sumInt == sum1.stream().mapToInt(Integer::intValue).sum()){
//            System.out.println(sum1);
//            sum1.clear();
//        }
//        if(sumInt == sum2.stream().mapToInt(Integer::intValue).sum()){
//            System.out.println(sum2);
//            sum2.clear();
//        }
//        findNext(sumInt, back(sum1, index), sum2, index + 1);
//        findNext(sumInt, sum1, back(sum2, index), index + 1);
//    }

    private static void findNext(int sumInt, String sum1, String sum2, int index) {
        if(index == ins.length) {
            return;
        }
        if(sumInt == backInt(sum1)){
            resS.add(sum1);
        }
        if(sumInt == backInt(sum2)){
            resS.add(sum2);
        }
        findNext(sumInt, sum1 + " " + ins[index], sum2, index + 1);
        findNext(sumInt, sum1, sum2 + " " + ins[index], index + 1);
    }

    private static int backInt(String str){
        int sum = 0;
        if(!str.contains(" ")) {
            return sum;
        } else {
            for (String s : str.split(" ")) {
                sum += Integer.parseInt(s);
            }
            return sum;
        }
    }

    private static List<Integer> back(List<Integer> sum, int index) {
        sum.add(ins[index]);
        return sum;
    }
}
