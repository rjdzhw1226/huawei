package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//校招 模式匹配
public class Test68 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String part = in.nextLine();
    String line = in.nextLine();
    String joins = "(";
    String[] split = part.substring(0, part.length() - 1).split("\\(");
    int anInt = Integer.parseInt(split[0]);
    for (char c : split[1].toCharArray()) {
      if(c == 'A'){
        joins += "[a-zA-Z]";
      } else if(c == 'N'){
        joins += "[0-9]";
      }
    }
    joins += "){" + anInt + "}";
    System.out.println(joins);
    Pattern pattern = Pattern.compile(joins);
//    Pattern pattern = Pattern.compile("([a-zA-Z][0-9][a-zA-Z]){3}");
    Matcher matcher = pattern.matcher(line);
    while (matcher.find()) {
      System.out.println(matcher.group());
    }
  }
}
