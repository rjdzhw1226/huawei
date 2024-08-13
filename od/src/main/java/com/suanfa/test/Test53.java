package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
//转盘寿司
public class Test53 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int[] ints = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    for (int i = 0; i < ints.length; i++) {
      int j;
      if(i != ints.length - 1){
        j = i + 1;
      } else {
        j = 0;
      }
      Stack<Integer> stack = new Stack<>();
      stack.push(ints[i]);
      int count = 0;
      while (i != j && count == 0){
        if(ints[j] <= stack.peek()){
          stack.push(ints[j]);
          count++;
        }
        j++;
        if(j == ints.length){
          j = 0;
        }
      }
      System.out.println(stack.stream().mapToInt(Integer::intValue).sum());
    }

  }

  public static void stackFollow(int[] nums){
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
      if(stack.isEmpty()){
        stack.push(nums[i]);
      } else {
        while (true){
          if(stack.isEmpty()){
            break;
          }
          if(nums[i] <= stack.peek()){
            stack.push(nums[i]);
            break;
          } else {
            stack.pop();
          }
        }
      }
    }
    System.out.println(stack);
  }
}
