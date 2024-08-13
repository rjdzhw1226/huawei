package com.suanfa.gpthelp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Import {

  Map<String, Convert> map = new HashMap<>();
  public Import() {
  }

  public interface Convert{
    String convert(String value);
  }

  public void putConvert(String s, Convert convert){
    map.put(s,convert);
  }

  public static void main(String[] args) {
//    List<Integer> list1 = new ArrayList<>();
//    List<Integer> list2 = new ArrayList<>();
//    // 1 2 3
//    // 1 2 3
//    list2.add(1);
//    list2.add(2);
//    list2.add(3);
//    list1.add(1);
//    list1.add(2);
//    list1.add(3);
//    List<int[]> collect = list1.stream()
//      .flatMap(value -> list2.stream().map(v -> Arrays.stream(new int[]{value, v}).sorted().toArray()))
//      .collect(Collectors.toList());
//    collect.forEach(x ->{
//      System.out.println(Arrays.toString(x));
//    });


    List<String> list = Arrays.asList("2 1 3 4".split(" "));
    System.out.println(list);
    list.stream()
      .flatMap(str -> list.stream().map(str::concat))
      .flatMap(str -> list.stream().map(str::concat))
      .map(Import::filter).filter(v -> v.length() == 3).map(Integer::valueOf)
      .collect(Collectors.toList())
      .forEach(System.out::println);
  }

  public static String filter(String s) {
    if (s == null) {
      return null;
    }
    StringBuilder sbd = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (s.indexOf(c) == s.lastIndexOf(c)) {
        sbd.append(c);
      } else {
        if (s.indexOf(c) == i) {
          sbd.append(c);
        }
      }
    }
    return sbd.toString();
  }

}
