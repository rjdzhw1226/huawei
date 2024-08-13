package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//游戏分组
public class Test08 {

    //题目描述
    //部门准备举办一场 王者荣耀Q表演赛，有 10 名游戏爱好者参与，分为两队，每队 5 人。
    //每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，我们需要把 10 名参赛者分为示例尽量相近的两队。
    //-队的实力可以表示为这一队 5 名队员的评分总和。
    //现在给你 10 名参与者的游戏水平评分，请你根据上述要求分队，最后输出这两组的实力差绝对值,例:10名参赛者的评分分别为:51834671092，分组为(135810)和(24679)，两组实力差最小，差值为1。有多种分法但是实力差的绝对值最小为1。
    //输入描述
    //10个整数，表示10名参与者的游戏水平评分。范围在[1,10000]之间。
    //输出描述
    //1个整数，表示分组后两组实力差绝对值的 最小值
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] num = new int[10];
        for (int i = 0; i < 10; i++) {
            num[i] = in.nextInt();
        }
        divideDiff(num, 0, 0, 0);
        System.out.println(minDiff);
    }

    private static void divideDiff(int[] num, int index, int sum1, int sum2){
        if(index == num.length) {
            minDiff = Math.min(minDiff, Math.abs(sum1 - sum2));
            return;
        }
        divideDiff(num, index + 1, sum1 + num[index], sum2);
        divideDiff(num, index + 1, sum1, sum2 + num[index]);
    }

    public static int getResult(int[] arr) {
        Arrays.sort(arr);
        ArrayList<Integer> res = new ArrayList<>();
        // dfs求10选5的去重组合，并将组合之和记录进res中，即res中记录的是所有可能性的5人小队实力值之和
        dfs(arr, 0, 0, 0, res);

        int sum = Arrays.stream(arr).reduce(Integer::sum).orElse(0);
        // 某队实力为subSum，则另一队实力为sum - subSum，则两队实力差为 abs((sum - subSum) - subSum)，先求最小实力差
        return res.stream().map(subSum -> Math.abs(sum - 2 * subSum)).min((a, b) -> a - b).orElse(0);
    }

    // 求解去重组合
    public static void dfs(int[] arr, int index, int level, int sum, ArrayList<Integer> res) {
        if (level == 5) {
            res.add(sum);
            return;
        }

        for (int i = index; i < 10; i++) {
            if (i > index && arr[i] == arr[i - 1]) continue; // arr已经升序，这里进行树层去重
            dfs(arr, i + 1, level + 1, sum + arr[i], res);
        }
    }
}

