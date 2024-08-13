package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

//无重复字符的最长子串
public class lengthsOfLongest {

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("abcabcbb"));
  }

  public static int lengthOfLongestSubstring(String s) {
    LinkedList<Character> list = new LinkedList<>();
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < s.toCharArray().length; i++) {
      char c = s.charAt(i);
      if(!list.contains(c)){
        list.addLast(c);
      } else {
        ans.add(list.size());
        while (list.contains(c)){
          list.removeFirst();
        }
        list.addLast(c);
      }
    }
    if(list.size() != 0) {
      ans.add(list.size());
    }
    return ans.stream().max((o1, o2) -> o1 - o2).orElse(0);
  }
}
