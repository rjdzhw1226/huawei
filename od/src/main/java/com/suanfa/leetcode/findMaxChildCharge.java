package com.suanfa.leetcode;

import java.util.*;

public class findMaxChildCharge {
  //2813 子序列最大优雅度
  public long findMaximumElegance(int[][] items, int k) {
    Arrays.sort(items, (a, b) -> b[0] - a[0]);
    long ans = 0;
    long totalProfit = 0;
    Set<Integer> vis = new HashSet<>();
    Deque<Integer> duplicate = new ArrayDeque<>();
    for (int i = 0; i < items.length; i++) {
      int profit = items[i][0];
      int category = items[i][1];
      if(i < k){
        totalProfit += profit;
        if(!vis.add(category)){
          duplicate.push(profit);
        }
      } else if(!duplicate.isEmpty() && vis.add(category)) {
        totalProfit += profit - duplicate.pop();
      }
      ans = Math.max(ans, totalProfit + (long) vis.size() * vis.size());
    }
    return ans;
  }

  //2786. 访问数组中的位置使分数最大
  public long maxScore(int[] nums, int x) {
    long res = nums[0];
    long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
    dp[nums[0] % 2] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int parity = (int) (nums[i] % 2);
      long cur = Math.max(dp[parity] + nums[i], dp[1 - parity] - x + nums[i]);
      res = Math.max(res, cur);
      dp[parity] = Math.max(dp[parity], cur);
    }
    return res;
  }

  long[][] m;
  public long maxScoreQ(int[] nums, int x) {
    m = new long[nums.length][2];
    for (long[] ms : m) {
      Arrays.fill(ms, -1);
    }
    return dfs(0,nums,nums[0] % 2, x);
  }

  private long dfs(int i, int[] nums, int i1, int x) {
    if(i == nums.length){
      return 0;
    }
    if(m[i][i1] != -1){
      return m[i][i1];
    }
    if(nums[i] % 2 != i1){
      m[i][i1] = dfs(i + 1, nums, i1, x);
      return m[i][i1];
    }
    long res1 = dfs(i + 1, nums, i1, x);
    long res2 = dfs(i + 1, nums, i1 ^ 1, x);
    return Math.max(res1, res2 - x) + nums[i];
  }


  //521 最长特殊子序列
  public int findLUSlength(String a, String b) {
    return !a.equals(b) ? Math.max(a.length(), b.length()) : -1;
  }

  public int findLUSlength(String[] strs) {
    int ans = -1;
    next:
    for (int i = 0; i < strs.length; i++) {
      if (strs[i].length() <= ans) { // 不会让 ans 变大
        continue;
      }
      for (int j = 0; j < strs.length; j++) {
        if (j != i && isSubseq(strs[i], strs[j])) {
          continue next;
        }
      }
      ans = strs[i].length();
    }
    return ans;
  }

  // 判断 s 是否为 t 的子序列
  private boolean isSubseq(String s, String t) {
    int i = 0;
    for (char c : t.toCharArray()) {
      if (s.charAt(i) == c && ++i == s.length()) { // 所有字符匹配完毕
        return true; // s 是 t 的子序列
      }
    }
    return false;
  }
}
