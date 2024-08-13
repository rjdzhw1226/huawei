package com.suanfa.test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.getNumericValue;

public class TestList05 {
  public int findCandy(long sum, int count) {
    if (sum <= 1) {
      return count;
    }
    if (sum % 2 == 0) {
      return findCandy(sum / 2, count + 1);
    }
    return Math.min(findCandy(sum + 1, count + 1), findCandy(sum - 1, count + 1));
  }


  //指定中心为 i 左边长 i 右边长 n - i - 1
  public int findCenter(int[] num) {
    for (int i = 0; i < num.length; i++) {
      if (valid(num, i)) {
        return i;
      }
    }
    return -1;
  }
  private boolean valid(int[] num, int i) {
    int sumLeft = i == 0 ? 1 : Arrays.stream(num).limit(i).reduce((x, y) -> x * y).orElse(1);
    int sumRight = i == (num.length - 1) ? 1 : Arrays.stream(num).skip(i + 1).reduce((x, y) -> x * y).orElse(0);
    if (sumLeft == sumRight) {
      return true;
    } else {
      return false;
    }
  }

  // 1 2 3 1 3 2
  /*不开心的小朋友 数组剩下的就是不开心的小朋友*/
  public void findCar(double n, int[] num, int temp){
    if(n <= 0){
      int[] res = num == null ? new int[]{0} : num;
      System.out.println(Arrays.toString(res));
      return;
    }
    for (int j = 1; j < num.length; j++) {
      if(temp == num[j]){
        n -= 0.5;
        int[] ans = Move(num, j);
        if (ans == null) {
          findCar(n, null, 0);
        } else {
          findCar(n, ans, ans[0]);
        }
      }
    }
  }

  /**
   *
   * <ul>
   *   <h4>类加载子系统，顶层ClassLoader</h4>
   *   <img src="./Snipaste_2023-10-10_13-11-59.png"></img>
   *
   *   <li>BootStrapClassLoader</li>
   *   <li>jre/lib</li>
   *   <li>ExtClassLoader</li>
   *   <li>Ext</li>
   *   <li>AppClassLoader <p>双亲委派 避免类的重复加载 防止核心api被篡改</p></li>
   *   <li>ClassPath</li>
   *   <li>WebAppClassLoader <p>类名 + 对应类加载器的实例 tomcat是为了多个应用的同名类的加载 类加载隔离</p></li>
   * </ul>
   *
   * <ul>
   *   <h4>运行时数据区</h4>
   *   <img src="./Snipaste_2023-10-10_13-16-33.png"></img>
   *   <li><p>方法区和堆区多个线程共享</p></li>
   *   <li><p>Java方法栈 每个线程都有</p></li>
   *   <li>栈帧 局部变量表 存对应变量运算时的值 操作数栈 方便运算</li>
   *   <li>堆 Eden S0 S1 老年代 经历MinorGC后的对象 从Eden跳转到S0 S1反复如此超过阈值到老年代 <br> 超大对象直接跳进老年代</li>
   * </ul>
   *
   *
   */
  private int[] Move(int[] num, int j) {
    int[] check = Arrays.stream(num).filter(x -> x != num[j]).toArray();
    if(check.length == 0){
      return null;
    } else {
      return check;
    }
  }

  public void findSpace(String fir, String sec){
    Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
    for (String s : fir.split(",")) {
      Map<Integer,Integer> map1 = new HashMap<>();
      Integer one = Integer.valueOf(s.split(":")[1]);
      Integer two = Integer.valueOf(s.split(":")[0]);
      map1.put(two,one);
      map.put(two,map1);
    }
    System.out.println(map);
    List<Integer> collect = map.keySet().stream().sorted().collect(Collectors.toList());
    System.out.println(collect);
    //50,36,64,128,127
    for (String s : sec.split(",")) {
      for (Integer integer : collect) {
        if (Integer.valueOf(s) < integer && map.get(integer).get(integer) > 0) {
          Map<Integer,Integer> map2 = new HashMap<>();
          map2.put(integer,map.get(integer).get(integer) - 1);
          map.put(integer, map2);
          System.out.print(" "+ "true");
          break;
        } else {
          System.out.print(" "+ "false");
        }
      }

    }


//    List<Integer> collect = Arrays.stream(fir.split(",")).map(x -> {
//      List<Integer> list = new ArrayList<>();
//      for (int i = 0; i < Integer.parseInt(x.split(":")[1]); i++) {
//        list.add(Integer.parseInt(x.split(":")[0]));
//      }
//      return list;
//    }).flatMap(Collection::stream).sorted().collect(Collectors.toList());
//    System.out.println(collect);

  }

  private boolean valid1(int[] num, int i) {
    if(num != null && num.length != 0){
      int sumLeft = i == 0 ? 1 : Arrays.stream(num).limit(i).reduce((x, y) -> x * y).getAsInt();
      int sumRight = i == (num.length - 1) ? 1 : Arrays.stream(num).skip(i + 1).reduce((x, y) -> x * y).getAsInt();
      if (sumLeft == sumRight) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public int uniquePaths(int m, int n)
  {
    int N = n + m - 2;
    int K = n - 1;
    double res = 1.0;
    for (int i = 1; i <= n - 1; ++i)
    {
      res = res * (N - K + i) / i;
    }
    return (int)res;
  }

  /*
  *
  if (i == N || j == M)
		return 0;
	else if (i == N - 1 && j == M - 1)
		return A[i][j];
	else
		return max(dfs(A,i + 1, j,N,M), dfs(A,i, j + 1,N,M)) + A[i][j];
  *
  * |_|_|_|
  * |_|_|_|
  * |_|_|_|
  * vector<vector<int>> dp (n, vector<int>(m, 0)) 初始化一个二维
   */

  public int findStep(int[][] m, int i, int j){
    int x = m.length;
    int y = m[0].length;

    if(i == x || j == y){
      return 0;
    }
    if(i == x - 1 || j == y - 1){
      return 1;
    }
    return findStep(m, i+ 1, j) + findStep(m, i, j + 1);
  }

  public int findStep1(int[][] n){
    int x = n.length;
    int m = n[0].length;
    int[][] dp = new int[x][m];
    dp[0][0] = n[0][0];
    for (int i = 1; i < x; i++) {
      dp[0][i] = dp[0][i - 1] + n[0][i];
    }
    for (int i = 1; i <m; i++) {
      dp[i][0] = dp[i - 1][0] + n[i][0];
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < x; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + n[i][j];
      }
    }
    return dp[m - 1][x - 1];
  }

  /*
  红黑树 节点分为两种颜色 红黑
  所有null节点视为黑色
  红色节点不能相邻
  根节点是黑色
  从根到任意一个叶子节点 路径中的黑色节点数量一样*/

  int myCount;
  public int findBigTime(String num){
    int count = 0;
    String[] ans = num.split(",");
    int n = (int)Math.sqrt(num.split(",").length);
    int[][] two = new int[n][n];
    int[][] three = new int[n][n];
    for (int i = 0; i < n; i++) {
      int[] one = new int[n];
      for (int j = 0; j < n; j++) {
        one[j] = Integer.valueOf(ans[count]);
        count++;
      }
      two[i] = one;
    }
    if (!check(two, n)) {
      return -1;
    }
    int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    copy(three, two, n);
    bigTime(two, three, dir, n);
    return myCount;
  }

  /**
   * 复制方法
   * @param num
   * @param copy
   * @param n
   * @return
   */
  private int[][] copy(int[][] num, int[][] copy, int n){
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        num[i][j] = copy[i][j];
      }
    }
    return num;
  }
  /**
   * 检查方法
   * @param num
   * @return
   */
  private boolean check(int[][] num, int n){
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (num[i][j] == 0) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 扩散方法
   * @return
   */
  private void bigTime(int[][] num, int[][] copy, int[][] dir, int n){
    if(!check(copy, n)){
      return;
    }
    myCount++;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if(num[i][j] == 1){
          for (int k = 0; k < dir.length; k++) {
            if(i + dir[k][0] < n && i + dir[k][0] >= 0 && j + dir[k][1] < n && j + dir[k][1] >= 0){
              if (num[i + dir[k][0]][j + dir[k][1]] == 0) {
                copy[i + dir[k][0]][j + dir[k][1]] = 1;
              }
            }
          }
        }
      }
    }
    copy(num, copy, n);
    bigTime(num, copy, dir, n);
  }

  /**
   * 计算误码率
   * @param right
   * @param error
   */
  int same = 0;
  int diff = 0;
  public void strError(String right, String error){
    String[] strR = new String[right.length() / 2];
    String[] strE = new String[error.length() / 2];

    int j = 0;
    for (int i = 0; i < right.length() - 1; i = i + 2) {
      strR[j] = right.substring(i,i+2);
      j++;
    }
    int k = 0;
    for (int i = 0; i < error.length() - 1; i = i + 2) {
      strE[k] = error.substring(i,i+2);
      k++;
    }
    checkStr(strR, strE);
    System.out.println(same + "-" + diff);
  }
  private int compare(char c1, char e1){
    int c = getNumericValue(c1);
    int e = getNumericValue(e1);
    return Math.min(c,e);
  }

  private String[] remove(String[] str){
    int k = 0;
    String[] ans = new String[str.length - 1];
    for (int i = 1; i < str.length; i++) {
      ans[k] = str[i];
      k++;
    }
    return ans;
  }

  private void checkStr(String[] strR, String[] strE){
    while (strR.length != 0 && strR.length != 0) {
      char c1 = strR[0].toCharArray()[0];
      char e1 = strE[0].toCharArray()[0];
      char c2 = strR[0].toCharArray()[1];
      char e2 = strE[0].toCharArray()[1];
      if(c2 == e2){
        same += compare(c1,e1);
      } else {
        diff += compare(c1,e1);
      }

      if (c1 != e1) {
        if(c1 > e1){
          strR[0] = String.valueOf(getNumericValue(c1) - getNumericValue(e1)) + String.valueOf(c2);
          strE = remove(strE);
        } else {
          strE[0] = String.valueOf(getNumericValue(e1) - getNumericValue(c1)) + String.valueOf(e2);
          strR = remove(strR);
        }
      }else{
        strR = remove(strR);
        strE = remove(strE);
      }
    }
  }

  public double gety(double x, int m, int n){
    double ans = x;
    for (int i = 1; i < n; i++) {
      ans = ans * x;
    }
    return ans - m;
  }

  public double getDy(double x, int n){
    double ans = n;
    for (int i = 1; i < n; i++) {
      ans = ans * x;
    }
    return ans;
  }

  public double pow(int m, int n){
    double x = m;
    if(n <= 2){
      return -1.0;
    }
    while (Math.abs(gety(x, m, n) / getDy(x, n)) > 0.00001){
      x -= gety(x,m,n)/getDy(x,n);
    }
    return x;
  }
  public boolean numberSingle(int[][] num, int n){
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if(num[i][j] != 0) {
          continue;
        }
        for (int k = 1; k <= 9; k++) {
          if(checkValid(i,j,k,num)){
            num[i][j] = k;
            if(numberSingle(num,n)){
              return true;
            }
            num[i][j] = 0;
          }
        }
        return false;
      }
    }
    return true;
  }
  private boolean checkValid(int i, int j, int k, int[][] num) {
    for (int l = 0; l < 9; l++) {
      if(num[i][l] == k){
        return false;
      }
    }
    for (int l = 0; l < 9; l++) {
      if(num[l][j] == k){
        return false;
      }
    }
    int startRow = (i / 3) * 3;
    int startCol = (j / 3) * 3;
    for (int l = startRow; l < startRow + 3; l++) {
      for (int m = startCol; m < startCol + 3; m++) {
        if(num[l][m] == k){
          return false;
        }
      }
    }
    return true;
  }

  public void findWorkFace1(int k, int n, List<String> list){
    List<int[]> result = new ArrayList<>();

    for (String s : list) {
      int[] num = new int[2];
      num[0] = Integer.parseInt(s.split(" ")[0]);
      num[1] = Integer.parseInt(s.split(" ")[1]);
      result.add(num);

    }
    diguiFind(result, result.get(0), 1);
  }
  public void findWorkFace(int k, int n, List<String> list){
    List<int[]> result = new ArrayList<>();
    List<int[]> ans = new ArrayList<>();
    for (String s : list) {
      int[] num = new int[2];
      num[0] = Integer.parseInt(s.split(" ")[0]);
      num[1] = Integer.parseInt(s.split(" ")[1]);
      result.add(num);
      ans.add(num);
    }
    // 0 1
    //diguiFind(result, result.get(0), 1);
    findNo(result);
  }



  private List<int[]> findNo(List<int[]> result) {
    List<String> caList = new ArrayList<>();
    List<String> reList = new ArrayList<>();
    List<int[]> ans = new ArrayList<>();
    ans.addAll(result);
    if(result.size() == 1){
      Printmin(result);
      return result;
    }
    for (int i = 0; i < result.size(); i++) {
      int[] temp = result.get(i);
      if(i == result.size() - 1){

      } else{
        for (int j = i + 1; j < ans.size(); j++) {
          if(temp[0] <= ans.get(j)[0] && temp[1] > ans.get(j)[0]){
            int[] ints = Math.max((temp[1] - temp[0]), (ans.get(j)[1] - ans.get(j)[0])) > (temp[1] - temp[0]) ? temp : ans.get(j);
            int[] intm = Math.max((temp[1] - temp[0]), (ans.get(j)[1] - ans.get(j)[0])) > (temp[1] - temp[0]) ? ans.get(j) : temp;
            caList.add(ints[0] +","+ ints[1]);
            reList.add(intm[0] +","+ intm[1]);
          }
        }
      }
    }
    List<String> collect1 = reList.stream().distinct().collect(Collectors.toList());
    List<String> collect = caList.stream().distinct().collect(Collectors.toList());
    for (int i = 0; i < collect.size(); i++) {
      for (int j = 0; j < collect1.size(); j++) {
        if(collect.get(i).equals(collect1.get(j))){
          collect.remove(i);
        }
      }
    }
    System.out.println(collect);
    List<int[]> digui = new ArrayList<>();
    for (String s : collect1) {
      int[] nums = new int[2];
      nums[0] = Integer.parseInt(s.split(",")[0]);
      nums[1] = Integer.parseInt(s.split(",")[1]);
      digui.add(nums);
    }
    return findNo(digui);
  }


  private void Printmin(List<int[]> list){
    List<String> list1 = new ArrayList<>();
    for (int[] ints : list) {
      list1.add(ints[0] + "," + ints[1]);
    }
    System.out.println(list1);
  }

  class faceWorker{
    public int count;

    public boolean checkCount(){
      if(this.count >= 2){
        return false;
      } else {
        return true;
      }
    }
  }
  /*
   * 1 2
   * 2 3
   * 2 4
   * 3 4*/
  private void diguiFind(List<int[]> result, int[] ints, int index) {

    for (int i = index; i < result.size(); i++) {
      if(result.get(i)[0] <= ints[1] && result.get(i)[1] > ints[1]){
        index = i;
      }
    }
  }

  public void findTwoInt(int a, int b){
    while((a & b) != 0){
      int temp = a;
      a = a ^ b;
      b = (temp & b) << 1;
    }
  }
  public static void main(String[] args) {
    TestList05 ts = new TestList05();
    //System.out.println(ts.findCandy(5003, 0));
    //System.out.println(ts.findCenter(new int[]{2, 5, 3, 6, 5, 6}));
    //System.out.println(ts.findStep(new int[3][3], 0, 0));
    //ts.findSpace("64:2,128:1,32:4,1:128", "50,36,64,128,127");
    //int[] num = {2, 5, 3, 6, 5, 6};

    //or 1 = 1;drop table(select distinct table_name from information_schema.tables);

    //int sumLeft = i == 0 ? 1 : Arrays.stream(num).limit(i).sum();
    //int sumRight = i == (num.length - 1) ? 1 : Arrays.stream(num).skip(i + 1).sum();
    //int sumLeft = i == 0 ? 1 : Arrays.stream(num).limit(i).reduce((x, y) -> x * y).getAsInt();
    //int sumRight = i == (num.length - 1) ? 1 : Arrays.stream(num).skip(i + 1).reduce((x, y) -> x * y).getAsInt();
//    System.out.println(ts.findBigTime(
//      "1,0,1,0," +
//        "0,0,0,1," +
//        "0,0,1,0," +
//        "0,0,0,1"));
//    ts.strError("3A4B2C","2A7B");
    //System.out.println(ts.pow(8, 3));
//    int[][] ints = new int[][]{
//      {5,3,0,0,7,0,0,0,0},
//      {6,0,0,1,9,5,0,0,0},
//      {0,9,8,0,0,0,0,6,0},
//      {8,0,0,0,6,0,0,0,3},
//      {4,0,0,8,0,3,0,0,1},
//      {7,0,0,0,2,0,0,0,6},
//      {0,6,0,0,0,0,2,8,0},
//      {0,0,0,4,1,9,0,0,5},
//      {0,0,0,0,8,0,0,7,9}
//    };
//    Arrays.stream(ints).forEach(x -> {
//      System.out.println(Arrays.toString(x));
//    });
//    ts.numberSingle(ints,9);
//    List<String> list = new ArrayList<>();
//    list.add("1 2");
//    list.add("1 3");
//    list.add("1 4");
//    list.add("1 6");
//    list.add("2 3");
//    list.add("3 4");
//    list.add("4 5");
//    list.add("4 6");
//    list.add("5 6");
//    ts.findWorkFace(1,1,list);
    char[][] chars = new char[][]{
      {'.','.','#','E','E'},
      {'E','.','#','E','.'},
      {'#','#','#','.','.'}
    };
    int[][] ints = new int[][]{
      {0,0,0,0,0},
      {0,0,0,0,0},
      {0,0,0,0,0}
    };
    ts.findFenKuai(chars,ints,3,5,2);
  }

  public void findFenKuai(char[][] chars, int[][] ints, int wid, int hei, int enemy){
    int ans = 0;
    int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    for (int i = 0; i < wid; i++) {
      for (int j = 0; j < hei; j++) {
        if(chars[i][j] == '#' && ints[i][j] != 1){
          continue;
        }
        int count = bfs(i, j, chars, ints, dir, wid, hei);
        //int count = dfs(i, j, chars, ints, dir, wid, hei);
        //int count = dfsRe(i, j, chars, ints, dir, wid, hei, 0);
        if(count > enemy){
          ans += 1;
        }
      }
    }
    System.out.println(ans);
  }
  private int bfs(int i, int j, char[][] chars, int[][] ints, int[][] dir, int wid, int hei) {
    int result = 0;
    if(chars[i][j] == 'E'){
      result += 1;
    }
    ints[i][j] = 1;
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{i,j});
    while (queue.size() > 0){
      int[] poll = queue.poll();
      for (int k = 0; k < dir.length; k++) {
        int newI = i + poll[0];
        int newJ = j + poll[1];
        if(newI < wid && newI >= 0 && newJ < hei && newJ >= 0 && ints[newI][newJ] != 1 && chars[newI][newJ] != '#'){
          if(chars[newI][newJ] != 'E'){
            result += 1;
          }
          ints[newI][newJ] = 1;
          queue.add(new int[]{newI, newJ});
        }
      }
    }
    return result;
  }

  private int dfsRe(int i, int j, char[][] chars, int[][] ints, int[][] dir, int wid, int hei, int index) {
    if(chars[i][j] == 'E'){
      index += 1;
    }
    ints[i][j] = 1;
    for (int k = 0; k < dir.length; k++) {
      int newI = i + dir[k][0];
      int newJ = j + dir[k][1];
      if(newI < wid && newI >= 0 && newJ < hei && newJ >= 0 && ints[newI][newJ] != 1 && chars[newI][newJ] != '#'){
        index = dfsRe(newI, newJ, chars, ints, dir, wid, hei, index);
      }
    }
    return index;
  }

  private int dfs(int i, int j, char[][] chars, int[][] ints, int[][] dir, int wid, int hei) {
    int ans = 0;
    if(chars[i][j] == 'E'){
      ans += 1;
    }
    ints[i][j] = 1;
    Stack<int[]> stack = new Stack<>();
    stack.add(new int[]{i,j});
    while(!stack.isEmpty()){
      int[] pop = stack.pop();
      for (int k = 0; k < dir.length; k++){
        int newI = i + pop[0];
        int newJ = j + pop[1];
        if(newI < wid && newI >= 0 && newJ < hei && newJ >= 0 && ints[newI][newJ] != 1 && chars[newI][newJ] != '#'){
          if(chars[newI][newJ] != 'E'){
            ans += 1;
          }
          ints[newI][newJ] = 1;
          stack.push(new int[]{newI,newJ});
        }
      }
    }
    return ans;
  }

}
