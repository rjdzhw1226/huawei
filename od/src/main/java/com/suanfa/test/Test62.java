package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
//字符串变换最小字符串
public class Test62 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String str = in.nextLine();
    char[] chars = str.toCharArray();
    char[] charsOri = str.toCharArray();
    Arrays.sort(chars);

    for (int i = 0; i < charsOri.length; i++) {
      if (chars[i] != charsOri[i]) {
        int maxIndex = findMax(chars[i], charsOri);
        char temp = charsOri[maxIndex];
        charsOri[maxIndex] = charsOri[i];
        charsOri[i] = temp;
        break;
      }
    }

    System.out.println(new String(charsOri));
//    System.out.println(new String(chars));


  }

  private static int findMax(char aChar, char[] charsOri) {
    int max = 0;
    for (int i = 0; i < charsOri.length; i++) {
      if(charsOri[i] == aChar){
        if(i >= max){
          max = i;
        }
      }
    }
    return max;
  }
}
