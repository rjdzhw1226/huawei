package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//分苹果 分积木
public class Test71 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int count = Integer.parseInt(in.nextLine());
    int[] weights = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    Arrays.sort(weights);
    int min = weights[0];
    int ten = min;
    int two = min;
    for (int i = 1; i < weights.length; i++) {
      ten += weights[i];
      two ^= weights[i];
    }
    if(two == 0){
      System.out.println(ten - min);
    } else {
      System.out.println("NO");
    }
  }
}
