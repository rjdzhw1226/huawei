package com.suanfa.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//最长回文串
public class longestTenet {
  public static void main(String[] args) {
    System.out.println(longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
  }

  public static int longestPalindrome(String s) {
    Map<Character, Integer> hash = new HashMap<>();
    if(s.length() == 0){
      return 0;
    }
    for (char c : s.toCharArray()) {
      if(hash.get(c) == null){
        hash.put(c, 1);
      } else {
        hash.put(c, hash.get(c) + 1);
      }
    }
    int sum = hash.entrySet().stream().filter(e -> e.getValue() % 2 == 0).map(Map.Entry::getValue).mapToInt(Integer::intValue).sum();
    int sumo = 0;
    if(hash.entrySet().stream().filter(e -> e.getValue() % 2 != 0).collect(Collectors.toList()).size() != 0){
      sumo = hash.entrySet().stream().filter(e -> e.getValue() % 2 != 0).map(e -> e.getValue() - 1).mapToInt(Integer::intValue).sum();
      return sum + sumo + 1;
    } else {
      return sum + sumo;
    }
  }
}
