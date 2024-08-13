package com.suanfa.test;

import com.suanfa.gpthelp.FunctionFind;
import com.struct.Heap.Heap;
import com.struct.Heap.MinHeap;
import com.struct.Tree.TreeNodeString;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <ul>
 *   <li>算法03</li>
 * </ul>
 */
public class TestList03 {

  public static class GIS {
    int x;
    int y;

    public GIS() {
    }

    public GIS(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }

    @Override
    public String toString() {
      return "GIS{" +
        "x=" + x +
        ", y=" + y +
        '}';
    }
  }

  public static class BookWH {
    private int width;
    private int height;

    public int getWidth() {
      return width;
    }

    public void setWidth(int width) {
      this.width = width;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    public BookWH(int width, int height) {
      this.width = width;
      this.height = height;
    }

    @Override
    public String toString() {
      return "BookWH{" +
        "width=" + width +
        ", height=" + height +
        '}';
    }
  }


  class Block{
    int max; //块内最大值
    int startIndex; //起始索引
    int endIndex; //结束索引
  }

  //查索引
  public static int getIndex(Block[] blockArray, int array[], int number){
    int indexBlock = findIndexBlock(blockArray, number);
    if(indexBlock == -1){
      return -1;
    }
    int startIndex = blockArray[indexBlock].startIndex;
    int endIndex = blockArray[indexBlock].endIndex;
    for (int i = startIndex; i <= endIndex; i++) {
      if(array[i] == number){
        return i;
      }
    }
    return 0;
  }

  //查找块
  public static int findIndexBlock(Block[] block, int number){
    for (int i = 0; i < block.length; i++) {
      if(number <= block[i].max){
        return i;
      }
    }
    return -1;
  }

  public BookWH checkBook(BookWH book1, BookWH book2){
    if (book1.getWidth() <= book2.getWidth() && book1.getHeight() <= book2.getHeight()) {
      return book1;
    } else if (book1.getWidth() >= book2.getWidth() && book1.getHeight() >= book2.getHeight()) {
      return book2;
    }
    return null;
  }


  public int findBook(List<BookWH> bookStk){
    List<BookWH> books = new ArrayList<>();
    bookStk.sort(new Comparator<BookWH>() {
      @Override
      public int compare(BookWH o1, BookWH o2) {
        return o1.getWidth() - o2.getWidth();
      }
    });
    BookWH min = bookStk.get(0);
    books.add(min);
    for (int i = 1; i < bookStk.size(); i++) {
      if(bookStk.get(i).getHeight() >= min.getHeight()){
        books.add(bookStk.get(i));
      }
      min = bookStk.get(i);
    }
    System.out.println(books);
    System.out.println(bookStk);
    return books.size();
  }

  List<String> strs = new ArrayList<>();

  public void strdigui(String str){
    Stack<Character> strstk = new Stack<>();
    char[] chars = str.toCharArray();
    for (int i = 1; i <= chars.length; i++) {
      char[] newChar = new char[i];
      System.arraycopy(chars,0,newChar,0,i);
      dfsStr(newChar, strstk);
    }

  }

  private void dfsStr(char[] str, Stack<Character> strstk) {
    if(str.length == 0){
      System.out.println(strstk);
    }
    for (int i = 0; i < str.length; i++) {
      char[] temp = new char[str.length - 1];
      System.arraycopy(str,0,temp,0,i);
      System.arraycopy(str,i+1,temp,i,str.length-i-1);
      strstk.push(str[i]);
      dfsStr(temp,strstk);
      strstk.pop();
    }
  }

  public void findAbs(int[] nums){
    int min = Math.abs(nums[0] + nums[1]);
    find(nums,0, min);
  }

  private void find(int[] nums, int i, int min) {
    if(i == nums.length - 1){
      System.out.println(min);
      return;
    }
    for (int j = i + 1; j < nums.length; j++) {
      if (Math.abs(nums[j] + nums[i]) < min) {
        min = Math.abs(nums[j] + nums[i]);
      }
    }
    find(nums,i+1, min);
  }

  public void topK(int[] nums, int K){
    int[] ints = sort(nums);
    int j = 0;
    for (int i = nums.length - 1; j < K; i--,j++) {
      System.out.println(ints[i]);
    }
  }

  public static int[] sort(int[] n){
    //校验
    if (!isEmpty(n)) {
      int pivot = n[0];
      //去掉首个
      int[] rest = remove(n,0);
      //重复递归过滤大小值
      int[] small = sort(Arrays.stream(rest).filter(x  ->x < pivot).toArray());
      int[] big = sort(Arrays.stream(rest).filter(x  ->x >= pivot).toArray());
      //拼接
      return IntStream.concat(IntStream.concat(Arrays.stream(small),IntStream.of(pivot)),Arrays.stream(big)).toArray();
    }return n;
  }

  private static int[] remove(int[] n, int i) {
    int[] newInts = new int[n.length - 1];
    System.arraycopy(n,0,newInts,0,i);
    System.arraycopy(n,i+1,newInts,i,n.length - i - 1);
    return newInts;
  }

  public static boolean isEmpty(int[] n){
    return n.length == 0;
  }


  public void climbMount(int[][] TowInts, int k, int m, int n){
    climbe(TowInts, 0, 0, m, n, k);
  }

  private void climbe(int[][] towInts, int x, int y, int m, int n, int k) {
    GIS v;
    if((v = checkClimbe(towInts, x, y, m, n)) == null){
      return;
    }
    climbe(towInts,v.getX(),v.getY(),m,n,k);
  }

  private GIS checkClimbe(int[][] towInts, int x, int y, int m, int n) {
    int value = towInts[x][y];
    int xc =0;
    int yc =0;
    int[][] check = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    for (int i = 0; i < 4; i++) {
      xc = x + check[i][0];
      yc = y + check[i][1];
      if(xc < m && xc >= 0 && yc < n && yc >= 0){
        if(value < towInts[xc][yc]){
          System.out.println(xc+","+yc);
          return new GIS(xc,yc);
        }
      }else {
        continue;
      }
    }
    return null;
  }

  int[][] check = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
  int value = 0;
  public void matrix(int[][] towInts,int k){
    int row = towInts.length;
    int col = towInts[0].length;
    for (int i = 0; i < row; i++) {
      for(int j = 0;j < col;j++){
        checkClimbMine(towInts,i,j,row,col,k);
      }
    }
  }
  private void checkClimbMine(int[][] towInts, int x, int y, int m, int n,int k) {
    for (int i = 0; i < 4; i++) {
      int xc = x + check[i][0];
      int yc = y + check[i][1];
      if(xc >= 0 && xc < m && yc >= 0 && yc < n){
        if(towInts[xc][yc] > value){
          value = towInts[xc][yc];
          System.out.println(xc +","+yc);
          checkClimbMine(towInts,xc,yc,m,n,k);
        }
      }
    }
  }

  private Heap left = new Heap(10);
  private MinHeap right = new MinHeap(10);

  public void addNum(int num){
    if(left.getSize() == right.getSize()){
      right.offer(num);
      left.offer(right.poll());
    }else {
      left.offer(num);
      right.offer(left.poll());
    }
  }

  private PriorityQueue<Integer> leftQ = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
  private PriorityQueue<Integer> rightQ = new PriorityQueue<>();

  public double findMedian(){
    if(left.getSize() == right.getSize()){
      return (left.peek() + right.peek()) / 2.0;
    } else {
      return left.peek();
    }

  }

  public void freeMusic(String str, String str2){
    String[] split = str.split(" ");

  }



  public void bonusMoney(int k, int[] num){
    int max = num[0];
    int index = 0;
    for (int i = 0; i < k; i++) {
      if(num[i] > max){
        int temp = max;
        int tempIndex = index;
        max = num[i];
        index = i;
        System.out.println((max - temp) * (i - tempIndex));
      }else {
        System.out.println(num[i]);
      }
    }
  }

  int k;
  int index;
  public void bonusFinal(int[] num){
    k = num[0];
    index = 0;
    for (int i = 0; i < num.length; i++) {
      bonusFind(i,num);
    }
  }
  /*
   * 最值指向第一个 后面的比前面的大就 指向当前
   * 2 10 9 0 8 1 7 3
   *                ^
   * */
  public void bonusFind(int n, int[] num){
    int[] nums = new int[num.length - n - 1];
    if(n != num.length - 1){
      System.arraycopy(num, n + 1, nums, 0, num.length - n - 1);
    } else {
      System.out.println(num[n]);
    }
    for (int i = 0; i < nums.length; i++) {
      if(k < nums[i]){
        k = nums[i];
        index = i;
        System.out.print((nums[0] - num[n]) * (n + 1 - index) +"\t");
      }else{
        System.out.print(num[n]+"\t");
      }
    }
  }


  public TreeNodeString constructExpressionTrees(String[] tokens){
    LinkedList<TreeNodeString> stack = new LinkedList<>();
    for (String t : tokens) {
      switch(t){
        case "+":
          TreeNodeString right = stack.pop();
          TreeNodeString left = stack.pop();
          TreeNodeString root = new TreeNodeString(t);
          root.left = left;
          root.right = right;
          stack.push(root);
          break;
        default:
          stack.push(new TreeNodeString(t));
          break;
      }
    }
    return stack.peek();
  }

  public void hashTest(){
    Hashtable<String,Integer> tb = new Hashtable<>();

  }

  static String[] number = new String[]
    {
      "A","B","C","D",
      "E","F","G","H",
      "I","G","K","L",
      "M","N","O","P",
      "Q","R","S","T",
      "U","V","W","X",
      "Y","Z"
    };
  static String[] byteNumber = new String[]
    {
      "1","2","3","4","5","6","7","8","9"
    };
  public static void scannerWord(String[] stringS, int rows, int cols){
    //a-z 1-9
    Map<String, String> map = new HashMap<>();
    for (int i = 0; i < stringS.length; i++) {
      String[] split = stringS[i].split(" ");
      System.out.println(Arrays.toString(split));
      String num = byteNumber[i];
      for (int j = 0; j < split.length; j++) {
        String byteN = number[j];
        map.put(byteN + num, split[j]);
      }
    }
    System.out.println(map);

  }

  public void aVoid(Map<String, String> map, String begin, String end){
    int sum = 0;
    char b1 = begin.toCharArray()[0];
    char e1 = end.toCharArray()[0];
    char b2 = begin.toCharArray()[1];
    char e2 = end.toCharArray()[1];

    for (int i = 0; i <= e2 - e1; i++) {
      for (int j = 0; j <= b2 - b1; j++) {
        char b = (char) (b1 + i);
        char n = (char) (b2 + j);
        char[] c = new char[]{b,n};
        String s = new String(c);
        tryResolve(sum,map,map.get(s));
      }
    }
  }

  public String[][] resolves(String[] stringS, int rows, int cols){
    String[][] st = new String[rows][cols];
    for (int i = 0; i < rows; i++) {
      String string = stringS[i];
      for (int j = 0; j < cols; j++) {
        String[] s = string.split(" ");
        st[i][j] = s[j];
      }
    }
    return st;
  }

  public void play(String[][] strs, String begin, String end, Map<String, String> map){
    char b1 = begin.toCharArray()[0];
    char e1 = end.toCharArray()[0];
    int b2 = Character.getNumericValue(begin.toCharArray()[1]);
    int e2 = Character.getNumericValue(end.toCharArray()[1]);
    int sum = 0;
    for (int j = b2; j < e2 - b2 + 1; j++) {
      for (int i = 0; i < strs[j].length; i++) {
        if (strs[j][i].charAt(0) == '=') {
          String s = strs[j][i].replace('=', ' ').trim();
          if(s.contains("+")){
            Integer s1 = Integer.parseInt(map.get(s.split("\\+")[0]));
            Integer s2 = Integer.parseInt(map.get(s.split("\\+")[1]));
            sum = s1 + s2 + sum;
          } else if (s.contains("-")) {
            Integer s1 = Integer.parseInt(map.get(s.split("\\-")[0]));
            Integer s2 = Integer.parseInt(map.get(s.split("\\-")[1]));
            sum = s1 - s2 + sum;
          } else if (s.contains("*")) {
            Integer s1 = Integer.parseInt(map.get(s.split("\\*")[0]));
            Integer s2 = Integer.parseInt(map.get(s.split("\\*")[1]));
            sum = s1 * s2 + sum;
          } else {
            Integer s1 = Integer.parseInt(map.get(s.split("\\/")[0]));
            Integer s2 = Integer.parseInt(map.get(s.split("\\/")[1]));
            sum = s1 / s2 + sum;
          }
        }
      }
    }
    System.out.println(sum);
  }
  private void tryResolve(int sum, Map<String, String> map, String s) {

    if(s.contains("=")){
      s.split("[\\+\\-\\*/]");

    }
  }

  public int findLong(int[] nums, int key){
    int j = 0;
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      if(nums[i] == key){
        j++;
      } else {
        if(j > max){
          max = j;
        }
        j = 0;
      }
    }
    if(j > max) {
      max = j;
    }
    return max;
  }

  /*
  *  a       b      a
  * f(i)  f(i+1) f(i+2)
  * begin   end    end+1
  *
  * */
  public void FiveChess(int[] nums, FunctionFind find){
    int ma = 0;
    int[] ints = new int[]{1,0,-1};
    for (int j = 0; j < ints.length; j++) {
      for (int i = 0; i < nums.length; i++) {
        int[] newInts = new int[nums.length];
        System.arraycopy(nums,0,newInts,0,nums.length);
        newInts[i] = ints[j];
        int max = find.findLong(newInts, ints[j]);
        //int max = findLong(newInts, ints[j]);
        if(max > ma){
          ma = max;
        }
      }
    }
    System.out.println(ma);
  }

  /**
   * total 5 speed 1 day 0   s = v * t
   * 橡皮筋长5m
   * 蜗牛从一端每天爬一米
   * 每爬1m 橡皮筋就瞬间拉长5m 问多少天爬到头
   */
  public static void main(String[] args) {
    TestList03 ts = new TestList03();
//    BookWH bookWH1 = new BookWH(23, 1);
//    BookWH bookWH2 = new BookWH(12, 19);
//    BookWH bookWH3 = new BookWH(17, 39);
//    BookWH bookWH4 = new BookWH(18, 5);
//    BookWH bookWH5 = new BookWH(4, 7);
//    BookWH bookWH6 = new BookWH(42, 41);
//    List<BookWH> list = new ArrayList<>();
//    list.add(bookWH1);
//    list.add(bookWH2);
//    list.add(bookWH3);
//    list.add(bookWH4);
//    list.add(bookWH5);
//    list.add(bookWH6);
//    ts.findBook(list);

    //ts.topK(new int[]{1, 4, 7, 2, 0, 8, 6, 5, 9, 11}, 4);
//    int[][] nums = new int[][]{
//      {0, 1, 2, 0},
//      {1, 0, 0, 0},
//      {1, 0, 1, 2},
//      {1, 3, 1, 0},
//      {0, 0, 0, 9}
//    };
//    ts.matrix(nums,1);
    //int[] num = new int[]{2 ,10 ,9 ,0 ,8 ,1 ,7 ,3};
    //ts.bonusFinal(num);
    //String[] str = new String[]{"10 12 =C5", "15 5 6" , "7 8 =3+C2", "6 =B2-A1 =C2"};
    //ts.resolves(str,4,3);
    //scannerWord(str,4,3);
    //int[] nums = new int[]{-1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,-1 ,1};
    /*ts.FiveChess(nums, (int[] num, int key) ->{
      int j = 0;
      int max = 0;
      for (int i = 0; i < num.length; i++) {
        if(num[i] == key){
          j++;
        } else {
          if(j > max){
            max = j;
          }
          j = 0;
        }
      }
      if(j > max) {
        max = j;
      }
      return max;
    });*/
    //ts.WoNiu2(5.0,1.0,0,0.0,5.0);
    //ts.Easy(5.0,0);
    ts.my_fun(5);

  }

  // _ _ _ _ _
  //         ^
  // _ _| _ _ |_ _ |_ _ _ |_ _ _
  //                  ^
  // _ _ _ |_ _ _ |_ _ _ |_ _ _ |_ _ _
  // x / y + (x % y != 0 ? 1 : 0);

  public void WoNiu(double left, double right, double result){
    while (right > 1){
      right -= 1;
      left += 1;
      double temp = left + right;
      left += left * 5 / temp;
      right += right * 5 / temp;
      ++result;
    }
    System.out.println(result + 1);
  }

  public void WoNiu2(double BeforeTotal, double speed, int day, double AlreadyTotal, double total){
    while (BeforeTotal > 0){
      AlreadyTotal += speed;
      BeforeTotal = total - AlreadyTotal;

      double temp =  total / 5.0;
      double AT = AlreadyTotal / temp;
      AlreadyTotal = AlreadyTotal + AT;

      total += 5.0;
      day++;
    }
    System.out.println(day);
  }

  //volatile  synchronized
  // 可见性     可见性 原子性
  // 变量        类 方法 变量
  //volatile 关键字主要⽤于解决变量在多个线程之间的可⻅性，⽽ synchronized 关键字解决
  //的是多个线程之间访问资源的同步性。
  public void Easy(double delta, int count){
    double sum = 0;
    while (sum < 1){
      sum = sum + (1.0 / delta);
      delta += 5.0;
      count++;
    }
    System.out.println(count);
  }

  public void Monitor(int[][] v, int[][] m, int x, int y){
    int[][] pos = new int[][]{{0,0},{1,0},{-1,0},{0,1},{0,-1}};
    for (int i = 0; i < v.length; i++) {
      for (int j = 0; j < v[i].length; j++) {
        if (v[i][j] == 1) {
          openMonitor( i, j, m, pos, x, y);
        }
      }
    }
    System.out.println(Arrays.toString(m));
  }

  private void openMonitor(int i, int j, int[][] m, int[][] pos, int key, int tak) {
    for (int l = 0; l < pos.length; l++) {
      if((i + pos[l][0]) > 0 && (j + pos[l][1]) > 0 && (j + pos[l][1]) < key && (i + pos[l][0]) < tak && m[i + pos[l][0]][j + pos[l][1]] != 1){
        m[i + pos[l][0]][j + pos[l][1]] = 1;
      }
    }
  }
//  String s1 = "";
//    while (n > 0){
//    s1 = n % 8 + " " + s1;
//    n = n / 8;
//  }
  public void my_fun(int n){
    int s = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        s++;
      }
    }
    System.out.println(s);
  }

}




