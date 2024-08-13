package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//课程表
public class classTable {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] inDegrees = new int[numCourses];
    List<List<Integer>> adjacency = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      adjacency.add(new ArrayList<>());
    }
    for (int[] prerequisite : prerequisites) {
      inDegrees[prerequisite[0]]++;
      adjacency.get(prerequisite[1]).add(prerequisite[0]);
    }
    for (int i = 0; i < numCourses; i++) {
      if(inDegrees[i] == 0){
        queue.add(i);
      }
    }
    while (!queue.isEmpty()){
      int pre = queue.poll();
      numCourses--;
      for (Integer cur : adjacency.get(pre)) {
        if(--inDegrees[cur] == 0){
          queue.add(cur);
        }
      }
    }
    return numCourses == 0;
  }
}
