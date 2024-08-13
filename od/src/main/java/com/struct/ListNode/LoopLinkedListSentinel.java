//package com.struct.ListNode;
//
//import com.sun.istack.internal.Nullable;
//
//import java.util.LinkedList;
//
///**
// * 环形链表
// */
//public class LoopLinkedListSentinel {
//  /**
//   * Check that the given {@code CharSequence} is neither {@code null} nor
//   * of length 0.
//   * <p>Note: this method returns {@code true} for a {@code CharSequence}
//   * that purely consists of whitespace.
//   * <p><pre>{@code
//   * StringUtils.hasLength(null) = false
//   * StringUtils.hasLength("") = false
//   * StringUtils.hasLength(" ") = true
//   * StringUtils.hasLength("Hello") = true
//   * }
//   * </pre>
//   * @param str the {@code CharSequence} to check (may be {@code null})
//   * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
//   * @see #f(int)
//   */
//  public static boolean hasLength(@Nullable CharSequence str) {
//    return (str != null && str.length() > 0);
//  }
//
//  /**
//   * 阶乘递归
//   * @param n 终点值
//   * @return 阶乘值
//   */
//  public static int f (int n){
//    if(n == 1){
//      return 1;
//    }
//    return n * f(n - 1);
//  }
//
//  /**
//   * 字符串逆序递归
//   * @param n 索引值
//   * @param str 字符串
//   */
//  public static void f1(int n, String str){
//    if(n == str.length()){
//      return;
//    }
//    f1(n + 1, str);
//    System.out.println(str.charAt(n));
//  }
//
//  /**
//   * 二分递归
//   * @author RJD
//   * @since JDK 1.8
//   * @param a 数组
//   * @param target 当前值
//   * @param i 左指针
//   * @param j 右指针
//   * @return 目标值
//   */
//  public static int f2(int[] a, int target, int i, int j){
//    if(i > j){
//      return -1;
//    }
//    int m = (i + j) >>> 1;
//    if (target < a[m]){
//      return f2(a,target,i,m-1);
//    } else if (a[m] < target){
//      return f2(a,target,m+1,j);
//    } else {
//      return m;
//    }
//  }
//
//  /**
//   * 汉诺塔
//   * @param n 层数
//   * @param a 柱子1
//   * @param b 柱子2
//   * @param c 柱子3
//   */
//  public static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c){
//    if(n == 0){
//      return;
//    }
//    move(n - 1,a, c, b);
//    c.addLast(a.removeLast());
//    move(n - 1, b, a ,c);
//  }
//
//  /**
//   * 杨辉三角
//   * {@link com.struct.ListNode.LoopLinkedListSentinel#triangle(int,int)}
//   * @param i
//   * @param j
//   * @return
//   */
//  public static int triangle(int i, int j){
//    if(j == 0 || i == j){
//      return i;
//    }
//    return triangle(i - 1, j - 1) + triangle(i - 1, j);
//  }
//  /*
//   * 尾调用
//   * funtion a(){
//   *   return b();
//   * }
//   */
//
//  /**
//   * 冒泡递归
//   * @param a {@code a} 数组
//   * @param j 排序右边界
//   */
//  public static void f3(int[] a, int j){
//    if(j == 0){
//      return;
//    }
//    for (int i = 0; i < j; i++) {
//      if(a[i] > a[i + 1]){
//        int temp = a[i];
//        a[i] = a[i + 1];
//        a[i + 1] = temp;
//      }
//    }
//    f3(a,j - 1);
//  }
//
//  public static void printTwo(int n){
//    int[] row = new int[n];
//    for (int i = 0; i < n; i++) {
//      createRow(row, i);
//      for (int j = 0; j <= i; j++) {
//        System.out.printf("%-4d", row[j]);
//      }
//      System.out.println();
//    }
//  }
//
//  /**
//   * 杨辉三角
//   * @param row
//   * @param i
//   */
//  public static void createRow(int[] row, int i){
//    if(i == 0){
//      row[0] = 1;
//      return;
//    }
//
//    for (int j = i; j < 0; j--) {
//      row[j] = row[j - 1] + row[j];
//    }
//  }
//}
