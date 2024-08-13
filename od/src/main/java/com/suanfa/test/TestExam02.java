package com.suanfa.test;

import java.util.*;
import java.util.regex.Pattern;

public class TestExam02 {
  static String[] chars = new String[]
    {
    "1","2","3","4","5","6","7","8","9",
      "10\\*","11\\*","12\\*","13\\*","14\\*",
    "15\\*","16\\*","17\\*","18\\*","19\\*",
      "20\\*","21\\*","22\\*","23\\*","24\\*","25\\*","26\\*"
    };

  static String[] findChars = new String[]
    {
      "1","2","3","4","5","6","7","8","9",
      "10*","11*","12*","13*","14*",
      "15*","16*","17*","18*","19*",
      "20*","21*","22*","23*","24*","25*","26*"
    };

  static String[] nums = new String[]{
    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
    "w", "x", "y", "z"
  };


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.nextLine();
    Pattern pattern2 = Pattern.compile("(\\d+)(\\*)(\\d+)");
    boolean b2 = pattern2.matcher(s).find();
    if(!b2){
      return;
    }
    Map<String, String> map = new LinkedHashMap<>();
    int i = 25;
    init(map);
//    System.out.println(map);
    for (Map.Entry<String, String> se : map.entrySet()) {
      if(s.contains(findChars[i])){
        s = s.replaceAll(se.getKey(), se.getValue());
      }
      i--;
    }
    System.out.println(s);
  }

  private static void init(Map<String, String> map) {
    for (int i = 25; i >= 0; i--) {
      map.put(chars[i], nums[i]);
    }
  }
}
