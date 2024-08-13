package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class carStream {
  public static void main(String[] args) {
    Integer[] ints = new Integer[]{1,2,3,4,5};

  }
  static class car{
    int position;
    double time;

    public car(int position, double time) {
      this.position = position;
      this.time = time;
    }
  }
  //车队
  public int carFleet(int target, int[] position, int[] speed) {
    List<car> list = new ArrayList<>();
    for (int i = 0; i < position.length; i++) {
      list.add(new car(position[i], (double) (target - position[i]) / speed[i]));
    }
    list = list.stream().sorted((o1, o2) -> o2.position - o1.position).collect(Collectors.toList());

    int ans = 0;
    int t = position.length;
    while (--t > 0){
      if(list.get(t).time < list.get(t -1).time){
        ans++;
      } else {
        list.remove(t -1);
        list.add(t -1, list.get(t));
      }
    }
    return ans + (t == 0 ? 1 : 0);
  }
}
