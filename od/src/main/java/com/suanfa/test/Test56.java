package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
//机器人搬砖
public class Test56 {
  //题目描述
  //机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第i堆砖中有 bricks[i] 块砖头，要求在8小时内搬完。机器人每小时能搬砖的数量取决于有多少能量格，
  // 机器人一个小时中只能在一个仓库中搬砖，机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，应尽量减小每次补充的能量格数。
  //为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
  //·无需考虑机器人补充能力格的耗时;
  //无需考虑机器人搬砖的耗时:
  //·机器人每小时补充能量格只在这一个小时中有效;
  //输入描述
  //第一行为一行数字，空格分隔
  //输出描述
  //机器人每小时最少需要充的能量格，若无法完成任务，输出-1
  //30 12 25 8 19
  //
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String line = in.nextLine();
    int[] ints = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    if(ints.length == 0) {
      System.out.println(-1);
      return;
    }
    if(ints.length > 8) {
      System.out.println(-1);
      return;
    }

    int max = Arrays.stream(ints).max().orElse(0);
    int min = 1;

    if(ints.length == 8) {
      System.out.println(max);
      return;
    }
    int res = 0;
    while (min <= max){
      int mid = (max + min) >> 1;
      if(check(mid,ints)){
        res = mid;
        max = mid - 1;
      } else {
        min = mid + 1;
      }
    }
    System.out.println(res);
  }

  private static boolean check(int mid, int[] ints) {
    int count = 0;
    for (int anInt : ints) {
      int i = anInt / mid + (anInt % mid > 0 ? 1 : 0);
      count += i;
      if(count > 8){
        return false;
      }
    }
    return true;
  }
}
