package com.suanfa.test;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//内存冷热标记
public class Test57 {
  //题目描述
  //现代计算机系统中通常存在多级的存储设备，针对海量 workload 的优化的一种思路是将热点内存页优先放到快速存储层级，这就需要对内存页进行冷热标记。
  //-种典型的方案是基于内存页的访问频次讲行标记，如果统计窗口内访问次数大于等于设定阈值，则认为是热内存页，否则是冷内存页对于统计窗口内跟踪到的访存序列和阈值，现在需要实现基于频次的冷热标记。内存页使用页框号作为标识。
  //输入描述
  //第一行输入为 N，表示访存序列的记录条数，0<N≤10000.第二行为访存序列，空格分隔的 N 个内存页框号，页面号范围 0~65535，同一个页框号可能重复出现，出现的次数即为对应框号的频次
  //第三行为热内存的频次阈值T，正整数范围 1≤T≤10000.
  //输出描述
  //第一行输出标记为热内存的内存页个数，如果没有被标记的热内存页，则输出0如果第一行>0，则接下来按照访问频次降序输出内存页框号，一行一个，频次一样的页框号，页框号小的排前面。
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int anInt = in.nextInt();
    in.nextLine();
    String[] split = in.nextLine().split(" ");
    if(split.length != anInt){
      throw new RuntimeException("输入长度异常");
    }
    int nextInt = in.nextInt();
    Hashtable<Integer, Integer> hash = new Hashtable<>();
    for (String s : split) {
      Integer integer = Integer.valueOf(s);
      if(hash.get(integer) == null) {
        hash.put(integer, 1);
      } else {
        hash.put(integer, hash.get(integer) + 1);
      }
    }
    int count = 0;
    for (Integer integer : hash.keySet()) {
      if (hash.get(integer) >= nextInt) {
        count++;
      }
    }
    List<Integer> collect = hash.entrySet().stream().filter(e -> e.getValue() >= nextInt).sorted((o1, o2) -> {
      return o2.getValue() - o1.getValue() == 0 ? o1.getKey() - o2.getKey() : o2.getValue() - o1.getValue();
    }).map(e -> e.getKey()).collect(Collectors.toList());

    System.out.println(count);
    collect.forEach(System.out::println);
  }

}
