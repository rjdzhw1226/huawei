package com.suanfa.leetcode;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
//行星碰撞
public class planetCash {
  public static void main(String[] args) {
//    asteroidCollision(new int[]{-2,-1,1,2});
    System.out.println(Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
  }

  public static int[] asteroidCollision(int[] asteroids) {
    Deque<Integer> stack = new ArrayDeque<Integer>();
    stack.push(asteroids[0]);
    for (int i = 1; i < asteroids.length; i++) {
      int asteroid = asteroids[i];
      int res;
      if (!stack.isEmpty()){
        if(asteroid > 0){
          stack.push(asteroid);
        } else {
          if(stack.peek() > 0){
            if ((res = check(asteroid, stack.pop())) != 0) {
              stack.push(res);
            }
          } else {
            stack.push(asteroid);
          }
        }
      }
    }
    int[] ans = new int[stack.size()];
    for (int i = ans.length - 1; i >= 0; i--) {
      ans[i] = stack.pop();
    }
    return ans;
  }

  private static int check(int asteroid, Integer pop) {
    if(Math.abs(asteroid) == Math.abs(pop)){
      return 0;
    } else if(Math.abs(asteroid) > Math.abs(pop)){
      return asteroid;
    } else {
      return pop;
    }
  }
}
