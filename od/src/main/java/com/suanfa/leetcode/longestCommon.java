package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class longestCommon {
  public static void main(String[] args) {
    longestCommonPrefix(new String[]{"fl","fll","flv"});
  }
  public static String longestCommonPrefix(String[] strs) {
    List<Integer> count = new ArrayList<>();
    for (int i = 0; i < strs.length; i++) {
      for (int j = 0; j < strs.length; j++) {
        String str1 = strs[i];
        String str2 = strs[j];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        if(i < j){
          int k = 0;
          while((k < chars1.length && k < chars2.length) && chars1[k] == chars2[k]){
            k++;
          }
          count.add(k);
        }
      }
    }
    System.out.println(count);
    return "";
  }
}
