package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

public class TestExam01 {
  /*public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num = in.nextInt();
    int row = in.nextInt();
    int col = (int) Math.ceil(1.0 * num / row);

    String[][] str = new String[row][col];
    init(str);
    int begin = 1;

    int countOne = 0;
    int countTwo = 0;
    while (begin <= num) {
      //列
      while (countTwo < col && str[countOne][countTwo].equals("*") && begin <= num) {
        str[countOne][countTwo] = String.valueOf(begin++);
        countTwo++;
      }
      countOne++;
      countTwo--;
      //行
      while (countOne < row && str[countOne][countTwo].equals("*") && begin <= num) {
        str[countOne][countTwo] = String.valueOf(begin++);
        countOne++;
      }
      countOne--;
      countTwo--;
      while (countTwo >= 0 && str[countOne][countTwo].equals("*") && begin <= num) {
        str[countOne][countTwo] = String.valueOf(begin++);
        countTwo--;
      }
      countOne--;
      countTwo++;
      while (countOne >= 0 && str[countOne][countTwo].equals("*") && begin <= num) {
        str[countOne][countTwo] = String.valueOf(begin++);
        countOne--;
      }
      countOne++;
      countTwo++;
    }
    print(str);
  }*/

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num = in.nextInt();
    int row = in.nextInt();
    int col = (int) Math.ceil(num * 1.0 / row);

//    System.out.println(col);

    String[][] str = new String[row][col];
    init(str);
    int begin = 1;
    if(col == 1){
      int count = 0;
      while(begin <= num && count < row){
        str[count][col - 1] = String.valueOf(begin);
        begin++;
        count++;
      }
    } else {
      int countOne = 0;
      int countTwo = 0;
      while (begin <= num) {
        //列
        while(countTwo < col && str[countOne][countTwo].equals("*") && begin <= num){
          str[countOne][countTwo] = String.valueOf(begin++);
          countTwo++;
        }
        countOne++;
        countTwo--;
        //行
        while(countOne < row && str[countOne][countTwo].equals("*") && begin <= num){
          str[countOne][countTwo] = String.valueOf(begin++);
          countOne++;
        }
        countOne--;
        countTwo--;
        while(countTwo >= 0 && str[countOne][countTwo].equals("*") && begin <= num){
          str[countOne][countTwo] = String.valueOf(begin++);
          countTwo--;
        }
        countOne--;
        countTwo++;
        while(countOne >= 0 && str[countOne][countTwo].equals("*") && begin <= num){
          str[countOne][countTwo] = String.valueOf(begin++);
          countOne--;
        }
        countOne++;
        countTwo++;
      }
    }
    print(str);
  }

  private static void init(String[][] str) {
    for (String[] chars : str) {
      Arrays.fill(chars, "*");
    }
  }

  private static void print(String[][] str){
    for (String[] strings : str) {
      for (String string : strings) {
        System.out.printf("%s ", string);
      }
      System.out.println();
    }
  }
}
