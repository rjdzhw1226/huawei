package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class kuoHaoGenerate {

  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    generate(new char[2 * n], 0, res);
    return res;
  }

  private void generate(char[] chars, int i, List<String> res) {
    if(i == chars.length){
      if(valid(chars)){
        res.add(Arrays.toString(chars));
      }
    } else {
      chars[i] = '(';
      generate(chars, i + 1, res);
      chars[i] = ')';
      generate(chars, i + 1, res);
    }
  }

  public boolean valid(char[] current) {
    int balance = 0;
    for (char c: current) {
      if (c == '(') {
        ++balance;
      } else {
        --balance;
      }
      if (balance < 0) {
        return false;
      }
    }
    return balance == 0;
  }

  public int longestValidParentheses(String s) {
    Stack<Integer> stack = new Stack<>();
    int maxAns = 0;
    stack.push(-1);
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(')
        stack.push(i);
      else { // 右括号情况
        stack.pop();
        if (stack.isEmpty())
          stack.push(i);
        else
          maxAns = Math.max(maxAns, i - stack.peek());
        // i - stack.peek() 当前的i值减去，栈顶返回的元素
      }
    }
    return maxAns;
  }
}
