package com.suanfa.test;

import java.util.*;

public class TestList08 {
  static class Person{
    Integer val;
    Integer index;

    public Person() {
    }

    public Person(Integer val, Integer index) {
      this.val = val;
      this.index = index;
    }

    @Override
    public String toString() {
      return "Person{" +
        "val=" + val +
        ", index=" + index +
        '}';
    }
  }
  public static void main(String[] args) {
    List<Person> rest = init(100);
    while (rest.size() != 1){
      for(Iterator<Person> it = rest.iterator(); it.hasNext();){
        Person per = it.next();
        if(per.index % 2 != 0){
          it.remove();
        }
      }
      System.out.println(rest);
      rest = init(rest.size(), rest);
    }
  }

  private static List<Person> init(int size) {
    List<Person> ans = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      Person person = new Person(i, i);
      ans.add(person);
    }
    return ans;

  }

  private static List<Person> init(int size, List<Person> list) {
    List<Person> ans = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      Person person = new Person(list.get(i - 1).val, i);
      ans.add(person);
    }
    return ans;
  }

  /**
   * 题目描述: 对于任意两个正整数A和B，定义它们之间的差异值和相似值:
   * 差异值: A、B转换成二进制后，对于二进制的每一位，对应位置的bit值不相同则为1，否则为0;
   * 相似值: A、B转换成二进制后，对于二进制的每一位，对应位置的bit值都为1则为1，否则为0;
   * 现在有n个正整数A0 到A(n-1)，向有多少对(i,j)(0 <= i <j< n)，Ai和Aj的差异值大于相似值.
   * 假设A=5,B=3:则A的二进制表示101: B的二进制表示011:
   * 则A与B的差异值二进制为110,相似值二进制为001; A与B的差异值十进制等于6，相似值十进制等于1，满足条件。
   */
//  public static void main(String[] args) {
//
//    int[] num = new int[]{2, 3, 7, 9, 11, 13, 17, 8};
//
//    for (int i = 0; i < num.length; i++) {
//      for (int j = i + 1; j < num.length; j++) {
//        if (compare(num, i, j)) {
//          System.out.println(i+":"+j);
//        }
//      }
//    }
//
//  }

  private static char[] init2(int length) {
    char[] chars = new char[length];
    Arrays.fill(chars, '0');
    return chars;
  }

  private static boolean compare(int[] num, int i, int j) {
    char[] chars1 = Integer.toBinaryString(num[i]).toCharArray();
    char[] chars2 = Integer.toBinaryString(num[j]).toCharArray();
    int res = chars1.length;
    if(chars1.length > chars2.length){
      char[] newChar = init2(chars1.length);
      System.arraycopy(chars2, 0, newChar, chars1.length - chars2.length, chars2.length);
      chars2 = newChar;
    } else if((res = chars2.length) > chars1.length){
      char[] newChar = init2(chars2.length);
      System.arraycopy(chars1, 0, newChar, chars2.length - chars1.length, chars1.length);
      chars1 = newChar;
    }
    int count = 0;
    String diff = "";
    String sim = "";
    while (count != res){
      int a = chars1[count] ^ chars2[count];
      int b = (chars1[count] == '1') && (chars2[count] == '1') ? 1 : 0;
      diff = diff + a;
      sim = sim + b;
      count++;
    }
    if(Integer.parseInt(diff) > Integer.parseInt(sim)){
      return true;
    } else {
      return false;
    }
  }

  public static void soutArray(char[] c){
    for (int i = 0; i < c.length; i++) {
      System.out.printf("%c,",c[i]);
    }
  }

  public static String finalString(String s) {
    Stack<Character> stack = new Stack<>();
    List<Character> res = new ArrayList<>();
    char[] chars = s.toCharArray();
    for (char aChar : chars) {
      if(aChar == 'i'){
        while (!stack.isEmpty()){
          res.add(stack.pop());
        }
      } else {
        stack.push(aChar);
      }
    }
    StringBuilder sb = new StringBuilder();
    StringBuilder be = new StringBuilder();

    for (Character re : res) {
      be.append(re);
    }

    while (!stack.isEmpty()){
      sb.append(stack.pop());
    }

    return be.append(sb.reverse()).toString();
  }
}
