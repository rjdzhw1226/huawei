package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;

//CPU算力分配
public class Test21 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int countA = in.nextInt();
        int countB = in.nextInt();
        int[] A = new int[countA];
        int[] B = new int[countB];
        for (int i = 0; i < countA; i++) {
             A[i] = Integer.valueOf(in.next());
        }
        for (int i = 0; i < countB; i++) {
            B[i] = Integer.valueOf(in.next());
        }

        int[] ASort = Arrays.stream(A).sorted().toArray();
//        System.out.println(Arrays.toString(ASort));
        for (int i = 0; i < ASort.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int Ab = ASort[i];
                int Bb = B[j];
                swap(ASort, B, i, j);
                if(sum(ASort) == sum(B)){
                    System.out.println(Ab +  " " + Bb);
                    return;
                }
            }
        }


    }

    private static int sum(int[] aSort) {
        return Arrays.stream(aSort).sum();
    }

    private static void swap(int[] aSort, int[] b, int i, int j) {
        int temp = 0;
        temp = b[j];
        b[j] = aSort[i];
        aSort[i] = temp;
    }
}
