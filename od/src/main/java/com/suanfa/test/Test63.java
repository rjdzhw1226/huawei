package com.suanfa.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//查找接口成功率最优时间段 todo
public class Test63 {

  static Map<String, Integer> prefix = new HashMap<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int min = Integer.parseInt(in.nextLine());
    int[] ints = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    //1
    //1 2 3 4
    for (int i = 0; i < ints.length; i++) {
      for (int j = i; j < ints.length; j++) {
        int sum = total(i, j, ints);
//        System.out.println(i + "" + j + " " + sum / (j - i + 1));
        if ((sum / (j - i + 1)) <= min) {
          if(j != i){
            System.out.println(i + "-" + j);
          }

        }
      }
    }
    System.out.println(prefix);


  }

  private static int total(int i, int j, int[] ints) {
    if(prefix.get(i + "" + (j - 1)) == null){
      int sum = 0;
      for (int i1 = i; i1 <= j; i1++) {
        sum += ints[i1];
      }
      prefix.put(i + "" + j, sum);
      return sum;
    } else {
      int sum = 0;
      sum = sum + prefix.get(i + "" + (j - 1)) + ints[j];
      prefix.put(i + "" + j, sum);
      return sum;
    }
  }
}
