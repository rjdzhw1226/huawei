package com.suanfa.test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestList07 {

  static class ArrayKey{
    int[] key = new int[26];

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ArrayKey arrayKey = (ArrayKey) o;

      return Arrays.equals(key, arrayKey.key);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(key);
    }

    public ArrayKey(String str){
      for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        key[c - 'a']++;
      }
    }
  }

  public List<List<String>> findAnagrams(String[] strs){
    Hashtable<ArrayKey, List<String>> hash = new Hashtable<>();
    for (String str : strs) {
      ArrayKey key = new ArrayKey(str);
      List<String> list = hash.computeIfAbsent(key, k -> new ArrayList<>());
      list.add(str);
    }
    return new ArrayList<>(hash.values());
  }



  public int singleNumber(int[] nums){
    int num = nums[0];
    for (int i = 0; i < nums.length; i++) {
      num = num ^ nums[i];
    }
    return num;
  }


  public String mostCommonWord(String paragraph, String[] banned) {
    String[] split = paragraph.split("[^A-Za-z]+");
    Hashtable<String, Integer> hash = new Hashtable<>();
    char[] chars = paragraph.toLowerCase().toCharArray();
    StringBuilder sb = new StringBuilder();
    for (char aChar : chars) {
      if(aChar >= 'a' && aChar <= 'z'){
        sb.append(aChar);
      } else {
        String key = sb.toString();
        hash.compute(key, (k,v) -> v == null ? 1 : v + 1);
        sb = new StringBuilder();
      }
    }
    if(sb.length() > 0){
      String key = sb.toString();
      hash.compute(key, (k,v) -> v == null ? 1 : v + 1);
    }
    Set<String> set = new HashSet<>();
    Collections.addAll(set,banned);
    for (String s : split) {
//      Integer value = hash.get(s);
//      if(value == null){
//        value = 0;
//      }
//      hash.put(s, value + 1);
      hash.compute(s, (k,v) -> v == null ? 1 : v + 1);
    }

    System.out.println(hash);
    return null;
  }

  public void bubbleR(int[] n, int j){
    if(j == 0){
      return;
    }
    int x = 0;
    for(int i = 0; i < j; i++){
      if(n[i] > n[i + 1]){
        int temp = n[i];
        n[i] = n[i + 1];
        n[i + 1] = temp;
        x = i;
      }
    }
    bubbleR(n, x);
  }


  public void bubble(int[] n) {
    int j = n.length - 1;
    do {
      int x = 0;
      for (int i = 0; i < j; i++) {
        if (n[i] > n[i + 1]) {
          int t = n[i];
          n[i] = n[i + 1];
          n[i + 1] = t;
          x = i;
        }
      }
      j = x;
    } while (j != 0);
  }

  public void select(int[] n) {
    for (int right = n.length - 1; right > 0; right--) {
      int max = right;
      for (int i = 0; i < right; i++) {
        if(n[i] > n[max]) {
          max = i;
        }
      }
      swaps(n,max,right);
    }
  }

  private void swaps(int[] n, int i, int j){
    int temp = n[i];
    n[i] = n[j];
    n[j] = temp;
  }

  public void insert(int[] n){
    for (int low = 1; low < n.length; low++) {
      int t = n[low];
      int i = low - 1;
      while (i >= 0 && t < n[i]){
        n[i + 1] = n[i];
        i--;
      }
      if(i + 1 != low){
        n[i + 1] = t;
      }
    }
  }

  public void insertMethod(int[] n, int low){
    if(low == n.length){
      return;
    }
    int t = n[low];
    int i = low - 1;
    while (i >= 0 && t < n[i]){
      n[i + 1] = n[i];
      i--;
    }
    if(i + 1 != low){
      n[i + 1] = t;
    }
    insertMethod(n, low + 1);
  }

  public void heir(int[] n){
    for (int gap = n.length >> 1; gap >= 1; gap = gap >> 1) {
      for (int low = gap; low < n.length; low++) {
        int t = n[low];
        int i = low - gap;
        while (i >= 0 && t < n[i]){
          n[i + gap] = n[i];
          i-=gap;
        }
        if(i + gap != low){
          n[i + gap] = t;
        }
      }
    }
  }

  public void mergeSortUnR(int[] n){
    int length = n.length;
    int[] n1 = new int[length];
    for (int width = 1; width < length; width *= 2) {
      for (int left = 0; left < length; left += 2 * width) {
        int right = Math.min(left + 2 * width, length - 1);
        int m = Math.min(left + width - 1, length - 1);
        System.out.printf("width %d [%d, %d]%n", width, left, right);
        merge(n,n1,left,m,m+1,right);
      }
      System.arraycopy(n1,0,n,0,length);
    }

  }

  public void mergeSort(int[] n){
    int[] n1 = new int[n.length];
    split(n, 0, n.length - 1, n1);
  }

  public void insertion(int[] n, int left, int right){
    for (int low = left + 1; low < right; low++) {
      int t = n[low];
      int i = low - 1;
      while (i >= left && t < n[i]){
        n[i + 1] = n[i];
        i--;
      }
      if(i + 1 != low){
        n[i + 1] = t;
      }
    }
  }

  private void split(int[] n, int left, int right, int[] n1){
    //大数据量时
    if(right - left <= 32){
      //插入排序
      insertion(n,left,right);
      return;
    }
//    if(left == right){
//      return;
//    }
    int m = (left + right) >>> 1;
    split(n, left, m, n1);
    split(n, m + 1, right, n1);

    merge(n,n1,left,m,m+1,right);
    System.arraycopy(n1,left,n,left,right - left + 1);
  }

  private void merge(int[] n, int[] n1, int i, int iEnd, int j, int jEnd){
    int k = i;
    while (i <= iEnd && j < jEnd) {
      if(n[i] < n1[j]){
        n1[k] = n[i];
        i++;
      } else {
        n1[k] = n[j];
        j++;
      }
      k++;
    }
    if(i > iEnd){
      System.arraycopy(n,j,n1,k,jEnd-j+1);
    }
    if(j > jEnd){
      System.arraycopy(n,j,n1,k,iEnd-i+1);
    }
  }

  public void quickSingle(int[] n, int left, int right){
    if(left >= right){
      return;
    }
    int m = partitionS(n, left, right);
    quickSingle(n, left, m - 1);
    quickSingle(n, m + 1, right);
  }

  //基于某个基准点分区 返回这个基准点 基于基准点交换
  private int partitionS(int[] n, int left, int right) {
    int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
    swaps(n, idx, left);
    //最右侧当基准点
    int pv = n[left];
    //i找比基准点大的 j找比基准点小的 找到交换
    int i = left;
    int j = left;
    while (j < right) {
      if(n[j] < pv){
        if(i != j){
          swaps(n, i, j);
        }
        i++;
      }
      j++;
    }
    //右侧值与i交换
    swaps(n, i, right);
    return i;
  }

  private int partitionD(int[] n, int left, int right){
    int pv = n[left];

    int i = left;
    int j = right;
    while(i < j){
      while(i < j && n[j] > pv){
        j--;
      }
      while (i < j && n[i] <= pv){
        i++;
      }
      swaps(n, i, j);
    }
    swaps(n, i, left);
    return i;
  }

  private int partition(int[] n, int left, int right){
    int pv = n[left];
    int i = left + 1;
    int j = right;
    while(i <= j){
      while(i <= j && n[i] < pv){
        i++;
      }
      while (i <= j && n[j] > pv){
        j++;
      }
      if(i <= j){
        swaps(n, i, j);
        i++;
        j--;
      }
    }
    swaps(n, j, left);
    return j;
  }
  // 5 3 0 1 1
  //
  // 0 1 2 3 4 5

  public void countSort(int[] n){
    int[] count = null;
    int m = max(n);
    int mi = min(n);
    int k = 0;
    if(mi < 0){
      k = Math.abs(mi);
      count = new int[m + k + 1];
      for (int i = 0; i < n.length; i++) {
        count[n[i] + k]++;
      }
    } else {
      count = new int[m + 1];
      for (int i = 0; i < n.length; i++) {
        count[n[i]]++;
      }
    }
    for (int i = 0; i < count.length; i++) {
      for (int j = 0; j < count[i]; j++) {
        System.out.println(i - k);
      }
    }
  }

  private int min(int[] n) {
    int temp = n[0];
    for (int i : n) {
      if(i < temp){
        temp = i;
      }
    }
    return temp;
  }

  private int max(int[] n) {
    int temp = n[0];
    for (int i : n) {
      if (i > temp){
        temp = i;
      }
    }
    return temp;
  }


  public void buketSort(int[] n){
    Object[] o = new Object[10];
    for (int i = 0; i < o.length; i++) {
      o[i] = new ArrayList<Integer>();
    }
    for (int i : n) {
      ArrayList<Integer> arrayList = (ArrayList<Integer>) o[i / 10];
      arrayList.add(i);
    }
    int k = 0;
    for (Object o1 : o) {
      ArrayList<Integer> list = (ArrayList<Integer>) o1;
      list.sort((o11, o2) -> o11 - o2);
      for (Integer integer : list) {
        n[k++] = integer;
      }
    }
    System.out.println(Arrays.toString(n));
  }
  static class ReLoad{
    int deep;
    List<String> data;
    List<ReLoad> loads = new ArrayList<>();

    public ReLoad() {
    }

    public ReLoad(int deep, List<String> data, ReLoad reLoad){
      this.deep = deep;
      this.data = data;
      if(reLoad != null) {
        this.loads.add(reLoad);
      }
    }

    @Override
    public String toString() {
      return "ReLoad{" +
        "deep=" + deep +
        ", data=" + data +
        ", loads=" + loads +
        '}';
    }
  }

  /**
   * [123,[456,[789,],333,[135,[479]]]]
   */

  public List<ReLoad> deep(String s){
    List<ReLoad> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    char[] chars = s.toCharArray();
    int dp = 0;
    for (int i = 0; i < chars.length; i++) {
      ReLoad re = new ReLoad();
      if(chars[i] == '['){
        dp++;
      } else if(chars[i] == ']'){
        dp--;
      } else {
        int temp = i;
        while (chars[temp] != ',' && chars[temp] != ']') {
          sb.append(chars[temp]);
          temp++;
        }
        i = temp;
        re.deep = dp;
        re.data = new ArrayList<>(Collections.singleton(sb.toString()));
        re.loads = null;
        list.add(re);
      }
      sb = new StringBuilder();
    }
    return list;
  }

  // "[123,[456,[789]]]"
  public List<ReLoad> deserialize(String s) {
    LinkedList<Character> stack = new LinkedList<>();
    char[] chars = s.toCharArray();
    int i = 0;
    char c;
    while (i < chars.length){
      if(chars[i] == ']'){
        while ((c = stack.pop()) != '[') {
          System.out.println(c);
        }
      } else {
        stack.push(chars[i]);
      }
      i++;
    }
    return null;
  }

  public static void main(String[] args) {
    TestList07 ts = new TestList07();
    //ts.mostCommonWord("I love you, and you love me.", new String[]{"love"});
//    ts.countSort(new int[]{-2, -1, 0, 1, 5, 7, 3, -3});
//    ts.buketSort(new int[]{18 ,20 ,28 ,25 ,31 ,66 ,67});
//    ts.deserialize("[123,[456,[789],333,[135,[479]]]]");
//    System.out.println(ts.deep("[123,[456,[789],333,[135,[479]]]]"));
    //ts.nthUglyNumber(10);
//    System.out.println(ts.hIndex(new int[]{1,2,100}));
    System.out.println(ts.exchangeCharge(new int[]{1, 2, 5}, 11));
//    ts.deserialize("[123,[456,[789,[333,[135,[479]]]]]]");
//    ts.deserialize("[123,[456,[789]]]");
  }
  public void nthUglyNumber1(int n){
    int[] factors = {2, 3, 5};
    Set<Long> set = new HashSet<>();
    PriorityQueue<Long> heap = new PriorityQueue<>();
    set.add(1L);
    heap.offer(1L);
    int ugly = 0;
    for (int i = 0; i < n; i++) {
      long cur = heap.poll();
      ugly = (int) cur;
      for (int factor : factors) {
        long next = cur * factor;
        if(set.add(next)){
          heap.offer(next);
        }
      }
    }
    System.out.println(ugly);
  }

  // a[n] =
  // 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15,
  // 2 3 5
  //给你一个整数 n ，请你找出并返回第 n 个 丑数 。
  //
  //丑数 就是质因子只包含 2、3 和 5 的正整数。
  public void nthUglyNumber(int n){
    Hashtable<Integer, Integer> hash = new Hashtable<>();
    hash.put(1,1);
    List<Integer> num = new ArrayList<>();
    num.add(1);
    Hashtable<Integer, Integer> h = fnH(n, hash, num);
    System.out.println(h);
  }
  private Hashtable<Integer, Integer> fnH(int n, Hashtable<Integer, Integer> hash, List<Integer> num){
    if(hash.size() >= n){
      return hash;
    }
    Integer first = fn(num).get(0);
    hash.put(first * 2, first * 2);
    hash.put(first * 3, first * 3);
    hash.put(first * 5, first * 5);
    num.remove(first);
    return fnH(n, hash, num);
  }

  private List<Integer> fn(List<Integer> num) {
    Integer temp = num.get(0);
    num.addAll(Arrays.asList(temp * 2, temp * 3, temp * 5));
    num.sort((o1, o2) -> o1 - o2);
    return num;
  }

  // 1 2 3 4 5 6 7 8
  //       ^ ^
  // 1 2 100
  // 2 3 4 5 6
  //给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。
  //
  //h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）至少 有 h 篇论文分别被引用了至少 h 次。
  public int hIndex(int[] citations) {
    int length = citations.length;
    int end = length - 1; // 7
    int begin = 0; // 3

    while (true){
      int mid = (end - begin) >> 1;
      if(mid == 0){
        return citations[begin];
      }
      if(citations[mid + begin] < length - mid - begin){
        begin = mid;
      } else if(citations[mid + begin] > length - mid - begin){
        end = mid + begin;
      } else {
        return citations[mid];
      }
    }
  }
  //给你一个整数数组 coins 12 大小 2^31 -1 ，表示不同面额的硬币；以及一个整数 amount 10^4 ，表示总金额。
  //amount = 目前大于的最大值
  //  1  2  3  4  5  6 硬币的值
  //1 1
  //2
  //3
  //4
  //5
  //6
  //用每个硬币的个数
  //计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
  //
  //你可以认为每种硬币的数量是无限的。

  public int exchangeCharge(int[] coins, int amount){
    heir(coins);
    int count = 0;
    while (true){
      if(amount == 0){
        return count;
      }
      if(amount < coins[0]){
        return -1;
      }
      if(amount > coins[coins.length - 1]){
        amount = amount - coins[coins.length - 1];
        count++;
      } else {
        for (int i = 0; i < coins.length; i++) {
          if(i != coins.length - 1){
            if (coins[i] < amount && coins[i + 1] > amount) {
              amount = amount - coins[i];
              count++;
              break;
            } else if(amount == coins[i]){
              amount = amount - coins[i];
              count++;
              break;
            } else if(amount == coins[i + 1]){
              amount = amount - coins[i + 1];
              count++;
              break;
            }
          }
        }
      }
    }
  }




}
