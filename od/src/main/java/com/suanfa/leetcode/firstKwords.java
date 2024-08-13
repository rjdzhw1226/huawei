package com.suanfa.leetcode;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class firstKwords {
  public static void main(String[] args) {
    String[] str = new String[]{"I","love","you","me","me","hello","love"};
    topKFrequent(str, 2);
  }
  //返回出现次数最多的前k个字符串
  public static List<String> topKFrequent(String[] words, int k) {
    Hashtable<String, Integer> hashtable = new Hashtable<>();
    for (String word : words) {
      if (hashtable.get(word) == null) {
        hashtable.put(word, 1);
      } else {
        hashtable.put(word, hashtable.get(word) + 1);
      }
    }
    List<String> collect = hashtable.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).limit(k).map(e -> e.getKey()).collect(Collectors.toList());
    return collect;
  }
}

