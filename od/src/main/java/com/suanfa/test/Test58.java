package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
//虚拟理财游戏
public class Test58 {
  //题目描述
  //在一款虚拟游戏中生活，你必须进行投资以增强在虚拟游戏中的资产以免被淘汰出局，现有一家Bank，它提供有若干理财产品 m 个，风险及投资回报不同，你有N(元)进行投资，能接收的总风险值为X.你要在可接受范围内选择最优的投资方式获得最大回报。
  //备注:
  //。在虚拟游戏中，每项投资风险值相加为总风险值;
  //。在虚拟游戏中，最多只能投资2个理财产品;
  //在虚拟游戏中，最小单位为整数，不能拆分为小数;
  //投资额*回报率=投资回报
  //输入描述
  //第一行:
  //·产品数(取值范围[1,20])
  //·总投资额(整数，取值范围[1，100001)
  //可接受的总风险(整数，取值范围[1,2001)
  //第二行:产品投资回报率只序列，输入为整数，取值范围[1,60]
  //第三行:产品风险值序列，输入为整数，取值范围[1，100]
  //第四行:最大投资额度序列，输入为整数，取值范围[1，10000]
  //输出描述
  //每个产品的投资额序列
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int[] ints = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    if(ints.length != 3){
      return;
    }
    int goodsCount = ints[0];
    int moneyTotal = ints[1];
    int fibTotal = ints[2];

    int[] back = transformString(in.nextLine());
    int[] fib = transformString(in.nextLine());
    int[] mon = transformString(in.nextLine());
    if(back.length != goodsCount || fib.length  != goodsCount || mon.length  != goodsCount){
      return;
    }

    int maxReturn = 0;
    int[][] pairs = new int[2][2];

    for (int i = 0; i < goodsCount; i++) {
      for (int j = i + 1; j < goodsCount; j++) {
        //风险超出
        if(fib[i] + fib[j] > fibTotal){
          continue;
        }
        //判断最大值
        int max1, max2;
        if(mon[i] + mon[j] <= moneyTotal) {
          max1 = mon[i];
          max2 = mon[j];
        } else {
          if(back[i] >= back[j]) {
            max1 = Math.min(mon[i], moneyTotal);
            max2 = moneyTotal - max1;
          } else {
            max2 = Math.min(mon[j], moneyTotal);
            max1 = moneyTotal - max2;
          }
        }
        int ans = max1 * back[i] + max2 * back[j];
        if (ans > maxReturn) {
          maxReturn = ans;
          pairs[0][0] = i;
          pairs[0][1] = max1;
          pairs[1][0] = j;
          pairs[1][1] = max2;
        }
      }

      int[] ans = new int[goodsCount];
      ans[pairs[0][0]] = pairs[0][1];
      ans[pairs[1][0]] = pairs[1][1];

      for (int j = 0; j < goodsCount; j++) {
        System.out.print(ans[j] + " ");
      }
    }

  }

  public static int[] transformString(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
  }
}
