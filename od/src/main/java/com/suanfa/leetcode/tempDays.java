package com.suanfa.leetcode;

import com.suanfa.gpthelp.StringUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class tempDays {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
//    dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
  }
//  public static int[] dailyTemperatures(int[] temperatures) {
//    int[] res = new int[temperatures.length];
//    for (int i = 0; i < temperatures.length; i++) {
//      int t = temperatures[i];
//      for (int j = i + 1; j < temperatures.length; j++) {
//        if(temperatures[j] > t){
//          res[i] = j - i;
//          break;
//        }
//      }
//    }
//    return res;
//  }

  public static int[] dailyTemperatures(int[] temperatures) {
    int[] res = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < temperatures.length; i++) {
      int t = temperatures[i];
      while (!stack.isEmpty() && t > temperatures[stack.peek()]) {
        int j = stack.pop();
        res[j] = i - j;
      }
      stack.push(i);
    }
    return res;
  }

  public static int[] dailyTemperatures1(int[] temperatures) {
    int n = temperatures.length;
    int[] ans = new int[n];
//    Deque<Integer> st = new ArrayDeque<>();
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < n; i++) {
      int t = temperatures[i];
      while (!st.isEmpty() && t > temperatures[st.peek()]) {
        int j = st.pop();
        ans[j] = i - j;
      }
      st.push(i);
    }
    return ans;
  }
}
