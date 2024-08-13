package com.suanfa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//最长的指定瑕疵度的元音子串
public class Test59 {

  static List<String> ans = new ArrayList<>();
  static Character[] chars = new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int count = Integer.parseInt(in.nextLine());
    String line = in.nextLine();

    List<Integer> index = new ArrayList<>();
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if(Arrays.asList(chars).contains(c)) {
        index.add(i);
      }
    }

    System.out.println(index);

    for (int i = 0; i < index.size(); i++) {
      for (int j = i; j < index.size(); j++) {
        int begin = index.get(i);
        int end = index.get(j);
        check(begin, end, line, count);
      }
    }

    System.out.println(ans.stream().max((o1, o2) -> o1.length() - o2.length()).orElse("0"));
  }

  //aabeebuu
  //
  //aeueo
  //
  private static void check(int begin, int end, String line, int count) {
    if (line.charAt(begin) == line.charAt(end)) {
      if (count == 0) {
        ans.add(line.charAt(begin) + "");
      }
    } else {
      String substring = line.substring(begin, end + 1);
      int id = 0;
      for (int i = 0; i < substring.length(); i++) {
        char c = substring.charAt(i);
        if (!Arrays.asList(chars).contains(c)) {
          id++;
        }
      }
      if(id == count){
        ans.add(substring);
      }
    }
  }


}
