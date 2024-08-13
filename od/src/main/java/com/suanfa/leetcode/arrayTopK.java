package com.suanfa.leetcode;

import java.util.PriorityQueue;

public class arrayTopK {
  public int findKthLargest(int[] nums, int k){
    PriorityQueue<Integer> queue = new PriorityQueue<>();//默认为小根堆
//    PriorityQueue<Integer> queueBig = new PriorityQueue<>(((o1, o2) -> o2  - o1));//重写比较成为大根堆
    for (int i = 0; i < k; i++) {
      queue.offer(nums[i]);
    }
    for (int i = 0; i < nums.length; i++) {
      if(queue.peek() < nums[i]){
        queue.poll();
        queue.offer(nums[i]);
      }
    }
    return queue.peek();
  }

  public static void main(String[] args) {
    arrayTopK a = new arrayTopK();
    System.out.println(a.accountBalanceAfterPurchase(19));
  }

  public int accountBalanceAfterPurchase(int purchaseAmount) {
    int total = 100;
    int min = Integer.MAX_VALUE;
    int k = 0;
    int index = 0;
    for (int i = 0; i <= 90; i += 10) {
      if(min > (k = Math.min(Math.abs(purchaseAmount - i), Math.abs(purchaseAmount - (i + 10))))){
        min = k;
      }
    }
    String s = String.valueOf(purchaseAmount);
    String substring = s.substring(s.length() - 1);
    if(Integer.parseInt(substring) < 5){
      return total - (purchaseAmount - min);
    } else {
      return total - (min + purchaseAmount);
    }
  }
}
