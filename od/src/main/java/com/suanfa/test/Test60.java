package com.suanfa.test;

import java.util.*;

//执行任务赚积分
public class Test60 {
  public static void main(String[] args) {
    Map<Integer, Integer> integerMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);
//    System.out.println("请输入一个数字");
    int many = sc.nextInt();
//    System.out.println("请输入执行时间");
    int time = sc.nextInt();
    int count = 0;
//    System.out.println("请输入执行时间与数字积分 空格隔开");
    while (count < many){
      Scanner scStr = new Scanner(System.in);
      String line = scStr.nextLine();
      Integer key = Integer.valueOf(line.split(" ")[0]);
      Integer value = Integer.valueOf(line.split(" ")[1]);
      if(key > time){
        break;
      }
      if(integerMap.get(key) == null) {
        integerMap.put(key, value);
      } else {
        if(value.equals(integerMap.get(key))){
          continue;
        } else if (value > integerMap.get(key)) {
          integerMap.put(key, value);
        } else if (value < integerMap.get(key)) {
          continue;
        }
      }
      count++;
    }
    taskCommon(many, time, integerMap);
  }

  public static void taskCommon(int m, int n, Map<Integer, Integer> map){
    int sum = 0;
    Integer[] array = map.values().toArray(new Integer[0]);
    for (int i = 0; i < map.values().size(); i++) {
      sum += array[i];
    }
    System.out.println(sum);
  }
}
