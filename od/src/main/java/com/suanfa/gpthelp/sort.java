package com.suanfa.gpthelp;

import org.apache.commons.lang3.ArrayUtils;

import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class sort {
  public void swaps(int a, int b){
    //这种交换有越界风险 可以换long或者BigInteger 或者使用位运算
    a = a + b;
    b = a - b;
    a = a - b;

    a = a ^ b;
    b = b ^ a;
    a = a ^ b;
  }

  public static void main(String[] args) {
    sort s = new sort();
//    //快排
//    System.out.println(Arrays.toString(s.quickSort(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));
//    //选择排序
//    System.out.println(Arrays.toString(s.selectSort(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));
//    //冒泡排序
//    System.out.println(Arrays.toString(s.bubbleSort(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));
//    //插入排序
//    System.out.println(Arrays.toString(s.insertSort(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));
//    //希尔排序（优化后的插入排序）
//    System.out.println(Arrays.toString(s.heirSort(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));
//    //归并排序
    int[] num = {3, 1, 5, 7, 9, 0, 4, 1, 2};
//    System.out.println(Arrays.toString(s.mergeSort(num, 0, num.length - 1)));
    //计数排序
//    System.out.println(Arrays.toString(s.countSort(num)));
    //堆排序
    //构建小顶堆
    System.out.println(Arrays.toString(initMinHeap(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));
    //构建大顶堆
    System.out.println(Arrays.toString(buildMaxHeap(new int[]{3, 1, 5, 7, 9, 0, 4, 1, 2})));

  }

  public int[] quickSort(int[] num){
    if (!ArrayUtils.isEmpty(num)){
      int p = num[0];
      int[] rest = ArrayUtils.remove(num, 0);
      int[] small = quickSort(Arrays.stream(rest).filter(e -> e <= p).toArray());
      int[] big = quickSort(Arrays.stream(rest).filter(e -> e > p).toArray());
      return IntStream.concat(IntStream.concat(Arrays.stream(small), IntStream.of(p)),Arrays.stream(big)).toArray();
    }
    return num;
  }

  public int[] selectSort(int[] num){
    for (int i = 0; i < num.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < num.length; j++) {
        if(num[min] > num[j]){
          min = j;
        }
      }
      int temp = num[min];
      num[min] = num[i];
      num[i] = temp;
    }
    return num;
  }

  public int[] bubbleSort(int[] num){
    boolean flag;
    for (int i = 0; i < num.length; i++) {
      flag = false;
      for (int j = 0; j < num.length - 1 - i; j++) {
        if(num[j] > num[j + 1]){
          int temp = num[j];
          num[j] = num[j + 1];
          num[j + 1] = temp;
          flag = true;
        }
      }
      if(!flag){
        break;
      }
    }
    return num;
  }

  public int[] bubbleSortVersion(int[] num){
    boolean flag = true;
    int lastUnSortIndex = num.length - 1;
    int indexSort = -1;
    while (flag){
      flag = false;
      for (int i = 0; i < lastUnSortIndex; i++) {
        if(num[i] > num[i + 1]){
          int temp = num[i];
          num[i] = num[i + 1];
          num[i + 1] = temp;
          flag = true;
          indexSort = i;
        }
      }
      lastUnSortIndex = indexSort;
    }
    return num;
  }

  public int[] insertSort(int[] num){
    for (int i = 1; i < num.length; i++) {
      int j = i;
      while (j >= i && num[j] < num[j - 1]){
        int temp = num[j];
        num[j] = num[j - 1];
        num[j - 1] = temp;
        j--;
      }
    }
    return num;
  }

  public int[] insertSortVersion1(int[] num){
    for (int i = 1; i < num.length; i++) {
      int t = num[i];
      for (int j = i; j > 0; j--) {
        if(num[j - 1] >= t){
          int temp = num[j - 1];
          num[j - 1] = num[j];
          num[j] = temp;
        }
      }
    }
    return num;
  }

  public int[] insertSortVersion2(int[] num){
    for (int i = 0; i < num.length; i++) {
      int cur = num[i];
      int j = i - 1;
      while (j >= 0 && cur < num[j]) {
        num[j + 1] = num[j];
        j--;
      }
      num[j + 1] = cur;
    }
    return num;
  }

  //通过希尔排序改良的插入排序
  public int[] insertSortVersion3(int[] num){
    for (int gap = num.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < num.length; i++) {
        int cur = num[i];
        int pre = i - gap;
        while (pre >= 0 && cur < num[pre]){
          num[pre + gap] = num[pre];
          pre -= gap;
        }
        num[pre + gap] = cur;
      }
    }
    return num;
  }


  public int[] heirSort(int[] num){
    //间隔
    for (int gap = num.length / 2; gap > 0 ; gap /= 2) {
      //分组
      for (int groupStartIndex = 0; groupStartIndex < gap; groupStartIndex++) {
        //插入排序
        for (int currentIndex = groupStartIndex + gap; currentIndex < num.length; currentIndex += gap) {
          int currentNumber = num[currentIndex];
          int preIndex = currentIndex - gap;
          while (preIndex >= 0 && currentNumber < num[preIndex]){
            num[preIndex + gap] = num[preIndex];
            preIndex -= gap;
          }
          num[preIndex + gap] = currentNumber;
        }
      }
    }
    return num;
  }

  public int[] mergeSort(int[] num, int start, int end){
    if(start == end){
      return new int[]{num[start]};
    }
    int mid = (start + end) / 2;
    int[] num1 = mergeSort(num, start, mid);
    int[] num2 = mergeSort(num, mid + 1, end);
    return merge(num1, num2);
  }

  private int[] merge(int[] num1, int[] num2){
    int[] result = new int[num1.length + num2.length];
    int i = 0;
    int j = 0;
    while (i < num1.length && j < num2.length){
      if(num1[i] <= num2[j]){
        result[i + j] = num1[i];
        i++;
      } else {
        result[i + j] = num2[j];
        j++;
      }
    }
    while (i < num1.length){
      result[i + j] = num1[i];
      i++;
    }
    while (j < num2.length){
      result[i + j] = num2[j];
      j++;
    }
    return result;
  }

  public int[] countSort(int[] num){
    int max = Integer.MIN_VALUE;
    for (int i : num) {
      if(max < i){
        max = i;
      }
    }
    int[] count = new int[max + 1];
    for (int i : num) {
      count[i]++;
    }
    int[] res = new int[num.length];
    int c = 0;
    for (int i = 0; i < count.length; i++) {
      if(c == res.length){
        break;
      }
      for (int i1 = count[i]; i1 > 0; i1--) {
        res[c] = i;
        c++;
      }
    }
    return res;
  }

  /**
   * leetcode 官方范例
   * @param arr
   */
  public static void heapSort(int[] arr) {
    buildMaxHeap(arr);
    for (int i = arr.length - 1; i > 0; i--) {
      swap(arr, 0, i);
      maxHeapify(arr, 0, i);
    }
  }
  private static int[] buildMaxHeap(int[] arr) {
    // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 arr.length / 2-1
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
      maxHeapify(arr, i, arr.length);
    }
    return arr;
  }

  private static void maxHeapify(int[] arr, int i, int heapSize) {
    int l = 2 * i + 1;
    int r = l + 1;
    int largest = i;
    if (l < heapSize && arr[l] > arr[largest]) {
      largest = l;
    }
    if (r < heapSize && arr[r] > arr[largest]) {
      largest = r;
    }
    if (largest != i) {
      swap(arr, i, largest);
      maxHeapify(arr, largest, heapSize);
    }
  }
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static int[] initMinHeap(int[] array){
    for (int i = array.length / 2 - 1; i >= 0; i--) {
      minHeapify(array, i);
    }
    return array;
  }

  public static void minHeapInsert(int[] array, int i){
    while (array[i] > array[(i - 1) / 2]){
      swap(array, i, (i - 1) / 2);
      i = (i - 1) / 2;
    }
  }
  private static void minHeapify(int[] array, int i) {
    int heapSize = array.length;
    int left = i * 2 +1;
    while (left < heapSize){
      int small = left + 1 < heapSize && array[left] > array[left + 1] ? left + 1 : left;
      if(array[i] <= array[small]){
        break;
      }
      swap(array, i, small);
      i = small;
      left = i * 2 + 1;
    }
  }

  //基数排序 通过每一位进行排序 对于负数 找最长的数 通过-9 - 9进行统计
  public int[] radixSort(int[] num){
    if(num == null){
      return null;
    }
    int max = 0;
    for (int val : num) {
      if(val > max){
        max = val;
      }
    }
    int maxDigitLength = 0;
    while (max != 0){
      maxDigitLength++;
      max = max / 10;
    }

    int[] count = new int[10];
    int[] res = new int[num.length];
    int dev = 1;

    for (int i = 0; i < maxDigitLength; i++) {
      for (int k : num) {
        int radix = k / dev % 10;
        count[radix]++;
      }
      for (int j = 1; j < count.length; j++) {
        count[i] += count[j - 1];
      }

      for (int j = num.length - 1; j >= 0; j--) {
        int radix = num[j] / dev % 10;
        res[--count[radix]] = num[j];
      }
      System.arraycopy(res, 0, num, 0, num.length);
      Arrays.fill(count, 0);
      dev *= 10;
    }

    return num;
  }


}
