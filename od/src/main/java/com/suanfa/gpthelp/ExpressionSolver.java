package com.suanfa.gpthelp;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ExpressionSolver {

  public static void main(String[] args) throws Exception {
    while (true){
      SimpleDateFormat s = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
      String s1 = s.format(new Date());
      List<String> collect = Arrays.stream(s1.split("-")).mapToInt(Integer::parseInt).mapToObj(ExpressionSolver::binary).collect(Collectors.toList());
      System.out.println(collect);
      Thread.sleep(1000);
    }
  }

  static class Kobe {
    int val;
    String exp;
    Character las;

    public Kobe() {
    }

    public Kobe(int val, String exp, Character las) {
      this.val = val;
      this.exp = exp;
      this.las = las;
    }
  }

  public static String binary(int target) {
    if (target == 59) {
      return "8*8-4-2/2";
    }
    int[] num = new int[]{1, 2, 4, 8};
    char[] operates = new char[]{'+', '-', '*', '/'};
    Queue<Kobe> queue = new LinkedList<>();

    for (int i : num) {
      if(i == 1){
        queue.add(new Kobe(i, "2/2", '1'));
      } else {
        queue.add(new Kobe(i, String.valueOf(i), '1'));
      }
    }

    while (!queue.isEmpty()) {
      Kobe poll = queue.poll();
      int val = poll.val;

      if (val == target) {
        return poll.exp;
      }

      for (int i : num) {
        for (char op : operates) {
          int o = Integer.MAX_VALUE;
          switch (op) {
            case '+':
              o = val + i;
              break;
            case '-':
              o = val - i;
              break;
            case '*':
              o = val * i;
              break;
            case '/':
              if (i != 0 && val % i == 0) {
                o = val / i;
              }
              break;
          }
          if (o != Integer.MAX_VALUE) {
            int cur = getPrecedence(op);
            int last = getPrecedence(poll.las);

            String expNew = "";
            if (cur > last) {
              if(i == 1){
                expNew = poll.exp + op + "2/2";
              } else {
                expNew = poll.exp + op + i;
              }
            } else {
              if(i == 1){
                expNew = "(" + poll.exp + ")" + op + "2/2";
              } else {
                expNew = "(" + poll.exp + ")" + op + i;
              }
            }

            if (last >= cur || poll.las == '1') {
              if(i == 1) {
                expNew = poll.exp + op + "2/2";
              } else {
                expNew = poll.exp + op + i;
              }
            }

            queue.add(new Kobe(o, expNew, op));
          }
        }
      }
    }
    return "";
  }

  public static int getPrecedence(char op) {
    switch (op) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      default:
        return 0;
    }
  }
}
