package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//字符串乘法和加法 todo
public class StringSumAndMul {
  /*
  *      4561
  *       123
  *         3
  *       18
  *      15
  *
  *
  *        1 2 1
  *        9 5 6
  *        7 2 6
  *      6 0 5
  *  1 0 8 9
  * */

  public static void main(String[] args) {
//    System.out.println(4561 * 123);
    System.out.println(multiplyQ("956", "1211"));
  }
  public static String multiply(String num1, String num2) {
    if(num1.equals("0") || num2.equals("0")){
      return "0";
    }
    if(num1.equals("1")){
      return num2;
    }
    if(num2.equals("1")){
      return num1;
    }
    String res = "0";
    for (int i = num2.length() - 1; i >= 0; i--) {
      int carry = 0;
      StringBuilder temp = new StringBuilder();
      for (int j = 0; j < num2.length() - 1 - i; j++) {
        temp.append(0);
      }
      int n2 = num2.charAt(i) - '0';

      for (int j = num1.length() - 1; j > 0 || carry != 0; j--) {
        int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
        int pro = (n1 * n2 + carry) % 10;
        temp.append(pro);
        carry = (n1 * n2 + carry) / 10;
      }
      res = addStrings(res, temp.reverse().toString());
    }
    return res;
  }

  //自己写的加法有点问题 待改正
  public static String multiplyQ(String num1, String num2){
    if(num1.equals("0") || num2.equals("0")){
      return "0";
    }
    if(num1.equals("1")){
      return num2;
    }
    if(num2.equals("1")){
      return num1;
    }
    LinkedList<String> list = new LinkedList<>();
    int n = num1.length();
    int m = num2.length();
    if(num1.length() >= num2.length()){
      int co = 0;
      for (int i = m - 1; i >= 0; i--) {
        int count = 0;
        StringBuilder s = new StringBuilder();
        for (int k = 0; k < co; k++) {
          s.append("0");
        }
        for (int j = n - 1; j >= 0; j--) {
          int f = Integer.parseInt(num1.charAt(j) + "") * Integer.parseInt(num2.charAt(i) + "") + count;
          int temp = f % 10;
          if(j == 0 && f >= 10){
            s.append(temp);
            s.append(f / 10);
          } else {
            s.append(temp);
          }
          count = f / 10;
        }
        co++;
        list.add(s.reverse().toString());
      }
    }
    else {
      int co = 0;
      for (int i = n - 1; i >= 0; i--) {
        int count = 0;
        StringBuilder s = new StringBuilder();
        for (int k = 0; k < co; k++) {
          s.append("0");
        }
        for (int j = m - 1; j >= 0; j--) {
          int f = Integer.parseInt(num1.charAt(i) + "") * Integer.parseInt(num2.charAt(j) + "") + count;
          int temp = f % 10;
          if(j == 0 && f >= 10){
            s.append(temp);
            s.append(f / 10);
          } else {
            s.append(temp);
          }
          count = f / 10;
        }
        co++;
        list.add(s.reverse().toString());
      }
    }
    System.out.println(list);
    int len = list.stream().max((o1, o2) -> o1.length() - o2.length()).orElse("").length();
    LinkedList<String> st = new LinkedList<>();
    for (String s : list) {
      String zero = "";
      int l = s.length();
      while (len - l > 0){
        zero += "0";
        l++;
      }
      st.add(zero + s);
    }
    while (st.size() > 1){
      String fir = st.pop();
      String sec = st.pop();
      st.push(addStrings(fir, sec));
//      StringBuilder sb = new StringBuilder();
//      int temp = 0;
//      int carry = 0;
//      for (int i = len - 1; i >= 0; i--) {
//        int o = Integer.parseInt(fir.charAt(i) + "") + Integer.parseInt(sec.charAt(i) + "");
//        temp = (o + carry) % 10;
//        if(i == 0){
//          sb.append(temp);
//          sb.append((o + carry) / 10);
//        } else {
//          sb.append(temp);
//        }
//        sb.append(temp);
//        carry = o / 10;
//      }
//      st.push(sb.reverse().toString());
//      System.out.println(st);
    }
    return st.pop();
  }

  public static String addStrings(String num1, String num2) {
    int i = num1.length() - 1, j = num2.length() - 1, add = 0;
    StringBuffer ans = new StringBuffer();
    while (i >= 0 || j >= 0 || add != 0) {
      int x = i >= 0 ? num1.charAt(i) - '0' : 0;
      int y = j >= 0 ? num2.charAt(j) - '0' : 0;
      int result = x + y + add;
      ans.append(result % 10);
      add = result / 10;
      i--;
      j--;
    }
    // 计算完以后的答案需要翻转过来
    ans.reverse();
    return ans.toString();
  }

}
