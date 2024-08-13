package com.suanfa.test;

import com.suanfa.gpthelp.Import;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.reflect.Array.getLength;
import static java.util.Collections.swap;

public class TestList04 {

  static class fileMenu{
    public int index;
    public int value;
    public List<Integer> next;

    public fileMenu() {
    }

    public fileMenu(int index, int value, List<Integer> next) {
      this.index = index;
      this.value = value;
      this.next = next;
    }
  }

  public void fileDemo(int n, List<String> list){
    List<fileMenu> fileMenuList = new ArrayList<>();
    for (String s1 : list) {
      int s2 = Integer.parseInt(s1.split(" ")[0]);
      int s3 = Integer.parseInt(s1.split(" ")[1]);
      String trim = s1.split(" ")[2].replace('(', ' ').replace(')', ' ').trim();
      List<Integer> listNumber = new ArrayList<>();
      if(trim.contains(",")){
        for (String s : trim.split(",")) {
          int s4 = Integer.parseInt(s);
          listNumber.add(s4);
        }
      }else {
        if(trim.equals("")){
          listNumber.add(null);
        }else {
          int s4 = Integer.parseInt(trim);
          listNumber.add(s4);
        }
      }
      fileMenuList.add(new fileMenu(s2,s3,listNumber));
    }
    diguiMethod(fileMenuList, n);
  }


  int sum;
  private void diguiMethod(List<fileMenu> fileMenuList, Integer k){
    if(k == null){
      System.out.println(sum);
      return;
    }
    for (int i = 0; i < fileMenuList.size(); i++) {
      if(fileMenuList.get(i).index == k){
        sum += fileMenuList.get(i).value;
        for (Integer integer : fileMenuList.get(i).next) {
          diguiMethod(fileMenuList, integer);
        }
      }
    }
  }

  // 4 20 0
  // 5 30 0
  // 2 10 4 5
  // 1 40 0
  public void fileDemoMatrix(List<List<Integer>> ints, int k){
    if(k == 0){
      System.out.println(sum);
      return;
    }
    List<Integer> anInt = ints.get(k - 1);
    if (anInt.size() >= 1) {
        sum += anInt.get(1);
        for (int i = 2; i < anInt.size(); i++) {
          fileDemoMatrix(ints, anInt.get(i));
        }
    }
  }

  public int fn(int n){
    if(n == 1){
      return 2;
    }
    if(n == 2 || n == 3){
      return 1;
    }
    return Math.min(fn(n - 2) + 1, fn(n - 3) + 1);

  }

  public void passHouse(String str, int count, List<Integer> list){
    String[] strings = str.substring(1, str.length() - 1).replaceAll(" ", "").split(",");
    int[] ints = new int[strings.length];
    for (int i = 0; i < strings.length; i++) {
      ints[i] = Integer.parseInt(strings[i]);
    }
    for (int i = 1; i <= strings.length; i++) {
      diguiNumber(ints, list, count, i, 0);
    }
  }
  private int addAll(List<Integer> list){
    int sum = 0;
    for (Integer integer : list) {
      sum += integer;
    }
    return sum;
  }
  private void diguiNumber(int[] ints, List<Integer> list, int count, int k, int j) {
    if(list.size() == k){
      if (addAll(list) == count) {
        System.out.println(list);
      }
      return;
    }
    for (int i = j; i < ints.length; i++) {
      list.add(ints[i]);
      diguiNumber(ints, list, count, k, i+1);
      list.remove(list.size() - 1);
    }
  }

  public void stackPaiLie(LinkedList<Integer> stack, int count, int j, int[] ints){
    if(stack.size() == count){
      System.out.println(stack);
      return;
    }
    for (int i = j; i < ints.length; i++) {
      stack.push(ints[i]);
      stackPaiLie(stack, count, i+1, ints);
      stack.pop();
    }
  }

  /**
   * %d	格式化输出整数
   * %f	格式化输出浮点数
   * %e	格式化科学计数法的浮点数
   * %s	格式化输出字符串
   * @param n
   * @param j
   */
  public static void printWord(int n, int j){
    for (int i = 1; i <= n; i++) {
      int count = i;
      if(count % 2 == 0){
        int k = j + count - 1;
        while (count > 0){
          System.out.printf("%4d", k);
          System.out.print("\t");
          count--;
          k--;
          j++;
        }
      } else {
        while (count > 0){
          System.out.printf("%4d", j);
          System.out.print("\t");
          count--;
          j++;
        }
      }

      System.out.println();
    }
  }


  /**
   * 单词接龙
   * @param k 表示起始单词在数组中的索引K
   * @param n 表示单词的个数N
   * @param str 输入的单词
   */
  public void DragonNext(int k, int n, List<String> str){
    String st = str.get(k);
    boolean flag = false;
    List<String> newList = new ArrayList<>();
    newList.addAll(str);
    newList.remove(k);
    int i = 0;

    for (int j = 0; j < newList.size(); j++) {
      newList.sort(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
          if(o2.length() == o1.length()){
            return o1.compareTo(o2);
          }else {
            return o2.length() - o1.length();
          }
        }
      });
      if (newList.get(i).charAt(0) == findLast(str.get(k))) {
        k = str.indexOf(newList.get(i));
        st = st + newList.get(i);
        newList.remove(i);
        j = 0;
      }
    }
    System.out.println(st);
  }
  public Character findLast(String str){
    return new StringBuilder(str).reverse().toString().charAt(0);
  }

  private void diguistr(List<String> str, List<String> newList, int k, String st) {
    if(newList.size() == 0){
      System.out.println(st);
      return;
    }
    newList.sort(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o2.length() - o1.length();
      }
    });
    for (int i = 0; i < newList.size(); i++) {
      if(newList.get(i).charAt(0) == findLast(str.get(k))){
        st = st + newList.get(i);
        int i1 = str.indexOf(newList.get(i));
        newList.remove(i);
        diguistr(str,newList,i1,st);
      }
    }
  }

  class Triple{
    public int key;
    public int x;
    public int y;
    public int value;

    public Triple(int key, int x, int y, int value){
      this.key = key;
      this.x = x;
      this.y = y;
      this.value = value;
    }

    @Override
    public String toString() {
      return "Triple{" +
        "key=" + key +
        ", x=" + x +
        ", y=" + y +
        ", value=" + value +
        '}';
    }
  }
  static int innerCount = 0;
  static int outerCount = 0;
  static boolean flag = false;
  static List<Integer> newList = new ArrayList<>();

  /**
   * 稀疏矩阵压缩
   * @param sparse
   * @param list
   * @return
   */
  public List<Triple> sparseMatrix(int[][] sparse, List<Triple> list){
    AtomicInteger count = new AtomicInteger(0);
    Arrays.stream(sparse).forEach(ints -> {
      for (int j = 0; j < ints.length; j++) {
        if(ints[j] != 0){
          Triple triple = new Triple(count.incrementAndGet(), innerCount, j, ints[j]);
          list.add(triple);
          outerCount = 0;
          flag = true;
        } else if(flag){
          outerCount++;
          flag = true;
          newList.add(outerCount);
        }
      }
      innerCount++;
    });
    Comparator<Integer> comparator = Comparator.comparing(Integer::intValue);
    Integer x = newList.stream().max(comparator).get();
    System.out.println(x);
    return list;
  }

  public void findAncestor(int n, int fir, List<String> s){
    if(fir == n){
      System.out.println(s);
    }
    for (int i = fir; i <= n ; i++) {
      swap(s,fir,i);
      findAncestor(n,fir+1,s);
      swap(s,fir,i);
    }
  }

  public int[] findDecNumber(int[] ints) {
    if(ints.length != 0){
      int middle = ints[0];
      int[] rest = (int[])remove(ints, 0);
      int[] small = findDecNumber(Arrays.stream(rest).filter(x -> x>= middle).toArray());
      int[] big = findDecNumber(Arrays.stream(rest).filter(x -> x <= middle).toArray());
      return IntStream.concat(IntStream.concat(Arrays.stream(small),IntStream.of(middle)),Arrays.stream(big)).toArray();
    }return ints;
  }


  public List<Integer> findArrayQuick(List<Integer> n){
    if(n.size() != 0){
      int middle = n.get(0);
      n.remove(0);
      List<Integer> rest = n;
      List<Integer> small = findArrayQuick(rest.stream().filter(x -> x <= middle).collect(Collectors.toList()));
      List<Integer> big = findArrayQuick(rest.stream().filter(x -> x > middle).collect(Collectors.toList()));
      return Stream.concat(Stream.concat(small.stream(),Stream.of(middle)),big.stream()).collect(Collectors.toList());
    }return n;
  }


  protected static Object remove(Object array, int index) {
    int length = getLength(array);
    if (index >= 0 && index < length) {
      Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
      System.arraycopy(array, 0, result, 0, index);
      if (index < length - 1) {
        System.arraycopy(array, index + 1, result, index, length - index - 1);
      }

      return result;
    } else {
      throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
    }
  }

  public void findNumberTwo(int R){
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    String line1 = sc.nextLine();
    List<Integer> list1 = Arrays.stream(line.split(" ")).map(x -> {
      return Integer.parseInt(x);
    }).collect(Collectors.toList());
    List<Integer> list2 = Arrays.stream(line1.split(" ")).map(x -> {
      return Integer.parseInt(x);
    }).collect(Collectors.toList());

    for (Integer integer : list1) {
      for (Integer integer1 : list2) {
        if(integer1 >= integer && Math.abs(integer1 - integer) < R){
          System.out.printf("%d %d",integer,integer1);
          System.out.println();
          break;
        }
      }
    }

  }

  public static void findLogTime(){
    List<String> times = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String nextLine = sc.nextLine();
    while (nextLine != null && !nextLine.equals("")){
      times.add(nextLine);
      nextLine = sc.nextLine();
    }
    Map<Integer, List<Integer>> map = new HashMap<>();
    int i = 1;
    for (String time : times) {
      String[] split = time.split(":");
      List<Integer> ans = new ArrayList<>();
      for (String s : split) {
        Integer anInt = Integer.parseInt(s.replaceFirst("^0*", ""));
        ans.add(anInt);
      }
      map.put(i, ans);
      i++;
    }
    System.out.println(map);
  }


  /**
   * 第四层 为111221 意为第三层1211是一个1一个2 2个1组成 11 12 21
   * @param n
   * @return
   */
  public String findArrays(int n){
    if(n == 0){
      return "1";
    }
    if(n == 1){
      return "11";
    }
    String newStr = findArrays(n - 1);
    StringBuilder sb = new StringBuilder();
    char[] chars = newStr.toCharArray();
    int count = 1;
    int key1 = 0;
    int key2 = 1;
    while(key2 <= chars.length) {
      if(key2 == chars.length){
        sb.append(count).append(chars[key1]);
        break;
      }
      if(chars[key1] == chars[key2]){
        count++;
        key2++;
      } else {
        sb.append(count).append(chars[key1]);
        key1 = key2;
        key2++;
        count = 1;
      }
    }
    return sb.toString();
  }
//  public static void main(String[] args) {
//    List<String> strs = new ArrayList<>();
////    strs.add("1 20 (2)");
////    strs.add("2 10 (3)");
////    strs.add("3 15 ()");
////    strs.add("4 15 ()");
////    strs.add("4 20 ()");
////    strs.add("5 30 ()");
////    strs.add("2 10 (4,5,1)");
////    strs.add("1 40 ()");
//    TestList04 ts = new TestList04();
////    ts.fileDemo(2,strs);
////    List<List<Integer>> lists = new ArrayList<>();
////    List<Integer> list1 = new ArrayList<>();
////    List<Integer> list2 = new ArrayList<>();
////    List<Integer> list3 = new ArrayList<>();
////    list1.add(1);
////    list1.add(10);
////    list1.add(2);
////    list2.add(2);
////    list2.add(20);
////    list2.add(3);
////    list3.add(3);
////    list3.add(15);
////    list3.add(0);
////    lists.add(list1);
////    lists.add(list2);
////    lists.add(list3);
////    ts.fileDemoMatrix(lists, 1);
////    System.out.println(ts.fn(16));
//    //String str = "[1,4  ,5,2 ,2]";
//    //int[] ints = new int[]{1,4,5,2,2};
//    //List<Integer> list = new ArrayList<>();
//    //LinkedList linkedList = new LinkedList();
//    //ts.passHouse(str, 7, list);
//    //ts.stackPaiLie(linkedList,2,0,ints);
////    printWord(10, 1);
////    List<String> list = new ArrayList<>();
////    Collections.addAll(list,"word","dd","da","dc","dword","d");
////    ts.DragonNext(0,6,list);
////    int[][] ints = {{1,2,3,4},{0,0,0,0},{0,0,4,1},{1,7,0,5}};
////    List<Triple> list = new ArrayList<>();
////    ts.sparseMatrix(ints, list);
////    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{"a","b","c"}));
//    //ts.findAncestor(2,0, list);
//    //Integer[] arr = {5, 2, 9, 3, 6, 8, 1, 7};
//    //List<Integer> list = new ArrayList<Integer>(Arrays.asList(new Integer[]{5, 2, 9, 3, 6, 8, 1, 7}));
//    //System.out.println(ts.findArrayQuick(list));
//    //ts.findNumberTwo(5);
//    //findLogTime();
////    System.out.println(ts.findArrays(3));
////    List<String> list = new ArrayList<String>(Arrays.asList(new String[]{"1 1","2 1","3 1","4 -2"}));
//    List<Integer> ints = new ArrayList<>(Arrays.asList(new Integer[]{2,3,4,5,6,7,8,9}));
//    //ts.findFuture(4,10, list);
//    ts.findNumber(8, ints);
//  }

  public void findFuture(int n, int k, List<String> list){
    int y = 0;
    int x = 0;
    int sum = 0;
    List<Integer> xlist = new LinkedList<>();
    List<Integer> ylist = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      String[] s1 = list.get(i).split(" ");
      xlist.add(Integer.valueOf(s1[0]));
      ylist.add(Integer.valueOf(s1[1]));

    }
    System.out.println(xlist);
    System.out.println(ylist);
    for (int i = 0; i < ylist.size(); i++) {
      y = y + ylist.get(i);
      sum = sum + (y) * (xlist.get(i) - x);
      x = xlist.get(i);
    }
    sum = sum + (k - xlist.get(xlist.size() - 1) - 1) * y;
    System.out.println(sum);
  }

  // 求排列数 A(n,m) n>m
  public static int A(int n, int m) {
    int result = 1;
    // 循环m次,如A(6,2)需要循环2次，6*5
    for (int i = m; i > 0; i--) {
      result *= n;
      n--;// 下一次减一
    }
    return result;
  }

  public String findSortNumberAll(int n, int k){
    List<String> list1 = new LinkedList<>();
    for (int i = 1; i <= n; i++) {
      list1.add(String.valueOf(i));
    }
    Stream<String> streams = list1.stream().distinct();
    for (int i = 1; i < n; i++) {
      streams = streams.flatMap(in -> list1.stream().filter(temp -> !in.contains(temp)).map(in::concat));
    }
    return streams.collect(Collectors.toList()).get(k - 1);
  }

  Import im = new Import();
  Import.Convert convert = value -> {
    return value.split(":")[1];
  };


  public void findURLCombine(String str){
    StringBuilder sb = new StringBuilder();
    for (String s : str.split(",")) {
      if (s.contains("/")) {
        sb.append(s);
      } else {
        sb.append("/").append(s);
      }
    }
    System.out.println(sb.toString());
  }

  public void findNumber(int n, List<Integer> list){
    List<List<Integer>> ans = new ArrayList<>();
    while (list.size() != 0){
      List<Integer> newlist = new ArrayList<>();
      List<Integer> rest = new ArrayList<>();
      list.sort((o1, o2) -> o1 - o2);
      int min = list.get(0);
      newlist.add(min);
      list.remove(0);
      for (int i = 0; i < list.size(); i++) {
        if(list.get(i) % min == 0){
          newlist.add(list.get(i));
          list.remove(i);
        }
      }
      ans.add(newlist);
    }
    System.out.println(ans);
  }

  public void findDouble(int n, List<String> list){
    Stack<int[]> stack = new Stack<>();
    List<int[]> result = new ArrayList<>();
    for (String s : list) {
      Integer being = Integer.valueOf(s.split(",")[0]);
      Integer end = Integer.valueOf(s.split(",")[1]);
      result.add(new int[]{being, end});
    }
    result.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    int[] current = result.get(0);
    stack.push(current);
    diguiLine(n,0, current, result, stack);
  }

  private void diguiLine(int count, int index, int[] current, List<int[]> result, Stack<int[]> stack) {
    List<int[]> ans = new ArrayList<>();
    int temp = 0;
    for (int i = index; i < count; i++) {
      if(result.get(i)[0] <= current[1] && result.get(i)[1] > current[1]){
        temp = i;
        ans.add(result.get(i));
      }
    }
    if(ans.size() < 1){
      System.out.println(stack.size());
      stack.forEach((t) -> System.out.println(Arrays.toString(t)));
      return;
    }
    ans.sort((o1, o2) -> o2[1] - o1[1]);
    stack.push(ans.get(0));
    diguiLine(count,temp,ans.get(0),result,stack);
  }


  public void findDouble2(int n, List<String> list){
    Stack<int[]> stack = new Stack<>();
    List<int[]> result = new ArrayList<>();
    for (String s : list) {
      Integer being = Integer.valueOf(s.split(",")[0]);
      Integer end = Integer.valueOf(s.split(",")[1]);
      result.add(new int[]{being, end});
    }
    result.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    stack.push(result.get(0));
    for (int i = 1; i < n; i++) {
      int[] currentNow = result.get(i);
      int[] top = stack.isEmpty() ? null : stack.peek();
      while (true) {
        // 如果栈为空，直接加入当前区间
        if (top == null) {
          stack.push(currentNow);
          break;
        }
        int topStart = top[0];
        int topEnd = top[1];
        int currentStart = currentNow[0];
        int currentEnd = currentNow[1];
        if (currentEnd <= topStart) {
          break;
        } else if (currentStart <= topStart && currentEnd <= topEnd) {
          break;
        } else if (currentStart <= topStart && currentEnd > topEnd) {
          stack.pop();
        } else if (currentStart > topStart && currentEnd > topEnd) {
          stack.push(new int[] {topEnd, currentEnd});
          break;
        } else {
          stack.push(currentNow);
          break;
        }
        top = stack.isEmpty() ? null : stack.peek();
      }
    }
    System.out.println(stack.size());
  }

  public void findLineMeager(int n, List<int[]> list){
    list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    Stack<int[]> ans = new Stack<>();
    Stack<int[]> rest = new Stack<>();
    ans.add(list.get(0));
    for (int i = 1; i < n; i++) {
      ans.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
      int top = ans.peek()[0];
      int end = ans.peek()[1];
      if(list.get(i)[0] >= top && list.get(i)[0] <= end && list.get(i)[1] > end){
        ans.add(new int[]{top, list.get(i)[1]});
      } else if(list.get(i)[0] >= top && list.get(i)[1] <= end){

      } else {
        ans.add(new int[]{list.get(i)[0], list.get(i)[1]});
      }
    }
//    System.out.println(Arrays.toString(ans.get(ans.size() - 1)));
    ans.forEach((t) -> System.out.println(Arrays.toString(t)));
  }


  public static void main(String[] args) {
    TestList04 ts = new TestList04();
    //ts.findDouble(3, new ArrayList<>(Arrays.asList(new String[]{"1,4","2,5","3,6"})));
    //ts.findDouble2(3, new ArrayList<>(Arrays.asList(new String[]{"1,4","2,5","3,6"})));
    ArrayList<String> strings = new ArrayList<>(Arrays.asList(new String[]{"1,4", "2,3", "2,5", "4,6", "5,18", "19,20", "19,22"}));
    List<int[]> result = new ArrayList<>();
    for (String s : strings) {
      Integer being = Integer.valueOf(s.split(",")[0]);
      Integer end = Integer.valueOf(s.split(",")[1]);
      result.add(new int[]{being, end});
    }
    ts.findLineMeager(7,result);
  }

}




