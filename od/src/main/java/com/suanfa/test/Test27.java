package com.suanfa.test;

import java.util.Scanner;

//异国的客人
public class Test27 {
    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        if(n >= k){
            System.out.println(0);
            return;
        }
        int ans = transform(m, k);
    }*/

    static int count = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long m = in.nextLong();
        long n = in.nextLong();
        long k = in.nextLong();
        if(n >= k){
            System.out.println(0);
            return;
        }
        transform(m, k, n);
        System.out.println(count);
    }

    private static void transform(long m, long k, long n) {
        while (m > 0) {
            long j = m % k;
            if(j == n){
                count++;
            }
            m = m / k;
        }
    }
}
