package com.suanfa.leetcode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;

public class findOnlyOne {
  //任何数异或自己等于0
  //0异或任何数等于任何数
  public static void main(String[] args) {
    int[] n = new int[]{1,2,1,2,0,0,3,3,4};
//    Random r = new Random();
//    int i1 = r.nextInt(n.length) - 1;
//    System.out.println(i1);

//    findOrange(new int[][]{
//      {2,1,1},
//      {1,0,1},
//      {1,1,0},
//      {2,0,1}});
    int sum = Integer.MAX_VALUE;
    for (int i = 0; i < n.length - 1; i++) {
      if(i == 0){
        sum = n[i] ^ n[i + 1];
      } else {
        sum = sum ^ n[i + 1];
      }
    }
    System.out.println(sum);
  }

  //一个数在一个长为n的数组中出现 超过或者等于 2/n 次 叫做多数的数
  public int majorityElement(int[] nums) {
    int limit = nums.length >> 1;
    int target = 0;
    Hashtable<Integer, Integer> hash = new Hashtable<>();
    for (int num : nums) {
      hash.merge(num, 1, Integer::sum);
      if((target = hash.get(num)) >= limit){
        break;
      }
    }
    return target;
  }
  static class grid{
    int x;
    int y;
    int val;

    public grid(){

    }

    public grid(int x, int y, int val) {
      this.x = x;
      this.y = y;
      this.val = val;
    }

    @Override
    public String toString() {
      return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("val", val)
        .toString();
    }
  }

  public static void findOrange(int[][] grid){
    grid[][] nums = init(grid);
    int[][] path = new int[][]{{1,0},{0,1},{-1,0},{-1,0}};
    int[][] visited = new int[grid.length][grid[0].length];

    //遍历到一个点先判断是否孤立和越界
    //先判断访问过没
    //只需判断是否当前下标
    Queue<grid> queue = new LinkedList<>();
    queue.add(nums[0][0]);

    while (!queue.isEmpty()){
      grid poll = queue.poll();
      if(visited[poll.x][poll.y] == 0){
        if(poll.val == 2){
          for (int[] ints : path) {
            int row = poll.x + ints[0];
            int col = poll.y + ints[1];
            if(row >= 0 && row < nums.length && col >= 0 && col < nums[0].length){
              if(visited[row][col] == 0){
                if(nums[row][col].val == 1){
                  nums[row][col].val = 2;
                }
                queue.add(nums[row][col]);
              }
            }
          }
        }
        visited[poll.x][poll.y] = 1;
      }
    }
    for (findOnlyOne.grid[] num : nums) {
      System.out.println(Arrays.toString(num));
      //接校验方法 如果无新鲜桔子则通过 有则-1
    }
  }

  private static grid[][] init(int[][] grid) {
    grid[][] nums = new grid[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        nums[i][j] = new grid(i,j,grid[i][j]);
      }
    }
    return nums;
  }
}
