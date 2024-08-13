package com.suanfa.test;

import java.util.*;
import java.util.stream.Collectors;

public class TestList06 {

  List<String> list = new ArrayList<>();
  public void findChangeElectronic(String str1, String str2){
    int count = 0;
    int i = Integer.valueOf(str1, 2) | Integer.valueOf(str2, 2);
    char[] chars = str1.toCharArray();
    change(chars,1,0);
    for (String s : list) {
      int i1 = Integer.valueOf(s, 2) | Integer.valueOf(str2, 2);
      if(i != i1){
        count++;
      }
    }
    System.out.println(count);
  }
  private void change(char[] str1, int i, int i1) {
    if(i1 == str1.length - 1){
      return;
    }
    char[] chars = new char[str1.length];
    int count = 0;
    int j = i;
    while (count < str1.length - i1 - 1){
      System.arraycopy(str1,0,chars,0,str1.length);
      charge(chars,i1,j);
      String str = new String(chars);
      list.add(str);
      j++;
      count++;
    }
    change(str1,i+1,i1+1);
  }
  private void charge(char[] chars, int i1, int i) {
    char temp = chars[i1];
    chars[i1] = chars[i];
    chars[i] = temp;
  }

  private char[] remove(char[] str1) {
    char[] chars = new char[str1.length - 1];
    System.arraycopy(str1,1,chars,0,str1.length - 1);
    return chars;
  }

  //用于记录str2子串的答案
  List<String> listT = new ArrayList<>();
  //用于记录str1排列的答案
  List<String> listA = new ArrayList<>();

  /**
   * 入口方法 并对分步结果进行比较
   * @param str1 要排列的字符串
   * @param str2 要找的字符串
   */
  public void findTwoStr(String str1, String str2){
    //构建str1排列深搜的栈（先进后出）
    Stack<Character> stack = new Stack<>();
    //str1排列方法，注释见方法头
    StrAll(str1,str1.length(),stack);
    //此处循环是确定要输出的子串长度，即是i的值
    for (int i = 1; i <= str2.length(); i++) {
      //str2采用顺序组合放开下面两行注掉子串方法
      //List<Character> list = new ArrayList<>();
      //StrPart(str2,i,0,list);
      //str2循环找规定长度的子串，注释见方法头
      StrPart1(str2,i);
    }
    //两层循环依次比较，外层拿listT的值
    for (int i = 0; i < listT.size(); i++) {
      //内层拿listA的值
      for (String c : listA) {
        //比较方法
        if (c.equals(listT.get(i))) {
          //输出结果
          System.out.println(listT.get(i));
        }
      }
    }
    System.out.println(listT);
  }

  /**
   * 排列方法
   * @param str1 要排列的字符串
   * @param length 字符串长度
   * @param stack 本层结果集记录
   */
  private void StrAll(String str1, int length, Stack<Character> stack) {
    //本层递归结束判定条件 当前结果栈stack长度等于指定长度length则结束本层
    //eg：stack[a,b,c].size() == 3 true
    if(stack.size() == length){
      //字符串拼接对象
      StringBuilder sb = new StringBuilder();
      //循环当前栈
      for (Character c : stack) {
        //拼接栈中每一个字符为一个字符串
        sb.append(c);
      }
      //添加到最终结果集
      listA.add(sb.toString());
      //返回上一层，即154行
      return;
    }
    //字符串变为字符数组 eg："abc" ['a','b','c']
    char[] chars1 = str1.toCharArray();
    //循环此字符数组
    for (int j = 0; j < chars1.length; j++) {
      //本层结果集-栈，记录当前字符 eg：chars = ['a','b','c'] chars[0] = 'a'
      //先进后出
      //eg：a,b,c,d a先入栈push 再b 再c 再d 弹栈pop 先d 再c 再b 再a
      // 栈顶入口也是出口
      // |   |  |   |  |   |  |   |     |   |
      // |   |  |   |  |   |  | c |     |   |
      // |   |  |   |  | b |  | b | ... | b | ...
      // |___|  |_a_|  |_a_|  |_a_|     |_a_|
      // 栈底封死
      //push压栈，存入栈底，参考上图
      stack.push(chars1[j]);
      //字符串截取，str1.length()为字符串长度，substring方法详见172行注释
      String s1 = str1.substring(0, j) + str1.substring(j + 1, str1.length());
      //递归调用StrAll，即回到118行，传入参数都不做改变
      StrAll(s1,length,stack);
      //此处是关键，遍历到底返回时回溯弹栈（弹出栈顶的第一个元素），参考上图 具体逻辑类似组合方法可参考下面方法，只不过组合规定个数，全排列为全长度有几个元素排几个元素
      stack.pop();
    }
  }

  /**
   * 子串处理方法
   * @param str2 要找子串的字符串
   * @param i 子串的长度
   * eg：要找子串的字符串 abc 子串-长度 [abc-3, ab-2, bc-2, a-1, b-1, c-1]
   */
  private void StrPart1(String str2, int i) {
    //指针位p 指向str2第一个索引位置
    int p = 0;
    //while循环 p指向str2最后一个索引位置时退出
    while (p <= str2.length() - i){
      //截取位指针temp 指针位p加截取长度i
      int temp = p + i;
      //截取后字符串substring
      //eg："abc"字符串通过(begin = 1, end = 3)时的substring(begin, end)方法后得 "bc"，此方法的左右两值为左闭右开区间，字符串起始索引为0，如"abc"[0] = 'a'
      String substring = str2.substring(p, temp);
      //添加结果到结果集
      listT.add(substring);
      //指针位后移一位 下次循环从修改后的p向后截取i位
      //eg："a b c"
      //     ^
      //    "a b c"
      //       ^
      p = p + 1;
    }
  }

  /**
   * 顺序组合方法
   * @param str2 字符串
   * @param i 组合长度 eg：abc 取两个组合即 i=2
   * @param k 遍历位置 eg：以两个组合为例 abc 从a->b->c 到判定处回退一位c，则a->b 此时k为2再循环将b退出 则a 此时k为1继续循环k+1 则a->c
   * @param sb 本层结果记录
   */
  private void StrPart(String str2, int i,int k, List<Character> sb) {
    //本层递归结束判定条件 当前结果集合sb长度等于指定长度i则结束本层
    //eg：sb[a,b,c].size() == 3 true
    if(sb.size() == i){
      //字符串拼接对象
      StringBuilder sbr = new StringBuilder();
      //循环当前集合
      for (Character c : sb) {
        //拼接集合中每一个字符为一个字符串
        sbr.append(c);
      }
      //添加到结果集
      listT.add(sbr.toString());
      //返回此层的下一步 即225行
      return;
    }
    //字符串变为字符数组 eg："abc" ['a','b','c']
    char[] chars = str2.toCharArray();
    //循环此字符数组 使用k记录当前遍历位置 方便回退
    for (int j = k; j < chars.length; j++) {
      // 1 a 2 b 3 c 由于c进入时j = 3，所以不进入循环直接进入退出c，b此时j = 2则又跳出循环再退出b，a此时j = 1再循环就j = 2加入c，重复此过程则完成组合
      //添加数组对应索引值的字符进本层结果集 eg：chars = ['a','b','c'] chars[0] = 'a'
      sb.add(chars[j]);
      //递归调用StrPart即回到192行，将当前遍历位置向后走一位j+1，结构参考下图，3元素组合示意
      //            begin
      //          /   |   \
      //   0     a    b    c
      //        /\   /\    /\
      //   1   b  c a  c  a  b
      //      /    \ ..........
      //   2 c      b
      StrPart(str2,i,j+1,sb);
      //此处是关键，当遍历到底返回时，回到此处将添加进入的最后一个值剔除再返回上层
      sb.remove(sb.size() - 1);
    }
  }

  public void findBigSub(int[] num){

  }

  public int[] findNumTwo(int[] num, int target){
    Hashtable<Integer, Integer> hash = new Hashtable<>();
    for (int i = 0; i < num.length; i++) {
      int key = target - num[i];
      if(hash.containsKey(key)){
        return new int[]{num[i], hash.get(key)};
      } else {
        hash.put(key,num[i]);
      }
    }
    return null;
  }


  public void findStrChild(String str){
    Hashtable<Character, Integer> hash = new Hashtable<>();
    int begin = 0;
    for (int end = 0; end < str.length(); end++) {
      char c = str.charAt(end);
      if(hash.containsKey(c)){
        begin = Math.max(hash.get(c) + 1, begin);
        hash.put(c,end);
      } else {
        hash.put(c,end);
      }
      System.out.println(str.substring(begin, end + 1));
    }
  }

  public void findDuplicate(int[] n){
    List<Integer> sort = Arrays.stream(n).boxed().sorted().collect(Collectors.toList());
    Integer i = sort.get(0);
    for (int j = 1; j < sort.size(); j++) {
      Integer integer = sort.get(j);
      if(i.equals(integer)){
        System.out.println(i);
        return;
      } else {
        i = integer;
      }
    }
  }

  public void findDuplicateBetter(int[] n){
    Hashtable<Integer, Integer> hash = new Hashtable<>();
    for (int i = 0; i < n.length; i++) {
      if(hash.containsKey(n[i])){
        System.out.println(n[i]);
        return;
      } else {
        hash.put(n[i],i);
      }
    }
  }

  // 0 1 2 3 4 5 6 7 8 9 8
  // ^         ^         ^
  // 0 1 1 2 3
  // 0 1 2 1
  // ^   ^ ^
  public void findDuplicateOneBetter(int[] n){
    int left = 1;
    int right = n.length - 1;
    while(right > left){
      int mid = (left + right) >> 1;

    }

  }

  public int findPeakElement(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      if(i - 1 >= 0 && i + 1 <= nums.length - 1){
        if (nums[i] > nums[i+1] && nums[i] > nums[i -1]) {
          return nums[i];
        }
      }
    }
    return -1;
  }

  // 1 2 2 1 3 5 6 4
  //       ^ ^     ^
  public int findPeakElementBetter(int[] nums) {
    int h = 0;
    int b = 1;
    while (b < nums.length){
      int temp = nums[h];
      if(nums[b] > nums[h]){
        b++;
        h++;
      } else if (nums[b] < nums[h]) {
        return temp;
      } else {
        b += 2;
        h += 2;
      }
    }
    return -1;
  }

  public int findPeakElementOneBetter(int[] nums) {
    int h = 0;
    int b = nums.length - 1;
    while (b > h){
      int mid = (b + h) >> 1;
      if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]){
        return mid;
      }
      if(nums[mid] > nums[mid + 1]){
        b = mid - 1;
      } else if(nums[mid] < nums[mid + 1]){
        h = mid + 1;
      }
    }
    return -1;
  }

  // 1 2 3 4 5 6 7 8 9
  // ^               ^
  // 2 3 4 5 6 7 8 9 1
  // ^
  // 3 4 5 6 7 8 9 1 2
  // ^
  public int[] findRotate(int[] nums, int k){
    int h = 0;
    int d = nums.length - 1;
    while (k > 0){
      int temp = nums[h];
      System.arraycopy(nums,1,nums,0,nums.length -1);
      nums[d] = temp;
      k--;
    }
    return nums;
  }

  public int[] findRotateWorse(int[] nums, int k){
    int[] temp = new int[k];
    System.arraycopy(nums,0,temp,0,k);
    System.arraycopy(nums,k,nums,0,nums.length - k);
    System.arraycopy(temp,0,nums,nums.length - k,temp.length);
    temp = null;
    return nums;
  }

  public int[] findRotateOneBetter(int[] nums, int k){
    for (int i = 0; i < k; i++) {
      int change = nums.length - k;
      swap(i, change, nums);
    }
    return nums;
  }

  private void swap(int i, int change, int[] nums) {
    int temp = nums[i];
    nums[i] = nums[change];
    nums[change] = temp;
  }

  // 1 2 3 4 5 6 7 8 9
  // 5 6 7 8 9 6 7 8 9
  // i = 4  j = 0
  // i = 5  j = 1
  // i = 6  j = 2
  // i = 7  j = 3
  // i = 8  j = 4
  // i = 0  j = 5
  // i = 1  j = 6
  public int[] findRotateBetter(int[] nums, int k){
    int[] ans = new int[nums.length];
    int i = k;
    int count = 0;
    while(count < nums.length){
      if(i == nums.length - 1){
        ans[count] = nums[i];
        i = 0;
      } else {
        ans[count] = nums[i];
        i++;
      }
      count++;
    }
    return ans;
  }

  public String findRotateTwoBetter(int[] nums, int k){
    int[] n = new int[8];
    String str = Arrays.toString(nums);
    String trim = str.replaceAll(", ", "").trim();
    String before = trim.substring(1, trim.length() - k - 2);
    StringBuilder after = new StringBuilder(trim.trim().substring(trim.length()-k-2,trim.length()-1)).reverse();
    return after.append(before).toString();
  }

  // 1 2 3 4 5 6 7 8 9
  public int[] findRotateThreeBetter(int[] nums, int k){
    int i = 0;
    while(i < (k >> 1)) {
      swap(i, k-1-i, nums);
      i++;
    }
    int j = 0;
    while (j < ((nums.length - k) >> 1)){
      swap(k, nums.length-1-j, nums);
      j++;
      k++;
    }
    int h = 0;
    while (h < ((nums.length) >> 1)){
      swap(h, nums.length-1-h, nums);
      h++;
    }
    return nums;
  }

  // false
  // 1 / 3 0 1
  // 10 / 3 3 1
  //
  // 4 / 333 0 4
  // 40/ 333 0 40
  // 400/ 333 1 67
  // 670/ 333 2 4
  public String fractionToDecimal(int numerator, int denominator) {
    StringBuilder sb = new StringBuilder();
    Hashtable<Integer,Integer> hash = new Hashtable<>();
    int i = numerator / denominator;
    int j = numerator % denominator;

    if(i < 0){
      sb.append("-");
    }

    if(j == 0){
      sb.append(i);
    } else {
      sb.append(i).append(".");
    }

    int i1 = (j * 10) / denominator;
    int j1 = (j * 10) % denominator;
    while (!hash.containsKey(i1)){
      sb.append(i1);
      hash.put(i1,j1);
      i1 = (j1 * 10) / denominator;
      j1 = (j1 * 10) % denominator;
    }
    return sb.toString();
  }

  public int findDays(int year, int month, int day){
    int[] monthDayR = {31,29,31,30,31,30,31,31,30,31,30};
    int[] monthDayP = {31,28,31,30,31,30,31,31,30,31,30};
    int days = 0;
    if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
      for (int i = 0; i < month - 1; i++) {
        days += monthDayR[i];
      }
      days += day;
    } else {
      for (int i = 0; i < month - 1; i++) {
        days += monthDayP[i];
      }
      days += day;
    }
    return days;
  }

  public static void main(String[] args) {
    TestList06 ts = new TestList06();
    //ts.findChangeElectronic("1011","1110");
    //System.out.println(Integer.toBinaryString(1001));
    //ts.findTwoStr("abc","efhgicabiii");
    //ts.findTwoStr("","abc");
    //System.out.println("cba".substring(1, 3));

    //放在 str2循环找规定长度的子串，注释见方法头 上头
//      List<Character> list = new ArrayList<>();
//      StrPart(str2,i,0,list);
    //ts.findStrChild("abba");
    long l = System.nanoTime();
    //ts.findDuplicate(new int[]{0,1,3,4,2,5,6,7,8,9,4});
    //ts.findDuplicateBetter(new int[]{0,1,3,4,2,5,6,7,8,9,4});
    //System.out.println(ts.findPeakElementBetter(new int[]{1, 2, 2, 1, 3, 5, 6, 4})); //155,600
    //System.out.println(ts.findPeakElement(new int[]{1, 2, 2, 1, 3, 5, 6, 4})); //183,500
    //System.out.println(ts.findPeakElementOneBetter(new int[]{1, 2, 2, 1, 3, 5, 6, 4})); //171,500
    //System.out.println(Arrays.toString(ts.findRotate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4))); //136,100
    //System.out.println(Arrays.toString(ts.findRotateOneBetter(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4)));
    //System.out.println(Arrays.toString(ts.findRotateThreeBetter(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4)));
    //System.out.println(Arrays.toString(ts.findRotateWorse(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4))); //199,800

    //System.out.println(Arrays.toString(ts.findRotateBetter(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4))); //192,200
    //int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    //System.out.println(ts.findRotateTwoBetter(ints,4));
    //System.out.println(ts.fractionToDecimal(4,333));
    //System.out.println(ts.findDays(2020,12,31));
//    System.out.println(Arrays.toString(ts.findFibonacci(10)));
//    System.out.println(ts.findTwoPowerOf(4));
//    BigDecimal b = new BigDecimal(4);
//    BigDecimal a = new BigDecimal(333);
//    BigDecimal divide = b.divide(a,10,BigDecimal.ROUND_HALF_UP);
//    System.out.println(divide);
//    ts.sort(new int[]{2,3,45,6,7,90,567,231,21,5,6,0,13,78,64,222,101,1241,3456,1,8,10,44});
    System.out.println(Arrays.toString(ts.findFibonacci2(30)));
    long l1 = System.nanoTime();
    System.out.println(l1 -l);
  }

  //自底向上
  public int[] findFibonacci2(int count){
    int[] ans = new int[count];
    ans[0] = 1;
    ans[1] = 1;
    for (int i = 2; i < count; i++) {
      ans[i] = ans[i - 1] + ans[i - 2];
    }
    return ans;
  }

  public int[] findFibonacci(int count){
    int[] ans = new int[count];
    for (int i = 0; i < count; i++) {
        ans[i] = f1(i);
    }
    return ans;
  }

  private int f1(int f) {
    if(f == 0){
      return 1;
    }
    if(f == 1){
      return 1;
    }
    return f1(f - 1) + f1(f - 2);
  }

  //自顶向下
  public int[] findFibonacci1(int count){
    Hashtable<Integer,Integer> hash = new Hashtable<>();
    int[] ans = new int[count];
    for (int i = 0; i < count; i++) {
      ans[i] = f(i, hash);
    }
    return ans;
  }

  private int f(int f, Hashtable<Integer,Integer> hash) {
    if(hash.containsKey(f)){
      return hash.get(f);
    } else {
      if(f == 0){
        return 1;
      }
      if(f == 1){
        return 1;
      }
      int a = f(f - 1, hash) + f(f - 2, hash);
      hash.put(f,a);
      return a;
    }
  }

  public boolean findTwoPowerOf(int n){
    if(n <= 1){
      return false;
    }
    return n == (n & -n);
  }

  public boolean findFourPowerOf(int n){
    if(n <= 1){
      return false;
    }
    if((n & -n) != n){
      return false;
    }
    return (n & 0xaaaaaaaa) == 0;
  }


  public void sort(int[] nums){
    for (int num : nums) {
      new Thread(()->{
        try {
          Thread.sleep(num);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println(num);
      }).start();
    }
  }

  public void findTwoBigMin(int[] num, int[] nums){
    sortQuick(num);
  }

  // 3 4 5 2 7 0 9 1
  // 3
  // 2 0 1
  // 4 5 7 9
  // 2
  // 0 1
  //
  // 4
  //
  // 5 7 9
  // 0
  //
  // 1
  // 5
  //
  // 7 9
  //    int[] min = Arrays.stream(num).filter(x->x < mid).toArray();
  //    int[] max = Arrays.stream(num).filter(x->x > mid).toArray();
  //    while(min.length > 1 || max.length > 1){
  //

  private void sortQuick(int[] num) {
    Stack<Integer> s = new Stack<>();
    int begin = 0;
    int end = num.length - 1;
    int mid;
    if(begin < end){
      mid = PartSort(num, begin, end);
      if(mid - 1 > begin){
        s.push(begin);
        s.push(mid - 1);
      }
      if(mid + 1 < end){
        s.push(mid + 1);
        s.push(end);
      }
      while(!s.isEmpty()){
        int right = s.peek();
        s.pop();
        int left = s.peek();
        s.pop();
        mid = PartSort(num,left,right);
        if(mid - 1 > left){
          s.push(left);
          s.push(mid - 1);
        }
        if(mid + 1 < right){
          s.push(mid + 1);
          s.push(right);
        }
      }
    }

  }

  private int PartSort(int[] num, int begin, int end) {
    // 3 4 5 2 7 0 9 1
    int mid = num[begin];
    while(begin < end){
      while (begin < end && num[end] >= mid){
        num[begin] = num[end];
        end--;
      }
      while(begin < end && num[begin] <= mid){
        num[end] = num[begin];
        begin++;
      }
    }
    num[begin] = mid;
    return begin;
  }


}

