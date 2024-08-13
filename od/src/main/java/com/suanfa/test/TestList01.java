package com.suanfa.test;

import com.struct.ListNode.ListNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestList01 {


  /**
   * 交换链表两两相邻元素
   *
   * @param head
   * @return
   */
  public ListNode swapPairs(ListNode head) {
    ListNode pre = new ListNode(0);
    pre.next = head;
    ListNode temp = pre;
    while (temp.next != null && temp.next.next != null) {
      ListNode start = temp.next;
      ListNode end = temp.next.next;
      temp.next = end;
      start.next = end.next;
      end.next = start;
      temp = start;
    }
    return pre.next;
  }

  /**
   * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，
   * 要求 不使用 乘法、除法和取余运算。
   *
   * @param dividend
   * @param divisor
   */
  public static void divide(int dividend, int divisor) {
    int de = math(dividend);
    int ds = math(divisor);
    int res = 0;
    while (de >= ds) {
      de = de - ds;
      res++;
    }
  }

  private static int sub(int dividend, int divisor) {
    if ((dividend <= 0 && divisor < 0)) {

    }
    return 0;
  }

  public static void remove() {

  }

  /**
   * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度
   *
   * @param old
   * @param noOld
   * @return
   */
  public static List<String> findStr(String old, String noOld) {
    List<String> list = new ArrayList<>();
    String[] split2Old = old.split(",");
    String[] split2noOld = noOld.split(",");
    int i = 0;
    while (i < split2noOld.length) {
      String s = split2noOld[i];
      for (int j = 0; j < split2Old.length; j++) {
        if (s.equals(split2Old[j])) {
          list.add(split2Old[j]);
        } else {
          list.add("''");
        }
      }
      i++;
    }
    return list;
  }

  public static String[] findStr2(String old, String noOld) {
    List<Integer> list = new ArrayList<>();
    List<Integer> list1 = new ArrayList<>();

    String[] split2Old = old.split(",");
    String[] split2noOld = noOld.split(",");
    for (int i = 0; i < split2Old.length; i++) {
      list1.add(i);
    }
    int i = 0;
    while (i < split2noOld.length) {
      String s = split2noOld[i];
      for (int j = 0; j < split2Old.length; j++) {
        if (s.trim().toLowerCase().equals(split2Old[j].trim().toLowerCase())) {
          list.add(j);
        }
      }
      i++;
    }
    int k = 0;
    while (k < list.size()) {
      Integer s = list.get(k);
      for (int j = 0; j < list1.size(); j++) {
        if (s == list1.get(j)) {
          list1.remove(j);
        }
      }
      k++;
    }
    for (int j = 0; j < list1.size(); j++) {
      split2Old[list1.get(j)] = "''";
    }
    return split2Old;
  }

  /**
   * 比较两个字符串
   *
   * @param old
   * @param noOld
   * @return
   */
  public static List<String> compareStr(String old, String noOld) {
    List<String> list = new ArrayList<>();
    String[] split2Old = old.split(",");
    String[] split2noOld = noOld.split(",");
    for (int i = 0; i < split2Old.length; i++) {
      if (!split2noOld[i].equals(humpToUnderline(split2Old[i]))) {
        list.add(split2Old[i]);
      }
    }
    return list;
  }

  /**
   * 驼峰转下划线
   *
   * @param str
   * @return
   */
  public static String humpToUnderline(String str) {
    String regex = "([A-Z])";
    Matcher matcher = Pattern.compile(regex).matcher(str);
    while (matcher.find()) {
      String target = matcher.group();
      str = str.replaceAll(target, "_" + target.toLowerCase());
    }
    return str;
  }

  /**
   * 下划线转驼峰
   *
   * @param str
   * @return
   */
  public static String underlineToHump(String str) {
    String regex = "_(.)";
    Matcher matcher = Pattern.compile(regex).matcher(str);
    while (matcher.find()) {
      String target = matcher.group(1);
      str = str.replaceAll("_" + target, target.toUpperCase());
    }
    return str;
  }

  private static int math(int i) {
    return Math.abs(i);
  }

  /**
   * 递归查重
   *
   * @param i
   * @param index
   */
  private static void findReplace(int[] i, int index) {
    if (index == i.length - 1) {
      return;
    }
    for (int j = index; j < i.length - index; j++) {
      if (j != i.length - 1) {
        if (i[index] == i[j + 1]) {
          i[j + 1] = 0;
        }
      }
    }
    findReplace(i, ++index);
  }

  /**
   * 给你两个字符串 haystack 和 needle ，
   * 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
   * 如果 needle 不是 haystack 的一部分，则返回 -1
   *
   * @param haystack
   * @param needle
   * @return
   */
  public static int removeStr(String haystack, String needle) {
    List<Integer> list = new ArrayList<>();
    findStr(haystack, needle, 0, list);
    System.out.println(list);
    return 0;
  }

  private static void findStr(String haystack, String needle, int index, List<Integer> list) {
    int len = needle.length();
    int leh = haystack.length();

    if (index == leh - len + 1) {
      return;
    }

    for (int i = index; i < leh - len + 1; i++) {
      if (haystack.substring(i, i + len).equals(needle)) {
        list.add(index);
      }
    }
    index = index + 1;
    findStr(haystack, needle, index, list);
  }

  public static String findIPV4(String ipv4) {
    int result = 0;
    if (ipv4.indexOf("\\.") != -1) {
      String[] split = ipv4.split("\\.");
    }
    String[] split = ipv4.split("#");
    if (split.length != 4) {
      return "invalid IP";
    }
    for (int i = 0; i < split.length; i++) {
      // 将 ip 的每一段解析为 int，并根据位置左移 8 位
      int intSlice = Integer.parseInt(split[i]) << 8 * i;

      // 求或
      result = result | intSlice;
    }
    return String.valueOf(result);
  }

  public static String solution(String s) {

    s = s.replaceAll("[a-z]", "");
    char[] scArr = s.toCharArray();

    ArrayList<Integer> leftBracketIndexes = new ArrayList<>();
    ArrayList<Integer> rightBracketIndexes = new ArrayList<>();
    ArrayList<Integer> commaIndexes = new ArrayList<>();

    ArrayList<Integer> xs = new ArrayList<>();
    ArrayList<Integer> ys = new ArrayList<>();

    for (int i = 0; i < scArr.length; i++) {
      if (Character.toString(scArr[i]).equals("(")) {
        leftBracketIndexes.add(i);
      }
      if (Character.toString(scArr[i]).equals(")")) {
        rightBracketIndexes.add(i);
      }
      if (Character.toString(scArr[i]).equals(",")) {
        commaIndexes.add(i);
      }
    }

    for (int i = 0; i < leftBracketIndexes.size(); i++) {
      StringBuilder str1 = new StringBuilder();
      StringBuilder str2 = new StringBuilder();
      for (int a = leftBracketIndexes.get(i) + 1; a < commaIndexes.get(i); a++) {
        str1.append(scArr[a]);
      }
      xs.add(Integer.valueOf(String.valueOf(str1)));

      for (int b = commaIndexes.get(i) + 1; b < rightBracketIndexes.get(i); b++) {
        str2.append(scArr[b]);
      }
      ys.add(Integer.valueOf(String.valueOf(str2)));
    }

    int resIndex = 0;
    int distance = 0;

    for (int i = 0; i < xs.size(); i++) {

      if (xs.get(i) <= 0 || xs.get(i) >= 1000 || ys.get(i) <= 0 || ys.get(i) >= 1000) {
        return "(0,0)";
      }

      if (xs.get(i) + ys.get(i) > distance) {
        distance = xs.get(i) + ys.get(i);
        resIndex = i;
      }
    }
    return "(" + xs.get(resIndex) + "," + ys.get(resIndex) + ")";
  }

  public static void min() {

    String str = "fergaA13fdsf3(100,200)fZ2r3rfasf(300,400)";
    String s = str.replaceAll("[A-z]", "");
    String[] split = s.split("");
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();

    for (int i = 0; i < split.length; i++) {
      if (String.valueOf(split[i]).equals("(")) {
        list1.add(i);
      }
      if (String.valueOf(split[i]).equals(")")) {
        list2.add(i);
      }
      if (String.valueOf(split[i]).equals(",")) {
        list3.add(i);
      }
    }


  }

  public static void TreeReplant() {
    Scanner scanner = new Scanner(System.in);
    //胡杨总数
    int n = scanner.nextInt();
    //死亡胡杨数目
    int m = scanner.nextInt();
    //死亡胡杨编号数组
    List<Integer> array = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      array.add(scanner.nextInt());
    }
    //补种数量
    int k = scanner.nextInt();

    //记录最大值
    int max = 0;
    //从左向右移动滑动窗口，这里的i标记的是窗口的左边界
    for (int i = 0; i <= m - k; i++) {
      if (i == 0) {
        //滑动窗口在数组最左边
        max = Math.max(max, array.get(i + k) - 1);
      } else if (i == m - k) {
        //滑动窗口在数组最右边
        max = Math.max(max, n - array.get(i - 1));
      } else {
        //滑动窗口在数组中间某个位置
        max = Math.max(max, array.get(i + k) - array.get(i - 1) - 1);
      }
    }

    System.out.println(max);

  }

  public static void match(Integer[] energy) {
    Map<Integer, Integer> map = new LinkedHashMap<>();
    for (int i = 0; i < energy.length; i++) {
      map.put(i, energy[i]);
    }
    if (map.size() % 2 == 0) {
      if (map.size() <= 4) {
        //冠军 亚军 季军 殿军
        System.out.println(map);
      } else {
        List<Integer> list = new LinkedList<>();
        List<Integer> listRemove = new LinkedList<>();
        for (int i = 0; i < map.size(); i = i + 2) {
          if (map.get(i) > map.get(i + 1)) {
            //map.remove(i+1);
            listRemove.add(i + 1);
            list.add(map.get(i));
          } else {
            //map.remove(i);
            listRemove.add(i);
            list.add(map.get(i + 1));
          }
        }
        for (Integer integer : listRemove) {
          map.remove(integer);
        }
        Integer[] integers = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
          integers[i] = list.get(i);
        }
        match(integers);
      }
    } else {
      //match(new Integer[]{2 ,3 ,5 ,4 ,1});
      if (map.size() <= 3) {
        //冠军 亚军 季军
        System.out.println(map);
      } else {
        //进去之前轮空的add
        List<Integer> list = new LinkedList<>();
        List<Integer> listRemove = new LinkedList<>();
        Integer a = map.get(map.size() - 1);
        map.remove(map.size() - 1);
        for (int i = 0; i < map.size(); i = i + 2) {
          if (map.get(i) > map.get(i + 1)) {
            //map.remove(i+1);
            listRemove.add(i + 1);//3
            list.add(map.get(i));//5
          } else {
            //map.remove(i);
            listRemove.add(i);//0
            list.add(map.get(i + 1));//3
          }
        }
        for (Integer integer : listRemove) {
          map.remove(integer);
        }
        list.add(a);
        System.out.println(list);
        Integer[] integers = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
          integers[i] = list.get(i);
        }
        match(integers);
      }
    }
  }

  public static void floatWindow(int N, int M, List<Integer> Ma, int K) {
    //N颗种的 3颗死的 1颗补种
    // 2 4 7
    int max = 0;
    for (int i = 0; i < M - K + 1; i++) {
      if (i == 0) {
        max = Math.max(max, Ma.get(i + K) - 1);
      } else if (i == M - K) {
        max = Math.max(max, N - Ma.get(i - 1));
      } else {
        max = Math.max(max, Ma.get(i + K) - Ma.get(i - 1) - 1);
      }
    }
  }

  public static void urlCount() {
    Map<String, Integer> map = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.nextLine();
      if (str.contains(".")) {
        map.put(str, map.getOrDefault(str, 0) + 1);
      } else {
        int n = Integer.parseInt(str);
        if (n > map.size()) {   //如果数字大于网站个数这退出
          break;
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet()); //将map转换成list
        String res = "";

        for (int i = 0; i < n; i++) {
          res += list.get(i).getKey();
          if (i != n - 1) {
            res += ",";
          }
        }
        System.out.println(res);
      }
    }
  }

  public static void matrix() {
    int max = 0;
    List<Integer> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int i = sc.nextInt();
    sc.nextLine();
    while (i > 0) {
      String[] split = sc.nextLine().split(",");
      //算最大值
      list.add(calc(split, i));
      i--;
    }
    System.out.println(list);
  }

  private static int calc(String[] split, int i) {
    int count = 0;
    StringBuilder sb = new StringBuilder();
    for (String s : split) {
      if (s.equals("1")) {
        count++;
      }
    }
    for (int j = 0; j < count; j++) {
      sb.append("1");
    }
    for (int k = 0; k < i - count; k++) {
      sb.append("0");
    }
    int index = Integer.parseInt(sb.toString(), 2);
    System.out.println(sb.toString());
    System.out.println(index);
    return index;
  }

  public static int getTime(int m, int n, int x1, int y1, int x2, int y2) {
    int[] nums = new int[m * n];
    nums[x1 * m + y1] = 1;
    nums[x2 * m + y2] = 1;
    //数组中1的数量
    int oneCount = 2;
    int result = 0;
    ArrayList<int[]> tochage = new ArrayList<>();
    tochage.add(new int[]{x1, y1});
    tochage.add(new int[]{x2, y2});
    //上下左右传播
    int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    while (oneCount < m * n) {
      ArrayList<int[]> cahce = new ArrayList<>();
      for (int[] split : tochage) {
        int x = split[0];
        int y = split[1];
        for (int[] moveIndex : move) {
          int newX = x + moveIndex[0];
          int newY = y + moveIndex[1];
          if (newX >= 0 && newX < m && newY >= 0 && newY < n && nums[newX * n + newY] == 0) {
            nums[newX * n + newY] = 1;
            cahce.add(new int[]{newX, newY});
            oneCount++;
          }
        }
      }
      tochage = cahce;
      result++;
    }
    return result;
  }

  public static int strduichen(int x, int index) {
    String s = strInit(x);
    System.out.println(s);
    return 0;
  }

  private static String strInit(int x) {
    if (x == 1) {
      return "R";
    }
    StringBuilder str = new StringBuilder();
    // i 个字符串 = 第 i - 1 号字符串的取反 + 第 i - 1 号字符串
    str.append(strFan(strInit(x - 1)));
    str.append(strInit(x - 1));
    return str.toString();
  }

  public static char[] strFan(String str) {
    char[] chars = str.toCharArray();
    char[] charsNew = new char[chars.length];
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == 'R') {
        charsNew[i] = 'B';
      } else {
        charsNew[i] = 'R';
      }
    }
    return charsNew;
  }
  //java 求一个list集合中出现次数最多的一项，和此项的出现次数
  //效率很高的统计方法。利用正则表达式统计

  public void st(List<String> list) {
    String regex;
    Pattern p;
    Matcher m;
    String tmp = "";
    String tot_str = list.toString();
    int max_cnt = 0;
    String max_str = "";
    for (String str : list) {
      if (tmp.equals(str)) continue;
      tmp = str;
      regex = str;
      p = Pattern.compile(regex);
      m = p.matcher(tot_str);
      int cnt = 0;
      while (m.find()) {
        cnt++;
      }
      //System.out.println(str + ":" + cnt);
      if (cnt > max_cnt) {
        max_cnt = cnt;
        max_str = str;
      }
    }
    System.out.println("字符串 " + max_str + " 出现的最大次数：" + max_cnt);  //字符串 aa 出现的最大次数：4
  }

  public static void shortTest() {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      List<Integer> collect = Arrays.stream(sc.nextLine().trim().split(" ")).map(t -> Integer.parseInt(t)).collect(Collectors.toList());
      findFrence(collect);
    }
  }

  private static void findFrence(List<Integer> collect) {
    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;
    int maxIndex = 0;
    for (Integer integer : collect) {
      int frequency = Collections.frequency(collect, integer);
      map.put(integer, frequency);
      if (map.get(integer) > max) {
        max = map.get(integer);
        maxIndex = integer;
      }
    }

    int firstIndex = collect.indexOf(maxIndex);
    int lastIndex = collect.lastIndexOf(maxIndex);
    System.out.println(max);
    System.out.println(maxIndex);
    System.out.println(lastIndex - firstIndex + 1);
  }

  public static void spread(int[][] matrix, int i, int k, int value) {
    // 检查边界条件，如果超出了矩阵的范围，则停止扩散
    if (i < 0 || i >= matrix.length || k < 0 || k >= matrix[0].length) {
      return;
    }

    // 检查当前点是否已经被扩散过，避免无限循环
    if (matrix[i][k] == value) {
      return;
    }

    // 更新当前点的值
    matrix[i][k] = value;

    // 分别向上、下、左、右四个方向进行递归扩散
    spread(matrix, i - 1, k, value);
    spread(matrix, i + 1, k, value);
    spread(matrix, i, k - 1, value);
    spread(matrix, i, k + 1, value);
  }

  public static void reserveNumber() {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    String[] s = str.split(" ");
    for (String s1 : s) {
      if (s1.contains(".")) {
        String re = s1.replace(".", "");
        StringBuilder sb = new StringBuilder(re);
        String s2 = sb.reverse().append(".").append(" ").toString();
        System.out.print(s2);
      } else {
        StringBuilder sb = new StringBuilder(s1);
        String s2 = sb.reverse().append(" ").toString();
        System.out.print(s2);
      }
    }
  }

  public static void guess() {
    Scanner sc = new Scanner(System.in);
    String guessStr = sc.nextLine();
    String database = sc.nextLine();
    String[] split1 = database.split(",");
    String[] split2 = guessStr.split(",");
    StringBuffer sb = new StringBuffer();
    for (String s : split1) {
      for (String s1 : split2) {
        boolean b = doubleRemove(s, s1);
        System.out.println(b);
      }

    }
  }

  /**
   * 去重
   *
   * @param s
   */
  private static boolean doubleRemove(String s, String s1) {
    StringBuffer sb = new StringBuffer();
    if (s.equals(s1)) {
      return true;
    }
    for (int i = 0; i < s.length(); i++) {
      if (i != s.length() - 1) {
        if (s.charAt(i) != s.charAt(i + 1)) {
          sb.append(s.charAt(i));
        }
      } else {
        sb.append(s.charAt(i));
      }
    }
    String s2 = sb.toString();
    //System.out.println(s2);
    if (s2.equals(s1)) {
      return true;
    }
    char[] chars = s1.toCharArray();
    char[] chars1 = s2.toCharArray();
    Arrays.sort(chars);
    Arrays.sort(chars1);
    if (Arrays.equals(chars, chars1)) {
      return true;
    }
    return false;
  }

  public static void strDecode() {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    String[] split = s.split("[abcdef0123456789]");
    for (String s1 : split) {
      removechongfu(s1);
    }
    System.out.println(Arrays.toString(split));

  }

  private static int removechongfu(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      int i1 = s.indexOf(s.charAt(i));
      if (i1 == i) {
        count++;
      }
    }
    System.out.print(count + " ");
    return count;
  }


  static class node {
    private Integer u;
    private Integer v;

    public node(Integer u, Integer v) {
      this.u = u;
      this.v = v;
    }

    public Integer getU() {
      return u;
    }

    public void setU(Integer u) {
      this.u = u;
    }

    public Integer getV() {
      return v;
    }

    public void setV(Integer v) {
      this.v = v;
    }
  }

  private static boolean panduan(List<Integer> left, List<Integer> right) {
    int sumleft = 0;
    int sumright = 0;
    for (Integer integer : left) {
      sumleft = sumleft + integer;
    }
    for (Integer integer : right) {
      sumright = sumright + integer;
    }
    if (sumleft == sumright) {
      return true;
    } else {
      return false;
    }
  }


  public static void mapfind(int n, int l, List<node> nodes, int i) {
    //节点个数n 时延列表l
    List<Integer> list = new ArrayList<>();
    List<Integer> mapKeyList = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    Map<Integer, List<Integer>> map = new LinkedHashMap<>();
    for (int j = 0; j < l; j++) {
      node node = nodes.get(j);
      temp.add(node.getU());
    }
    List<Integer> collect = temp.stream().distinct().collect(Collectors.toList());
    map.keySet().addAll(collect);
    for (Integer integer : map.keySet()) {
      mapKeyList.add(integer);
    }
    for (int j = 0; j < l; j++) {
      if (nodes.get(j).getU() == mapKeyList.get(j)) {
        list.add(nodes.get(j).getV());
        map.put(nodes.get(j).getU(), list);
      }
    }


  }

  public static int findString(String target, String source) {
    int max = 0;
    boolean flag = true;
    Map<Character, Integer> map = new LinkedHashMap<>();
    List<Integer> list = new ArrayList<>();
    char[] sources = source.toCharArray();
    char[] targets = target.toCharArray();
    for (int i = 0; i < targets.length; i++) {
      for (int j = 0; j < sources.length; j++) {
        if (targets[i] == sources[j]) {
          map.put(targets[i], j);
        }
      }
    }
    for (int i = 0; i < map.size(); i++) {
      if (map.size() != 1) {
        if (i == map.size() - 1) {
          if (map.get(targets[i - 1]) > map.get(targets[i])) {
            flag = false;
          }
        } else {
          Integer index = map.get(targets[i]);
          Integer nextIndex = map.get(targets[i + 1]);
          if (nextIndex < index) {
            flag = false;
          }
        }
      }
    }
    if (map.size() != 0) {
      if (flag) {
        max = map.get(targets[0]);
      }
    }
    return max;
  }


  public static int findSum(List<Integer> list) {
    int returnSum = 0;
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      if (i == 0) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Integer index = list.get(i);
        left.add(index);
        for (int j = 2; j < list.size(); j++) {
          right.add(list.get(j));
        }
        boolean panduan = panduan(left, right);
        if (panduan) {
          result.add(i + 1);
        }
      } else if (i == list.size() - 1) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        Integer index = list.get(i);
        right.add(index);
        for (int j = 0; j < list.size() - 2; j++) {
          left.add(list.get(j));
        }
        boolean panduan = panduan(left, right);
        if (panduan) {
          result.add(i - 1);
        }
      } else {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
          left.add(list.get(j));
        }
        for (int j = i + 2; j <= list.size() - 1; j++) {
          right.add(list.get(j));
        }
        boolean panduan = panduan(left, right);
        if (panduan) {
          result.add(i + 1);
        }
      }
    }
    if (result.size() > 0) {
      returnSum = result.get(0);
    }
    for (Integer integer : result) {
      if (returnSum > integer) {
        returnSum = integer;
      }
    }
    return returnSum;
  }

  public static List<Character> ArrayToList(char[] array) {
    Scanner sc = new Scanner(System.in);
    String target = sc.nextLine();
    String source = sc.nextLine();
    List<Character> list = new LinkedList<>();
    for (char c : array) {
      list.add(c);
    }
    return list;
  }

  public void dfs() {

  }

  //重复数的全排列
  public static List<List<Integer>> permuteUnique(int[] nums) {
    return null;
  }

  /**
   * 全排列 List实现
   * @param nums
   * @param list
   */
  public static void AllPailie(int[] nums, List<Integer> list){
    //终止条件 到子叶节点就输出
    if(nums.length == 0){
      System.out.println(list);
    }
    for (int i = 0; i < nums.length; i++) {
      //临时数组来存储
      int[] num = new int[nums.length - 1];
      //数组拷贝
      //1,2,3从1开始 就是将2,3拷贝
      //1,2,3到2为根向下遍历时 就是将1,3拷贝
      System.arraycopy(nums,0,num,0,i);//拷贝前面
      System.arraycopy(nums,i + 1,num,i,nums.length-i-1);//拷贝后面
      //添加进结果集
      list.add(nums[i]);
      //递归
      AllPailie(num,list);
      //遍历完一个分支回退到开始 清除中间加进去的值
      list.remove(list.size()-1);
    }
  }

  /**
   * 全排列 交换实现
   * @param nums
   * @param first
   * @param last
   */
  public static void AllPailie01(int[] nums,int first, int last){
    if(first == last){
      System.out.println(Arrays.toString(nums));
    }
    for (int i = first; i <= last; i++) {
      swaps(nums,first,i);
      AllPailie01(nums,first+1,last);
      swaps(nums,first,i);
    }

  }


  private static void swaps(int[] nums, int first, int i) {
    int temp = 0;
    temp = nums[first];
    nums[first] = nums[i];
    nums[i] = temp;
  }
  public static void main(String[] args) {
//    int[] in = {1, 2, 2, 3, 2, 4, 5, 6, 7, 8, 9};
//    String str1 = "a,e,b,c,g,d";
//    String str2 = "a,e,b,c,d";
//    String[] str21 = findStr2(str1, str2);
//    for (String s : str21) {
//      System.out.print(s+",");
//    }
    //findIPV4("168#255#12#7");
    //match();
    //divide(-7,3);
    //removeStr("sadlinesad","sad");
    //findReplace(in, 0);
    //Arrays.stream(in).forEach(System.out::println);
//    System.out.println("输入：");
//    Scanner sc = new Scanner(System.in);
//    int m = sc.nextInt();
//    sc.nextLine();
//    String[] strArr = new String[m];
//    // 从第二行开始读取
//    for(int i = 0; i < m; i++) {
//      strArr[i] = sc.nextLine();
//    }
//    System.out.println(Arrays.toString(strArr));
//    match(new Integer[]{2 ,3 ,5 ,4});
//    strduichen(5,1);
//    strDecode();
    //AllPailie(new int[]{1,2,3},new ArrayList<>());
    AllPailie01(new int[]{1,2,3},0,2);
//    Scanner sc = new Scanner(System.in);
//    int n = 0;
//    int l = 0;
//    String s2 = sc.nextLine();
//    String[] s3 = s2.split(" ");
//    n = Integer.parseInt(s3[0]);
//    l = Integer.parseInt(s3[1]);
//    List<node> list = new ArrayList<>();
//    while (l > 0){
//      String s = sc.nextLine();
//      String[] s1 = s.split(" ");
//      node node = new node(Integer.parseInt(s1[0]),Integer.parseInt(s1[1]));
//      list.add(node);
//      l--;
//    }
//    String s4 = sc.nextLine();
//    int i = Integer.parseInt(s4);
  }

  public static void perm(int[] array,int start,int end) {
    if(start==end) {
      System.out.println(Arrays.toString(array));
    } else {
      for (int i = start; i <= end; i++) {
        //1，2，3的全排列这块相当于将其中一个提了出来，下次递归从start+1开始
        swap(array,start,i);
        perm(array,start+1,end);
        //这块是复原数组，为了保证下次另外的同级递归使用数组不会出错
        //这块可以通过树来理解，每次回退一步操作，交换回去
        swap(array,start,i);
      }
    }
  }
  public static void swap(int[] array,int i,int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  //不重复数的全排列
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> output = new ArrayList<Integer>();
    for (int num : nums) {
      output.add(num);
    }
    int n = nums.length;
    backtrack(n, output, res, 0);
    return res;
  }

  public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
    // 所有数都填完了
    if (first == n) {
      res.add(new ArrayList<Integer>(output));
    }
    for (int i = first; i < n; i++)
    { // 动态维护数组
       Collections.swap(output, first, i);
      // 继续递归填下一个数
       backtrack(n, output, res, first + 1);
      // 撤销操作
       Collections.swap(output, first, i);
      }
  }
}
