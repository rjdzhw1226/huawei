package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
//螺旋数字矩阵
public class Test05 {
    //疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
    //给出数字个数 n （0 < n ≤ 999）和行数 m（0 < m ≤ 999），从左上角的 1 开始，按照顺时针螺旋向内写方式，依次写出2,3,....,n，最终形成一个 m 行矩阵。
    //小明对这个矩阵有些要求：
    //每行数字的个数一样多
    // 列的数量尽可能少
    // 填充数字时优先填充外部
    // 数字不够时，使用单个 * 号占位
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //个数
        int num = in.nextInt();
        //行数
        int row = in.nextInt();
        //列数
        int col = 0;
        int i = 1;
        while (true){
            if (num < row * i && num > row * (i - 1)) {
                col = i;
                break;
            }
            i++;
        }
        char[][] str = new char[row][col];
        init(str);
        int begin = 1;
        if (col == 1) {
            int count = 0;
            while (begin <= num && count < row){
                str[count][col - 1] = Character.forDigit(begin, 10);
                begin++;
                count++;
            }
            print(str);
        } else {
            int countOne = 0;
            int countTwo = 0;
            while (begin <= num) {
                while (countTwo < col && str[countOne][countTwo] == '*' && begin <= num) {
                    str[countOne][countTwo] = Character.forDigit(begin++, 10);
                    countTwo++;
                }
                countOne++;
                countTwo--;
                while (countOne < row && str[countOne][countTwo] == '*' && begin <= num) {
                    str[countOne][countTwo] = Character.forDigit(begin++, 10);
                    countOne++;
                }
                countOne--;
                countTwo--;
                while (countTwo >= 0 && str[countOne][countTwo] == '*' && begin <= num) {
                    str[countOne][countTwo] = Character.forDigit(begin++, 10);
                    countTwo--;
                }
                countOne--;
                countTwo++;
                while (countOne >= 0 && str[countOne][countTwo] == '*' && begin <= num) {
                    str[countOne][countTwo] = Character.forDigit(begin++, 10);
                    countOne--;
                }
                countOne++;
                countTwo++;
            }
            print(str);
        }


    }

    private static void init(char[][] str) {
        for (char[] chars : str) {
            Arrays.fill(chars, '*');
        }
    }

    private static void print(char[][] str){
        for (char[] chars : str){
            for (char aChar : chars) {
                System.out.printf("%c,", aChar);
            }
            System.out.println();
        }
    }
}
