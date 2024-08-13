package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//考勤信息
public class Test61 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int anInt = Integer.parseInt(in.nextLine());
    String[] str = new String[anInt];

    for (int i = 0; i < anInt; i++) {
      boolean result = findResult(in.nextLine());
      str[i] = String.valueOf(result);
    }

    System.out.println(Arrays.toString(str));
  }


  private static boolean findResult(String str) {
    String[] s = str.split(" ");
    if (s.length == 1) {
      return true;
    } else {
      String pre = "";
      int id = 0;
      int idL = 0;
      for (int i1 = 0; i1 < s.length; i1++) {
        if (i1 >= 7) {
          if ("present".equals(s[i1 - 7])) {
            id--;
          }
        }
        switch (s[i1]) {
          case "absent":
            if (++idL > 1) {
              return false;
            }
            break;
          case "late":
          case "leaveearly":
            if ("late".equals(pre) || "leaveearly".equals(pre)) {
              return false;
            }
            break;
          case "present":
            id++;
            break;
        }
        pre = s[i1];
        int len = Math.min(i1 + 1, 7);
        if (len - id > 3) {
          return false;
        }
      }
      return true;
    }
  }
}
