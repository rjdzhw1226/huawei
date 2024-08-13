package com.suanfa.test;

import java.util.*;

//校招 路由器单板匹配 没看到完整题 可能理解有误 todo
public class Test69 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int i = Integer.parseInt(in.nextLine());
    int[] ints = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    sumFind(ints, 0, new LinkedList<Integer>(), new LinkedList<Integer>());
//    sumFindO(ints, 0, 0, 0);
  }

  private static void sumFind(int[] ints, int i, LinkedList<Integer> sum1, LinkedList<Integer> sum2) {
    if(i == ints.length){
      if (sum1.stream().mapToInt(Integer::intValue).sum() == sum2.stream().mapToInt(Integer::intValue).sum()) {
        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println();
        return;
      }
      return;
    }
    sum1.add(ints[i]);
    sumFind(ints, i + 1, sum1, sum2);
    sum1.removeLast();
    sum2.add(ints[i]);
    sumFind(ints, i + 1, sum1, sum2);
    sum2.removeLast();
  }

  private static void sumFindO(int[] ints, int i, int sum1, int sum2) {
    if(i == ints.length){
      if(sum1 == sum2){
        System.out.println(sum1);
      }
      return;
    }

    sumFindO(ints, i + 1, sum1 + ints[i], sum2);
    sumFindO(ints, i + 1, sum1, sum2 + ints[i]);
  }
}
