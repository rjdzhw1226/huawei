package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class cutPartition {
  public static void main(String[] args) {
    System.out.println(partition("aab"));
  }

  static List<List<String>> ans = new ArrayList<>();
  public static List<List<String>> partition(String s) {
    dfs(s, 0, s.length());
    return ans;
  }

  static List<String> res = new ArrayList<>();
  private static void dfs(String s, int i, int len) {
    if(i == len){
      ans.add(new ArrayList<>(res));
      return;
    }
    for (int j = i; j < len; j++) {
      String substring = s.substring(i, j + 1);
      if(checkBack(substring)){
        res.add(substring);
      } else {
        continue;
      }
      dfs(s, i + 1, len);
      res.remove(res.size() - 1);
    }
  }

  public static boolean checkBack(String str) {
    if(str.length() == 1){
      return true;
    }
    int fir = 0;
    int las = str.length() - 1;
    while (las - fir > 0){
      if (str.charAt(fir) != str.charAt(las)) {
        return false;
      }
      fir++;
      las--;
    }
    return true;
  }
}
