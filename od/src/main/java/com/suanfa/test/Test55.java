package com.suanfa.test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//提取字符串中的最长合法数学表达式
public class Test55 {
  //题目描述
  //提取字符串中的最长合法简单 数学表达式Q，字符串长度最长的，并计算表达式的值。如果没有，则返回0。简单数学表达式只能包含以下内容:
  //。0-9数字，符号+-*
  //说明:
  //1.所有数字，计算结果都不超过long
  //2.如果有多个长度一样的，请返回第一个表达式的结果!
  //3.数学表达式，必须是最长的，合法的
  //4.操作符不能连续出现，如 +--+1 是不合法的
  //输入描述
  //字符串
  //输出描述
  //表达式值
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String str = in.nextLine();

    char[] array = str.toCharArray();
    Pattern compile = Pattern.compile("[0-9+\\-*]+");
    Matcher matcher = compile.matcher(str);
    if(!matcher.find()){
      return;
    }
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < array.length; i++) {
      if (compile.matcher(String.valueOf(array[i])).find()) {
        stack.push(array[i]);
      } else {
        stack.push('.');
      }
    }
    switchFollow(stack);

  }

  static List<String> res = new ArrayList<>();

  private static void switchFollow(Stack<Character> stack) {
    char a;
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      if ((a = stack.pop()) != '.') {
        sb.append(a);
      } else {
        if(sb.length() > 0){
          res.add(sb.reverse().toString());
        }
        sb.setLength(0);
      }
    }
    if(sb.length() > 0){
      res.add(sb.reverse().toString());
    }
    Pattern compile = Pattern.compile("(\\d+)([*+-])([*+-])(\\d+)");
    String s = res.stream().filter(e -> !compile.matcher(e).find()).sorted((o1, o2) -> o2.length() - o1.length()).limit(1).collect(Collectors.toList()).get(0);

    //    long i = calculateInfixExpression(s);
    long i = valueString(s);
    System.out.println(i);
  }

  /*String[] nums = s.split("[+\\-*]");
    ints.addAll(Arrays.asList(nums));
    for (char c : s.toCharArray()) {
      if(c == '+' || c == '-' || c == '*'){
        chars.push(String.valueOf(c));
      }
    }
    System.out.println(chars);
    System.out.println(ints);*/
  private static long valueString(String s) {
    Stack<String> chars = new Stack<>();
    Stack<String> ints = new Stack<>();
    int flag = 0; //上一个是空或者符号则为0
    int isNegative = 0; //是一就把数字变负数
    int i = 0;
    int len = s.length();
    char ch; //当前字符
    String op; //运算符
    int num1; //操作数右
    int num2; //操作数左
    int res; //运算结果
    while (i < len) {
      ch = s.charAt(i);
      //当前是操作数
      if(ch >= 48 && ch <= 57){
        String str = "" + ch;
        //判断右边是不是还是数字
        while (i + 1 < len){
          ch = s.charAt(i + 1);
          if(ch < 48 || ch > 57){
            break;
          } else {
            i++;
            str += ch;
          }
        }
        if(isNegative == 1) {
          str = "-" + str;
          isNegative = 0;
        }
        ints.push(str);
        flag = 1;
      //当前是运算符
      } else {
        if(ch == '-') {
          if(flag == 0) {
            isNegative = 1;
          } else {
            if(chars.isEmpty()) {
              chars.push("" + ch);
            } else {
              String currOp = "" + ch;
              String stackOp = chars.peek();
              if(priorityOd(currOp) > priorityOd(stackOp)){
                chars.push(currOp);
              } else {
                while (!chars.isEmpty() && priorityOd(currOp) <= priorityOd(stackOp)) {
                  op = chars.pop();
                  num1 = Integer.parseInt(chars.pop());
                  num2 = Integer.parseInt(chars.pop());
                  res = calculate(num2, op, num1);
                  ints.push("" + res);
                }
                chars.push(currOp);
              }
            }
            flag = 0;
          }
        } else if (ch == '+' || ch == '%' || ch == '*' || ch == '/') {
          //不是 - 不用判断负数
          if(chars.isEmpty()) {
            chars.push("" + ch);
          } else {
            String currOp = "" + ch;
            String stackOp = chars.peek();
            if(priorityOd(currOp) > priorityOd(stackOp)){
              chars.push(currOp);
            } else {
              while (!chars.isEmpty() && priorityOd(currOp) <= priorityOd(stackOp)) {
                op = chars.pop();
                num1 = Integer.parseInt(ints.pop());
                num2 = Integer.parseInt(ints.pop());
                res = calculate(num2, op, num1);
                ints.push("" + res);
              }
              chars.push(currOp);
            }
          }
          flag = 0;
        } else {
          throw new RuntimeException("无法识别" + ch);
        }
      }
      i++;
    }
    while (!chars.isEmpty()) {
      op = chars.pop();
      num1 = Integer.parseInt(ints.pop());
      num2 = Integer.parseInt(ints.pop());
      res = calculate(num2, op, num1);
      ints.push("" + res);
    }
    res = Integer.parseInt(ints.pop());
    return res;
  }

  //计算中缀表达式
  public static int calculateInfixExpression(String expression) {
    //定义两个栈(操作数栈、运算符栈)
    Stack<String> s1 = new Stack<String>();
    Stack<String> s2 = new Stack<String>();
    //遍历表达式
    int flag = 0;//上一个是空或者符号则为0
    int isNegative = 0;//是一就把数字变负数
    int i = 0;
    int len = expression.length();
    char ch;     //当前字符
    String op;   //运算符
    int num1;     //操作数1
    int num2;    //操作数2
    int res;     //运算结果
    while (i < len) {
      ch = expression.charAt(i);
      //判断当前字符是操作数还是运算符
      //System.out.println(ch);
      if (ch >= 48 && ch <= 57) {
        //当前字符是操作数，则直接入栈
        String str = "" + ch;
        while (i + 1 < len) {
          ch = expression.charAt(i + 1);
          if (ch < 48 || ch > 57) {
            break;
          } else {
            i++;
            str += ch;
          }
        }
        if (isNegative == 1) {
          str = "-" + str;
          isNegative = 0;
        }
        s1.push(str);
        flag = 1;
      } else {
        //当前字符是运算符，则判断是 左括号 或 右括号 或 + - * /
        if (ch == '(') {
          //直接入栈
          s2.push("" + ch);
          flag = 0;
        } else if (ch == ')') {
          //出栈并运算，直到遇到左括号，最后丢弃这对括号
          while (!s2.peek().equals("(")) {
            op = s2.pop();
            num1 = Integer.parseInt(s1.pop());
            num2 = Integer.parseInt(s1.pop());
            //计算 num2 op num1 并将结果压入栈, 注意传参的顺序
            res = calculate(num2, op, num1);
            s1.push("" + res);
          }
          //丢弃这对括号
          s2.pop();
        } else if (ch == '-') {
          if (flag == 0) {
            isNegative = 1;
          } else {
            if (s2.empty()) {
              s2.push("" + ch);
            } else {
              //判断优先级
              String currentOp = "" + ch;
              String stackTopOp = s2.peek();
              //当前运算符 > 栈顶运算符，直接入栈
              if (priority(currentOp) > priority(stackTopOp)) {
                s2.push(currentOp);
              } else {
                //当前运算符 <= 栈顶运算符，不断弹出栈顶运算符并计算，直到当前 运算符 >栈顶运算符 或 栈为空
                while (!s2.empty() && priority(currentOp) <= priority(s2.peek())) {
                  op = s2.pop();
                  num1 = Integer.parseInt(s1.pop());
                  num2 = Integer.parseInt(s1.pop());
                  res = calculate(num2, op, num1);
                  s1.push("" + res);
                }
                //将当前运算符压入栈
                s2.push(currentOp);
              }
            }
            flag = 0;
          }
        } else if (isOper(ch)) {
          //如果是 + - * /, 则需要跟栈顶运算符判断优先级(先判断栈是否为空，为空就直接入栈)
          if (s2.empty()) {
            s2.push("" + ch);
          } else {
            //判断优先级
            String currentOp = "" + ch;
            String stackTopOp = s2.peek();
            //当前运算符 > 栈顶运算符，直接入栈
            if (priority(currentOp) > priority(stackTopOp)) {
              s2.push(currentOp);
            } else {
              //当前运算符 <= 栈顶运算符，不断弹出栈顶运算符并计算，直到当前 运算符 >栈顶运算符 或 栈为空
              while (!s2.empty() && priority(currentOp) <= priority(s2.peek())) {
                op = s2.pop();
                num1 = Integer.parseInt(s1.pop());
                num2 = Integer.parseInt(s1.pop());
                res = calculate(num2, op, num1);
                s1.push("" + res);
              }
              //将当前运算符压入栈
              s2.push(currentOp);
            }
          }
          flag = 0;
        } else {
          //遇到无法识别字符则抛出异常
          throw new RuntimeException("无法识别" + ch);
        }
      }
      i++;
    }
    //将剩下的操作符逐一弹出栈并计算
    while (!s2.empty()) {
      op = s2.pop();
      num1 = Integer.parseInt(s1.pop());
      num2 = Integer.parseInt(s1.pop());
      res = calculate(num2, op, num1);
      s1.push("" + res);
    }
    res = Integer.parseInt(s1.pop());
    return res;
  }

  //判断是不是运算符
  public static boolean isOper(char op) {//这里不对减号进行判断，因为减号可能是负号，计算时单独判断
    return op == '+' || op == '%' || op == '*' || op == '/';
  }

  //计算 num1 op num2
  public static int calculate(int num1, String op, int num2) {
    int res = 0;
    switch (op) {
      case "+":
        res = num1 + num2;
        break;
      case "-":
        res = num1 - num2;
        break;
      case "*":
        res = num1 * num2;
        break;
      case "/":
        res = num1 / num2;
        break;
      case "%":
        res = num1 % num2;
        break;
      default:
        break;
    }
    return res;
  }

  //返回运算符的优先级
  public static int priority(String op) {
    switch (op) {
      case "+":
      case "-":
        return 1;
      case "*":
      case "/":
      case "%":
        return 2;
      case "(":
        return 0;
      default:
        return -1;
    }
  }

  public static int priorityOd(String op) {
    switch (op) {
      case "+":
      case "-":
        return 1;
      case "*":
        return 2;
      default:
        return -1;
    }
  }


}
