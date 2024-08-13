package com.suanfa.test;

import java.util.*;
//智能成绩表
public class Test52 {
  //题目描述
  //小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗?
  //输入描述
  //第1行输入两个整数，学生人数n和科目数量 m。
  //0<n<100
  //0<m<10
  //第 2 行输入 m 个科目名称，彼此之间用空格隔开。
  //科目名称只由英文字母构成，单个长度不超过10个字符
  //科目的出现顺序和后续输入的学生成绩一一对应。
  //，不会出现重复的科目名称，
  //第 3 行开始的n行，每行包含一个学生的姓名和该生 m 个科目的成绩(空格隔开)
  //学生不会重名。
  //学生姓名只由英文字母构成，长度不超过10个字符
  //。成绩是0~100的整数，依次对应第2行种输入的科目
  //第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
  //输出描述
  //输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int count = in.nextInt();
    int subCount = in.nextInt();
    //加一行堵在这
    in.nextLine();
    String[] sub = in.nextLine().split(" ");

    List<String> res = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      String sc = in.nextLine();
      res.add(sc);
    }
    boolean flag = false;
    int key = 0;
    String sort = in.nextLine();
    for (String s : sub) {
      if(s.equals(sort)){
        flag = true;
        break;
      }
      key++;
    }
    if(flag){
      //按科目排序

    } else {
      //按总分排序

    }


  }

}
