package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

//子集
public class childNum {
  //输入：nums = [1,2,3]
  //输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
  public static void main(String[] args) {
    System.out.println(subsets(new int[]{1, 2, 3}));
  }
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    //添加一个空的
    ans.add(new ArrayList<>());
    for (int num : nums) {
      int size = ans.size();
      for (int i = 0; i < size; i++) {
        List<Integer> temp = new ArrayList<>(ans.get(i));
        temp.add(num);
        ans.add(temp);
      }
    }
    return ans;
  }

  public static void recursion(int[] nums, int i, List<List<Integer>> res){
    if(i >= nums.length){
      return;
    }
    int size = res.size();
    for (int j = 0; j < size; j++) {
      List<Integer> sub = new ArrayList<>(res.get(j));
      sub.add(nums[j]);
      res.add(sub);
    }
    recursion(nums, i + 1, res);
  }

  public List<List<Integer>> subsetsQ(int[] nums) {
    ArrayList<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    Consumer<Integer> consumer = new Consumer<Integer>() {
      @Override
      public void accept(Integer s) {
        ans.add(new ArrayList<>(path));
        for (int i = s; i < nums.length; i++) {
          path.add(nums[i]);
          accept(i + 1);
          path.removeLast();
        }
      }
    };
    consumer.accept(0);
    return ans;
  }
}
