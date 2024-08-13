package com.suanfa.leetcode;

import java.util.*;

public class compressString {
  public static void main(String[] args) {
    System.out.println(compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
  }
  //压缩字符串
  public static int compress(char[] chars) {
    if(chars.length == 1){
      return 1;
    }
    List<Character> l = new ArrayList<>();
    List<List<String>> list = new ArrayList<>();
    int k = 0;
    int e = 0;
    int count = 0;
    int size = 0;
    while (k <= chars.length - 1){
      if(e == chars.length){
        l.add(chars[k]);
        if(count != 1){
          size += String.valueOf(count).length();
          for (char c : String.valueOf(count).toCharArray()) {
            l.add(c);
          }
        }
        size++;
        break;
      }
      if(chars[k] == chars[e]){
        count++;
        e++;
      } else {
        l.add(chars[k]);
        if(count != 1){
          size += String.valueOf(count).length();
          for (char c : String.valueOf(count).toCharArray()) {
            l.add(c);
          }
        }
        size++;
        count = 0;
        k = e;
      }
    }
    for (int i = 0; i < l.size(); i++) {
      chars[i] = l.get(i);
    }
    System.out.println(Arrays.toString(chars));
    return size;
  }

  public static int compressQ(char[] cs) {
    int n = cs.length;
    int i = 0, j = 0;
    while (i < n) {
      int idx = i;
      while (idx < n && cs[idx] == cs[i]) idx++;
      int cnt = idx - i;
      cs[j++] = cs[i];
      if (cnt > 1) {
        int start = j, end = start;
        while (cnt != 0) {
          cs[end++] = (char)((cnt % 10) + '0');
          cnt /= 10;
        }
        reverse(cs, start, end - 1);
        j = end;
      }
      i = idx;
    }
    System.out.println(Arrays.toString(cs));
    return j;
  }
  static void reverse(char[] cs, int start, int end) {
    while (start < end) {
      char t = cs[start];
      cs[start] = cs[end];
      cs[end] = t;
      start++; end--;
    }
  }
}
