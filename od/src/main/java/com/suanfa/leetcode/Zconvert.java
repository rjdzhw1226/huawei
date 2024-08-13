package com.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//z字变换 主要利用数组下标索引
public class Zconvert {
  public static void main(String[] args) {
    System.out.println(convert("AB", 2));
  }
  public static String convert(String s, int numRows) {
    List<List<String>> ans = new ArrayList<>(numRows);
    if(numRows == 1){
      return s;
    }
    for (int i = 0; i < numRows; i++) {
      List<String> list = new ArrayList<>();
      ans.add(list);
    }
    int count = 0;
    boolean flag = false;
    for (char c : s.toCharArray()) {
      if(!flag){
        ans.get(count).add(c + "");
        count++;
        if(count == numRows){
          flag = true;
          count--;
        }
      } else {
        count--;
        ans.get(count).add(c + "");
        if(count == 0){
          flag = false;
          count++;
        }
      }
    }
    return ans.stream().flatMap(Collection::stream).collect(Collectors.joining());
  }
}
