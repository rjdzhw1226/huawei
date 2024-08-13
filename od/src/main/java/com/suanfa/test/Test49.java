package com.suanfa.test;

import java.util.Scanner;
//找座位
public class Test49 {
  //题目描述
  //在一个大型体育场内举办了一场大型活动，由于疫情防控的需要，
  // 要求每位观众的必须间隔至少一个空位才允许落座.。
  //现在给出一排观众座位分布图只，座位中存在已落座的观众，
  // 请计算出，在不移动现有观众座位的情况下，最多还能坐下多少名观众。
  //输入描述
  //个数组，用来标识某一排座位只中，每个座位是否已经坐人。
  // 0表示该座位没有坐人，
  // 1表示该座位已经坐人。
  //1≤数组长度≤10000
  //输出描述
  //整数，在不移动现有观众座位的情况下，最多还能坐下多少名观众
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String line = in.nextLine();
    char[] chars = line.toCharArray();
    int count = 0;
    if(chars.length == 1) {
      if(chars[0] == '0'){
        count++;
      }
      System.out.println(count);
      System.out.println(new String(chars));
      return;
    }
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '0') {
        if(i == 0){
          if (chars[i + 1] == '0'){
            count++;
            chars[i] = '1';
          }
        } else if(i == chars.length - 1) {
          if (chars[i - 1] == '0'){
            count++;
            chars[i] = '1';
          }
        } else {
          if(chars[i + 1] == '0' && chars[i - 1] == '0') {
            count++;
            chars[i] = '1';
          }
        }
      }
    }
    System.out.println(count);
    System.out.println(new String(chars));
  }
}
