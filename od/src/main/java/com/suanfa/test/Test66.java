package com.suanfa.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//执行时长
public class Test66 {
  static Queue<Integer> queue = new LinkedList<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int min = Integer.parseInt(in.nextLine());
    //未用到
    int count = Integer.parseInt(in.nextLine());

    int[] ints = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    for (int anInt : ints) {
      if(!queue.isEmpty()){
        anInt = queue.poll() + anInt;
      }
      if(anInt > min){
        queue.add(anInt - min);
      }
    }
    if(queue.isEmpty()){
      System.out.println(ints.length);
    } else {
      int ct = 0;
      Integer poll = queue.poll();
      while (poll > 0){
        poll -= min;
        ct++;
      }
      System.out.println(ints.length + ct);
    }

  }
}
