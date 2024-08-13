package com.suanfa.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class simplePath {
  public static void main(String[] args) {
    simplePath s = new simplePath();
    System.out.println(s.simplifyPath("/home/"));
  }

  public String simplifyPath(String path) {
    StringBuilder sb = new StringBuilder();
    Stack<String> stack = new Stack<>();
    String[] strings = path.split("/");
    for (String string : strings) {
      if(string.equals("..")){
        if(!stack.isEmpty()){
          stack.pop();
        }
      } else if(string.equals(".") || string.equals("")){

      } else {
        stack.push(string);
      }
    }
    int size = stack.size();
    if(size == 0){
      return "/";
    }
    String[] st = new String[size];
    while (!stack.isEmpty()){
      st[size - 1] = stack.pop();
      size--;
    }
    for (String s : st) {
      sb.append("/");
      sb.append(s);
    }
    return sb.toString();
  }
}
