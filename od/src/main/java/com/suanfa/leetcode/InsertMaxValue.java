package com.suanfa.leetcode;

public class InsertMaxValue {
  public static void main(String[] args) {
    System.out.println(maxValue("112", 1));

  }
  //插入后的最大值
  public static String maxValue(String n, int x) {
    char[] chars = n.toCharArray();
    String ans = "";
    int index = 0;
    if(chars[0] == '-'){
      for (int i = 1; i < chars.length; i++) {
        int b = Integer.parseInt(chars[i] + "");
        if(b > x){
          index = i - 1;
        } else if(b < x){
          index = i + 1;
        } else {
          index = -2;
        }
      }
      if(index == -2 || index == chars.length){
        return n + x;
      }
      if(index == 0){
        return "-" + x + n.substring(1);
      }
      ans += "-";
      for (int i = 1; i < chars.length; i++) {
        if(i == index){
          ans += x;
        }
        ans += chars[i];
      }
      return ans;
    } else {
      index = -2;
      for (int i = 0; i < chars.length; i++) {
        int b = Integer.parseInt(chars[i] + "");
        if(b < x){
          index = i - 1;
        } else if(b == x){
          index = -1;
        }
      }
      if(index == -1 || index == -2){
        return n + x;
      }
      for (int i = 0; i < chars.length; i++) {
        if(i == index){
          ans += x;
        }
        ans += chars[i];
      }
      return ans;
    }
  }
}
