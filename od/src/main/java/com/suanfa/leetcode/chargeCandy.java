package com.suanfa.leetcode;

import java.util.Arrays;

//分糖果II
public class chargeCandy {
  public static void main(String[] args) {
    chargeCandy cd = new chargeCandy();
    System.out.println(Arrays.toString(cd.distributeCandies(20, 3)));
  }
  public int[] distributeCandies(int candies, int num_people) {
    int count = 0;
    int i = 1;
    int[] res = new int[num_people];
    while (candies >= i) {
      res[count] += i;
      candies -= i;
      if(count == num_people - 1){
        count = 0;
      } else {
        count++;
      }
      i++;
    }
    if(candies > 0){
      res[count] += candies;
    }
    return res;
  }
}
