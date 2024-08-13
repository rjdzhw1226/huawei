package com.suanfa.test;

import com.suanfa.test.TestList03.GIS;
import com.struct.Tree.TreeNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TestList02 {
  /**
   * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
   */
  public static double powreal(double x , int n){
    double result = 1.0;
    if(x == 0){
       return 0.0;
    }else if(n > 0){
      for (int i = 0; i < n; i++) {
        result *= x;
      }
      return result;
    }else{
      n = -n;
      for (int i = 0; i < n; i++) {
        result *= x;
      }
      return 1 / result;
    }
  }

  /**
   * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   *  fn = fn-1 + fn-2
   *
   *  public int climbStairs(int n) {
   *    int[] dp = new int[n + 1];
   *    dp[0] = 1;
   *    dp[1] = 1;
   *    for(int i = 2; i <= n; i++) {
   *      dp[i] = dp[i - 1] + dp[i - 2];
   *    }
   *    return dp[n];
   *  }
   */
  public static void climbStair(int n){
    int[] fn = new int[n+1];
    fn[0] = 1;
    fn[1] = 1;
    for (int i = 2; i <= n; i++) {
      fn[i] = fn[i - 1] + fn[i - 2];
    }
    System.out.println(fn[n]);
  }

  /**
   * 方法一：e换底 x^1/2 = e^1/2Inx
   * 方法二：二分查找
   */
  public static int mySqurt01(int x){
    if(x == 0){
      return 0;
    }
    int exp = (int) Math.exp(0.5 * Math.log(x));
    return (long) (exp + 1) * (exp + 1) <= x ? (exp + 1) : exp;
  }

  public static int mySqurt02(int x){
    int up = x;
    int down = 0;
    int result = -1;
    while (down < up){
      int mid = down + (up - down) / 2;
      if((long)(mid * mid) <= x){
        result = mid;
        down = mid + 1;
      }else {
        up = mid - 1;
      }
    }
    return result;
  }

  int s;
  public int mySqrt03(int x) {
    s = x;
    if (x == 0) return 0;
    return ((int) (sqrts(x)));
  }

  public double sqrts(double x) {
    double res = (x + s / x) / 2;
    if (res == x) {
      return x;
    } else {
      return sqrts(res);
    }
  }

  public void solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    List<List<String>> init = init(result);
    TryPlay(0, n, init);
  }

  private void TryPlay(int count ,int n, List<List<String>> init) {
    if(count == n){
      System.out.println(init);
      return;
    }
    for (int i = 0; i < n; i++) {
      if (checkPlay(n,i,init,count)) {
        init.get(i).set(count , "Q");
        TryPlay(count+1,n,init);
        //回溯
        init.get(i).set(count , ".");
      }
    }

  }

  private boolean checkPlay(int n, int i, List<List<String>> init, int count) {
    //检查列
    for (int j = 0; j < count; ++j) {
      String checkQueen = init.get(i).get(j);
      if(checkQueen == "Q"){
        return false;
      }
    }
    //检查正反对角线
    for (int i1 = count - 1, j = i - 1; i1 >= 0 && j >= 0; i1--, j--) {
      String checkQueen = init.get(i1).get(j);
      if(checkQueen == "Q"){
        return false;
      }
    }
    for (int i2 = count - 1, j = i + 1; i2 >= 0 && j <= n - 1; i2--, j++){
      String checkQueen = init.get(i2).get(j);
      if(checkQueen == "Q"){
        return false;
      }
    }
    return true;
  }

  private List<List<String>> init(List<List<String>> list){
    for (List<String> strings : list) {
      for (String string : strings) {
        string = ".";
      }
    }
    return list;
  }

  public static String findLongestChildString(String str){
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    List<String> collect = Arrays.asList(line.split(" ")).stream().distinct().collect(Collectors.toList());
    System.out.println(collect);
    return str;
  }
  public static volatile boolean flag = true;
  public static void threadTestState(){
    Object lock = new Object();
    Thread t1 = new Thread(()->{
      synchronized (lock){
        while(flag){
          try {
            System.out.println("t1获取锁等待");
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("t1唤醒");
      }

    },"t1");

    Thread t2 = new Thread(()->{
      synchronized (lock){
        System.out.println("t2获取锁执行");
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        lock.notify();
        flag = false;
      }
    },"t2");

    t1.start();
    t2.start();
  }

  static boolean run = true;

  private Thread monitorThread;
  volatile static boolean interruptFlag = false;

  private String basePath = "D:\\B站";
  public void start(){
    monitorThread = new Thread(()->{
      Thread thread = Thread.currentThread();
      while(true){
        if(interruptFlag){
          break;
        }
        try{
          Thread.sleep(1000);
          System.out.println(thread.getName()+"--------------------------------------------------------------------");
          File dir = new File(basePath);
          List<File> allFileList = new ArrayList<>();
          if (!dir.exists()) {
            System.out.println("目录不存在");
            return;
          }
          getAllFile(dir, allFileList);
          for (File file : allFileList) {
            System.out.println(file.getName());
          }
          System.out.println("该文件夹下共有" + allFileList.size() + "个文件");
        }catch (InterruptedException e){
          e.printStackTrace();
        }
      }

    },"监控线程");
    monitorThread.start();
  }

  public void stop(){
    interruptFlag = true;
    monitorThread.interrupt();
  }

  public static void getAllFile(File fileInput, List<File> allFileList) {
    File[] fileList = fileInput.listFiles();
    assert fileList != null;
    for (File file : fileList) {
      if (file.isDirectory()) {
        getAllFile(file, allFileList);
      } else {
        allFileList.add(file);
      }
    }
  }
  /**
   * 获取键盘输入两种方法
   *
   */
  public void textIn(){
    List<Integer> list = new LinkedList<>();
    Scanner sc= new Scanner(System.in);
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String s1 = "";
    try {
      s1 = bf.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(s1);
  }

  public void triangleGenerate(int numRows){
    List<List<String>> list = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      if(i == 0){
        List<String> objects = new ArrayList<>();
        objects.add("1");
        list.add(objects);
      } else if (i == 1) {
        List<String> objects = new ArrayList<>();
        objects.add("1");
        objects.add("1");
        list.add(objects);
      }else{
        list.add(generate(list,i,numRows));
      }
    }
    System.out.println(list);
  }

  /**
   * 1     0
   * 11    1
   * 121   2
   * 1331  3
   * 14641 4
   * 15101051 5
   * f0 = 1 f1 = 1 f2 = f0+f1 f3 = f1+f2 fn =
   * @param n
   * @return
   */
  private List<String> generate(List<List<String>> list, int n, int rows) {
    List<String> temp = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      if(i == 0 || i == n){
        temp.add("1");
      }else {
        temp.add(list.get(n-1).get(i-1)+list.get(n-1).get(i));
      }
    }
    return temp;
  }

  /**
   * 不获得上一级数据解决不了
   * @param n
   */
  private void generateRow(int n) {
    List<String> result = new ArrayList<>();
   if(n % 2 == 0){
     System.out.println("偶数");
   }else {
     System.out.println("奇数");
   }
  }

  public int reserveCalc(String[] tokens){
    LinkedList<Integer> stack = new LinkedList<>();
    Integer right;
    Integer left;
    for (String t : tokens) {
      switch (t){
        case "+":
          right = stack.pop();
          left = stack.pop();
          stack.push(left + right);
          break;
        case "-":
          right = stack.pop();
          left = stack.pop();
          stack.push(left - right);
          break;
        case "*":
          right = stack.pop();
          left = stack.pop();
          stack.push(left * right);
          break;
        case "/":
          right = stack.pop();
          left = stack.pop();
          stack.push(left / right);
          break;
        default:
          stack.push(Integer.parseInt(t));
          break;
      }
    }
    return stack.peek();
  }

  List<List<GIS>> list = new ArrayList<>();
  public String squareCount(int N, List<GIS> nodeList){
     if(N < 4){
       return "小于四个点，没有正方形";
     } else if (N == 4) {
       return checkSquare(nodeList).toString();
     }else {
       //打印出不为空的个数就是正方形数量
       conTogether(nodeList, 4);
     }
     return "";
  }
  private void conTogether(List<GIS> size, int i) {
    List<GIS> nodeList = new ArrayList<>();
    connect(0,size,i,nodeList);
  }
  private void connect(int i, List<GIS> size, int i1, List<GIS> nodeList) {
    if(nodeList.size() == i1){
      System.out.println(checkSquare(nodeList).toString());
      return;
    }
    for (int j = i; j < size.size(); j++) {
      nodeList.add(size.get(j));
      connect(j+1,size,i1,nodeList);
      nodeList.remove(nodeList.size() - 1);
    }
  }
  private static List<Integer> checkSquare(List<GIS> nodeList) {
    List<Integer> lengthList = new ArrayList<>();
    for (int i = 0; i < nodeList.size(); i++) {
      GIS gis1 = nodeList.get(i);
      if(i != nodeList.size() - 1){
        for (int j = i + 1; j < nodeList.size(); j++) {
          GIS gis2 = nodeList.get(j);
          lengthList.add(xyPath(gis1,gis2));
        }
      }
    }
    lengthList.sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    List<Integer> collect = lengthList.stream().distinct().collect(Collectors.toList());
    if(collect.size() == 2){
      return collect;
    }else {
      return new ArrayList<>();
    }
  }


  public static Integer xyPath(GIS gis1, GIS gis2){
    int x1 = gis1.getX();
    int x2 = gis2.getX();
    int y1 = gis1.getY();
    int y2 = gis2.getY();
    return (x1 -x2) * (x1 - x2) + (y1 -y2) * (y1 - y2);
  }
  /**
   * 逆波兰表达式
   * @param tokens
   * @return
   */
  public int evalRPN(String[] tokens) {
    Stack<String> stack = new Stack<>();
    Stack<Integer> number = new Stack<>();
    int result = 0;
    for (String token : tokens) {
      stack.push(token);
    }
    for (String token : tokens) {
      String pop = token;
      if("+".equals(pop)){
        Integer right = number.pop();
        Integer left = number.pop();
        result = left + right;
        number.push(result);
      } else if ("*".equals(pop)) {
        Integer right = number.pop();
        Integer left = number.pop();
        result = left * right;
        number.push(result);
      } else if ("/".equals(pop)) {
        Integer right = number.pop();
        if(right == 0){
          return -1;
        }
        Integer left = number.pop();
        result = left / right;
        number.push(result);
      } else if ("-".equals(pop)) {
        Integer right = number.pop();
        Integer left = number.pop();
        result = left - right;
        number.push(result);
      } else {
        number.push(Integer.parseInt(pop));
      }
    }
    return result;
  }

  /**
   * 树的遍历
   * @param node
   */
  public void findTree(TreeNode node){
    if(node == null){
      return;
    }
    System.out.println(node.val);
    findTree(node.left);
    findTree(node.right);

  }


  /**
   * 二叉搜索树的数量
   * @param n
   * @return
   */
  public int numTrees(int n) {
    if(n==0 || n==1){
      return 1;
    }
    int count = 0;
    for (int i = 1; i <= n; i++) {
      int left = numTrees(i - 1);
      int right = numTrees(n - i);
      count += left * right;
    }
    return count;
  }


  /**
   * 交错字符串
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public boolean isInterleave(String s1, String s2, String s3) {
    if(s1.length() + s2.length() != s3.length()){
      return false;
    }
    char[] charArrayS1 = s1.toCharArray();
    char[] charArrayS2 = s2.toCharArray();
    char[] charArrayS3 = s3.toCharArray();
    int i = 0;
    int j = 0;
    int k = 0;
    return check(i,j,k,charArrayS1,charArrayS2,charArrayS3);
  }

  private boolean check(int s1, int s2, int s3, char[] s1Length, char[] s2Length, char[] s3Length) {
    if(s3 == s3Length.length){
      return true;
    }
    boolean flag = false;
    if(s1 < s1Length.length && s1Length[s1] == s3Length[s3]){
      flag = check(s1+1,s2,s3+1, s1Length, s2Length, s3Length);
    }
    if(s2 < s2Length.length && s2Length[s2] == s3Length[s3]){
      flag = flag || check(s1,s2+1,s3+1, s1Length, s2Length, s3Length);
    }
    return flag;
  }

  public boolean exist(char[][] board, String word) {
    boolean[][] visited = new boolean[board.length][board[0].length];
    //右 下 左 上
    int[][] ints = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        boolean flag = checkChar(board,visited,ints,i,j,word,0);
        if(flag){
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkChar(char[][] board,boolean[][] visited,int[][] ints, int i, int j, String word, int i1) {
    if(i1 == word.length() -1){
      return true;
    }
    boolean result = false;
    if(word.charAt(i1) == board[i][j]){
      visited[i][j] = true;
      for (int[] anInt : ints) {
        if((i + anInt[0]) >= 0 && (i + anInt[0]) < board.length && (j + anInt[1]) >= 0 && (j + anInt[1]) < board[i].length){
          if (!visited[i + anInt[0]][j + anInt[1]]) {
            //右 下 左 上
            System.out.println(board[i + anInt[0]][j + anInt[1]]);
            System.out.println(i +","+j);
            boolean flag = checkChar(board,visited,ints,(i + anInt[0]),(j + anInt[1]),word,i1+1);
            if(flag) {
              result = flag;
              break;
            }
          }
        }
      }
    }
    visited[i][j] = false;
    return result;
  }
  List<Integer> temp = new ArrayList<Integer>();
  List<List<Integer>> ans = new ArrayList<List<Integer>>();

  public static void main(String[] args) {

    TestList02 ts = new TestList02();
    List<GIS> n = new ArrayList<>();
    n.add(new GIS(0,1));
    n.add(new GIS(1,0));
    n.add(new GIS(0,0));
    n.add(new GIS(1,1));
    n.add(new GIS(2,1));
    n.add(new GIS(1,2));
    n.add(new GIS(2,2));
    n.add(new GIS(1,1));
//    TreeNode left = new TreeNode(3,null,null);
//    TreeNode right = new TreeNode(2,null,null);
//    TreeNode node = new TreeNode(1,left,right);
    //ts.findTree(node);
    //ts.squareCount(8,n);
    //ts.combine(8,4);
    //filter("a:3,b:5,c:2@a:1,b:2");
    ts.combine(4,3);
//    char[][] ca = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//    System.out.println(ts.exist(ca, "SEE"));
//    ts.start();
//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//    ts.stop();
  }
  private void dft(int i, int n, int k) {
    if (temp.size() + (n - i + 1) < k) {
      return;
    }
    if(temp.size() == k){
      ans.add(new ArrayList<>(temp));
      return;
    }
    temp.add(i);
    dft(i+1,n,k);
    temp.remove(temp.size()-1);
    dft(i+1,n,k);
  }

  /**
   * 组合 回溯 剪枝
   * @param n
   * @param k
   */
  public void combine(int n, int k) {
    dft(1,n,k);
    System.out.println(ans);
    //List<Integer> count = new ArrayList<Integer>();
    //dfs(1,n,k,count);
    //System.out.println(ans);
  }

  private void dfs(int i, int n, int k, List<Integer> count) {
    if(count.size() == k){
      ans.add(new ArrayList<>(count));
      return;
    }
    for (int j = i; j <= n; j++) {
      count.add(j);
      dfs(j+1,n,k,count);
      count.remove(count.size()-1);
    }
  }

  /**
   * 一维数组前缀和
   * @param ints
   * @param y
   */
  public void qianzhuisum(int[] ints, int[] y){
    for (int i = 0; i < ints.length; i++) {
      if(i == 0){
        y[i] = ints[i];
      }
      y[i] = y[i -1] + ints[i];
    }
  }

  public static void filter(String str){
    Map<String,Integer> map = new HashMap<>();
    String[] strings = str.split("@");
    for (int i = 0; i < str.split("@").length; i++) {
      String[] result = strings[i].split(",");
      for (int j = 0; j < result.length; j++) {
        if(map.get(result[j].split(":")[0]) != null){
          Integer valueNew = Integer.valueOf(result[j].split(":")[1]);
          Integer valueOld = Integer.valueOf(map.get(result[j].split(":")[0]));
          map.put(result[j].split(":")[0],valueOld - valueNew);
        } else {
          map.put(result[j].split(":")[0],Integer.valueOf(result[j].split(":")[1]));
        }
      }
    }
    System.out.println(map);
  }

  /*除数是2^n次方
  * 被除数的后n位就是余数
  * 求被除数的后n位 就是与2^n-1 按位与*/

  public static void matrix(int[][] ints){
    Set<Integer> sets = new HashSet<>();

  }
  /**
   * 二维数组前缀和
   * @param ints
   * @param y
   */
  public void qianzhuisumTwo(int[][] ints, int[][] y){
    for(int i=0;i < ints.length;i++) {//行
      for (int x = 0; x < ints[i].length; x++)//列
      {
        if (x == 0 && i == 0) y[i][x] = ints[i][x];//左上角的值
        else if (x == 0) y[i][x] = y[i - 1][x] + ints[i][x];//第一列
        else if (i == 0) y[i][x] = y[i][x - 1] + ints[i][x];//第一行
        else y[i][x] = y[i - 1][x] + y[i][x - 1] - y[i - 1][x - 1] + ints[i][x];
      }
    }
  }

  public void middleToLast(String middle){
    LinkedList<Character> stack = new LinkedList<>();
    StringBuilder sb = new StringBuilder(middle.length());
    for (char c : middle.toCharArray()) {
      switch (c){
        case '*':
          check(stack, sb, c);
        case '/':
          check(stack, sb, c);
        case '+':
          check(stack, sb, c);
        case '-':
          check(stack, sb, c);
        case '(':
          stack.push(c);
        case ')':
          while (!stack.isEmpty() && stack.peek() != '(') {
            sb.append(stack.pop());
          }
          stack.pop();
        default:
          sb.append(c);
      }
    }
    while (!stack.isEmpty()){
      sb.append(stack.pop());
    }
    System.out.println(sb.toString());
  }

  private static void check(LinkedList<Character> stack, StringBuilder sb, char c) {
    if(stack.isEmpty()){
      stack.push(c);
    }else {
      if (priority(c) > priority(stack.peek())) {
        stack.push(c);
      }else {
        while (priority(stack.peek()) >= priority(c)){
          sb.append(stack.pop());
        }
        stack.push(c);
      }
    }
  }

  private static int priority(char c){
    switch(c){
      case '*':
        return 2;
      case '/':
        return 2;
      case '+':
        return 1;
      case '-':
        return 1;
      case '(':
        return 0;
      default:
        throw new IllegalArgumentException("不合法"+ c);
    }
  }
}

