package com.suanfa.gpthelp;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class search {


  public static void main(String[] args) {
    search sc = new search();
    int[] ints = sc.initArray();
    long long1 = System.currentTimeMillis();
    System.out.println(TwoSearch(new int[]{1, 8, 10, 89, 100, 134}, 10));
//    findMonkey(ints, 89);
//    sc.fibSearch(ints,134);
    long long2 = System.currentTimeMillis();
//    sc.ForSearch(ints,488);
//    sc.ToSearch(ints,638,ints.length-1,0);
    log.debug("寻找目标耗时:{}",(long2-long1)+"ms");
  }

  public int[] initArray(){
    int [] arr = {1,8,10,89,100,134};
//    int[] ints = new int[500];
//    for (int i = 0; i < 500; i++) {
//      ints[i] = i;
//    }
    return arr;
  }

  public static int TwoSearch(int[] ints, int target){
    int lef = 0;
    int rig = ints.length - 1;
    while (rig >= lef){
      int mid = (rig + lef) >> 1;
      if(ints[mid] < target){
        lef = mid;
      } else if(ints[mid] > target){
        rig = mid;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public void ToSearch(int[] ints, int target, int max, int min){
    int mid = (max + min) / 2;
    if(ints[mid] == target){
      log.info("目标已找到:{}", ints[mid]);
      return;
    }
    if(min >= max){
      log.info("目标不存在");
      return;
    }
    if(ints[mid] < target){
      min = mid + 1;
      ToSearch(ints,target,min,max);
    }
    if(ints[mid] > target){
      max = mid - 1;
      ToSearch(ints,target,min,max);
    }
  }

  public void InsertSearch(int[] ints, int target, int max, int min){
    int mid = (max + min) / (ints[max] - ints[min]) * (max - min);
    if(ints[mid] == target){
      log.info("目标已找到:{}", ints[mid]);
      return;
    }
    if(min >= max){
      log.info("目标不存在");
      return;
    }
    if(ints[mid] < target){
      min = mid + 1;
      ToSearch(ints,target,min,max);
    }
    if(ints[mid] > target){
      max = mid - 1;
      ToSearch(ints,target,min,max);
    }
  }
  public void ForSearch(int[] ints, int target){
    for (int i = 0; i < ints.length; i++) {
      if(ints[i] == target){
        log.info("目标已找到:{}", ints[i]);
        return;
      }
      if(i == ints.length - 1 && ints[i] != target){
        log.info("目标不存在");
        return;
      }
    }
  }

  public static void findMonkey(int[] n, int target){
    while (true){
      int i = (int) (Math.random() * n.length);
      if(n[i] == target){
        log.info("目标已找到:{}", target);
        break;
      }
    }
  }
  /**
   * 构造长度为length的斐波那契数组
   * @param length
   * @return
   */
  public static int[] fib(int length) {
    int[] f = new int[length];
    f[0] = 1;
    f[1] = 1;
    for (int i = 2; i < length; i++) {
      f[i] = f[i-1] + f[i-2];
    }
    return f;
  }

  public void fibSearch(int[] ints, int target) {
    int min = 0;
    int max = ints.length - 1;
    int mid = 0;
    int k = 0;
    int f[] = fib(20);
    //取接近数组长度值
    while(max > f[k] - 1) {
      k++;
    }
    //拷贝数组
    int[] temp= Arrays.copyOf(ints, f[k]);
    //换0
    for(int i = max + 1; i < temp.length; i++) {
      temp[i] = ints[max];
    }
    while (min <= max) {
      mid = min + f[k - 1] - 1;
      if(target < temp[mid]) {
        max = mid - 1;
        k--;
      } else if ( target > temp[mid]) {
        min = mid + 1;
        k -= 2;
      } else {
        if(mid <= max){
          log.info("目标已找到:{}", ints[mid]);
          return;
        }else{
          log.info("目标已找到:{}", ints[max]);
          return;
        }
      }
    }
    log.info("目标不存在");
  }

  public int quickSearch(int[] array, int left, int right, int i){
    int p = partition(array, left, right);
    if(array[p] == i){
      return p;
    }
    if(p > i){
      return quickSearch(array, p + 1, right, i);
    } else {
      return quickSearch(array, left, p - 1 , i);
    }
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

  private void swaps(int[] n, int i, int j){
    int temp = n[i];
    n[i] = n[j];
    n[j] = temp;
  }



}
