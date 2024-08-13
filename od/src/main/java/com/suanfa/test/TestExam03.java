package com.suanfa.test;

import java.util.*;
import java.util.regex.Pattern;
//模拟数据序列化传输 没做完 现在把编码 用栈取出来了 没想好怎么合并 todo
public class TestExam03 {
  //  12.5
/* public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int count = Integer.parseInt(in.nextLine());
    String line = in.nextLine();
    Pattern pattern2 = Pattern.compile("(\\])(,)(\\[)");
    boolean b = pattern2.matcher(line).find();
    if (!b){
      System.out.println("DECODE_ERROR");
    }
  }*/
  static Stack<String> stack = new Stack<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int count = Integer.parseInt(in.nextLine());
    String line = in.nextLine();
    if(count == 1){
      Pattern pattern2 = Pattern.compile("(\\])(,)(\\[)");
      boolean b = pattern2.matcher(line).find();
      if (!b){
        System.out.println("ENCODE_ERROR");
      }
      String temp = "";
      boolean f = true;
      int countKH = 0;
      int k = 0;
      char[] chars = line.toCharArray();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < chars.length; i++) {
        if(chars[i] == '['){
          if(!stack.isEmpty()){
            if(countKH == 0){
              stack.push(":");
              countKH += 1;
            }
          }
        }
        else if(chars[i] == ','){
          if(f){
            stack.push(temp);
          }
          temp = "";
        }
        else if(chars[i] == ']'){
          if(!temp.equals("")){
            stack.push(temp);
          }
          //弹出
          if(countKH > 0){
            if((i + 1) < chars.length && chars[i + 1] == ']'){
              while (!stack.isEmpty()){
                if(stack.peek().equals("String")){
                  stack.pop();
                  sb.append("1");
                } else if(stack.peek().equals("Integer")){
                  stack.pop();
                  sb.append("0");
                } else if(stack.peek().equals("Compose")){
                  stack.pop();
                  sb.append("2");
                } else {
                  sb.append(stack.pop());
                }
                sb.append("#");
              }
              System.out.println(Arrays.toString(findRight(sb.toString())));
              sb.setLength(0);
            } else {
              stack.push("$");
            }
          } else {
            while (!stack.isEmpty()){
              if(stack.peek().equals("String")){
                stack.pop();
                sb.append("1");
              } else if(stack.peek().equals("Integer")){
                stack.pop();
                sb.append("0");
              } else if(stack.peek().equals("Compose")){
                stack.pop();
                sb.append("2");
              } else {
                sb.append(stack.pop());
              }
              sb.append("#");
            }
            System.out.println(Arrays.toString(findRight(sb.toString())));
            sb.setLength(0);
          }
          f = false;
        }
        else {
          temp += chars[i] + "";
          f = true;
        }
      }
    } else {
      //解码
      Pattern pattern2 = Pattern.compile("^\\#");
      boolean b = pattern2.matcher(line).find();
      if (!b){
        System.out.println("DECODE_ERROR");
      }
    }
  }

  public static String[] findRight(String str){
    String[] split = str.split("#");
    return split;
  }
}
