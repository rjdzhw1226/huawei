package com.suanfa.test;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//密码输入检测
public class Test50 {
  //题目描述
  //给定用户密码 输入流input，输入流中字符'<'表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
  //密码安全要求如下:
  //1.密码长度 ≥8:
  //2.密码至少需要包含 1 个大写字母,
  //3.密码至少需要包含 1 个小写字母;
  //4.密码至少需要包含1 个数字;
  //5.密码至少需要包含 1 个字母和数字以外的非空白特殊字符;:
  //注意空串退格后仍然为空串，且用户输入只的字符串不包含''字符和空白字符。
  //输入描述
  //用一行字符串表示输入的用户数据，输入的字符串中''字符标识退格，用户输入的字符串不包含空白字符，例如:
  //ABC<c89%000<
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String line = in.nextLine();
    char[] chars = line.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char aChar : chars) {
      if(aChar == '<'){
        if(!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(aChar);
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    String string = sb.reverse().toString();
    boolean flag = false;
    Pattern compileBig = Pattern.compile("[A-Z]+");
    Pattern compileSmall = Pattern.compile("[a-z]+");
    Pattern compileNum = Pattern.compile("[0-9]+");
    Pattern compileSpecial = Pattern.compile("[[_`~!@#$%^&*()+=|{}:;,\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\n|\\r|\\t]+");
    Matcher matcher1 = compileBig.matcher(string);
    Matcher matcher2 = compileSmall.matcher(string);
    Matcher matcher3 = compileNum.matcher(string);
    Matcher matcher4 = compileSpecial.matcher(string);
    flag = matcher4.find() && matcher3.find() && matcher2.find() && matcher1.find() && string.length() >= 8;
    System.out.println(string + "," +flag);

  }
}
