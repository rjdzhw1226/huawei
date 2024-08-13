package com.suanfa.test;

import java.util.Scanner;
//灰度图压缩存储
public class Test36 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] split = in.nextLine().split(" ");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
//        int[][] matrix = new int[row][col];
        String count = in.nextLine();
        int x = Integer.parseInt(count.split(" ")[0]);
        int y = Integer.parseInt(count.split(" ")[1]);

        int sum = (x * col) + (y + 1);
        System.out.println(sum);
//        int sum = 40;
        boolean flag = false;
        for (int i = 2; i < split.length - 1; i += 2) {
            for (int j = Integer.parseInt(split[i + 1]); j > 0; j--) {
                sum--;
                if(sum == 0){
                    int value = Integer.parseInt(split[i]);
                    System.out.println(value);
                    flag = true;
                    break;
                }
            }
            if (flag){
                break;
            }
        }
    }


}
