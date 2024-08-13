package com.suanfa.leetcode;

public class cutTwoChild {
  static boolean flag = false;

  public static void main(String[] args) {
    canPartition(new int[]{1,5,11,5});
    System.out.println(flag);
  }

  public static boolean canPartition(int[] nums) {
    dp(nums, 0, 0, 0);
    return false;
  }

  public boolean canPartitionDP(int[] nums) {
    int n = nums.length;

    //「等和子集」的和必然是总和的一半
    int sum = 0;
    for (int i : nums) sum += i;
    int target = sum / 2;

    // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
    if (target * 2 != sum) return false;

    int[][] f = new int[n][target + 1];
    // 先处理考虑第 1 件物品的情况
    for (int j = 0; j <= target; j++) {
      f[0][j] = j >= nums[0] ? nums[0] : 0;
    }

    // 再处理考虑其余物品的情况
    for (int i = 1; i < n; i++) {
      int t = nums[i];
      for (int j = 0; j <= target; j++) {
        // 不选第 i 件物品
        int no = f[i-1][j];
        // 选第 i 件物品
        int yes = j >= t ? f[i-1][j-t] + t : 0;
        f[i][j] = Math.max(no, yes);
      }
    }
    // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
    return f[n-1][target] == target;
  }

  private static void dp(int[] nums, int i, int sum1, int sum2) {
    if(i == nums.length){
      if(sum1 == sum2){
        flag = true;
      }
      return;
    }
    dp(nums, i + 1, sum1 + nums[i], sum2);
    dp(nums, i + 1, sum1, sum2+ nums[i]);
  }
}
