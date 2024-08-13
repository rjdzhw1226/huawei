package com.suanfa.gpthelp;

import java.util.Scanner;
import java.util.StringJoiner;
//存在借鉴
public class Main04 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = Integer.parseInt(sc.nextLine());

    String[][] records = new String[n][];
    for (int i = 0; i < n; i++) {
      records[i] = sc.nextLine().split(" ");
    }

    getResult(n, records);
  }

  public static void getResult(int n, String[][] records) {
    StringJoiner sj = new StringJoiner(" ");

    for (int i = 0; i < n; i++) {
      sj.add(isAward(records[i]) + "");
    }

    System.out.println(sj);
  }

  public static boolean isAward(String[] record) {
    // 总缺勤次数
    int absent = 0;

    // 滑窗内正常上班的次数
    int present = 0;

    // 记录前一次的考勤记录
    String preRecord = "";

    for (int i = 0; i < record.length; i++) {
      if (i >= 7) {
        // 滑窗长度最大为7，如果超过7，则滑窗的左边界需要右移, 滑窗失去的部分record[i - 7]
        // 如果失去部分是present，则更新滑窗内present次数
        if ("present".equals(record[i - 7])) present--;
      }

      // 当前的考勤记录
      String curRecord = record[i];

      switch (curRecord) {
        case "absent":
          // 缺勤不超过一次
          if (++absent > 1) return false;
          break;
        case "late":
        case "leaveearly":
          // 没有连续的迟到/早退
          if ("late".equals(preRecord) || "leaveearly".equals(preRecord)) return false;
          break;
        case "present":
          present++;
          break;
      }

      preRecord = curRecord;

      // 任意连续7次考勤，缺勤/迟到/早退不超过3次, 相当于判断： 滑窗长度 - present次数 <= 3
      int window_len = Math.min(i + 1, 7); // 滑窗长度
      if (window_len - present > 3) return false;
    }

    return true;
  }
}
