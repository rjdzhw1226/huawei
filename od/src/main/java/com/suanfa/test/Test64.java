package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//解密犯罪时间 todo
public class Test64 {
  static List<String> res = new ArrayList<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    char[] ints = new char[4];
    List<String> collect = Arrays.stream(in.nextLine().split(":")).collect(Collectors.toList());

    int i = 0;
    for (String s : collect) {
      ints[i] = s.charAt(0);
      ints[i + 1] = s.charAt(1);
      i+=2;
    }
    List<Character> cur = new ArrayList<>();

    dfsNum(ints, cur);
    System.out.println(res);
    //过滤非法时间 离当前时间最近的时间 todo

  }

  private static void dfsNum(char[] ints, List<Character> cur) {
    if(cur.size() == 4){
      String s = "";
      int count = 0;
      for (Character c : cur) {
        if(count == 1){
          s = s + c + ":";
        } else {
          s += c;
        }
        count++;
      }
      res.add(s);
      return;
    }
    for (char anInt : ints) {
      cur.add(anInt);
      dfsNum(ints, cur);
      cur.remove(cur.size() - 1);
    }
  }
}
