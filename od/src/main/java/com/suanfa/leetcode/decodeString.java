package com.suanfa.leetcode;

import java.util.Stack;

public class decodeString {
  /*
  * |  |
  * | c |
  * | 2 |
  * | a |
  * | 3 |
  *3[a2[c]]
  * 3[a]2[b]
  * */
  public static void main(String[] args) {
    System.out.println(decodeString("3[a2[c]]4[d]"));
  }
  public static String decodeString(String s) {
    Stack<String> stack = new Stack<>();
    char[] chars = s.toCharArray();
    String temp = "";
    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (char a : chars) {
      if (a >= '0' && a <= '9') {
        temp += a;
      } else if(a == '[') {
        stack.push(temp);
        temp = "";
        count++;
      } else if(a == ']') {
        count--;
        if(count == 0){
          StringBuilder sbT = new StringBuilder();
          while (!stack.isEmpty()){
            String num = stack.pop();
            String countN = stack.pop();
            for (int i = 0; i < Integer.parseInt(countN); i++) {
              sbT.append(num);
            }
          }
          sb.append(sbT.reverse().toString());
        }
      } else {
        stack.push(a + "");
      }
    }
    return sb.toString();
  }
}
