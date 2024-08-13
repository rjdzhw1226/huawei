package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//靠谱的车
public class Test65 {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    int[] ints = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
    System.out.println(Arrays.toString(ints));
//    int i = Integer.parseInt(in.nextLine());
//    String s = Integer.toBinaryString(Integer.parseInt(in.nextLine()));
    // 1 1
    // 2 2
    // 3 3
    // 4 5
    // 5 6
    // 6 7
    // 7 8
    // 8 9
    // 9 10
    findNumCar(ints);


  }

  private static int findNumCar(int[] num) {
    int cor = 0;

    for (int i = 0; i < num.length; i++) {
      int fault = num[i];
      if(fault > 4) {
        fault--;
      }
      for (int j = num.length - i - 1; j > 0; j--) {
         fault *= 9;
      }
      cor += fault;
    }

    return cor;
  }
}
