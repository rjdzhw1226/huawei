package com.suanfa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//字符串序列判定
public class Test46 {
    public static void getResultOne(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.nextLine();
        String L = in.nextLine();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            boolean flag = false;
            for (int j = 0; j < L.length(); j++) {
                char c1 = L.charAt(j);
                if(c1 == c){
                    list.add(j);
                    flag = true;
                }
            }
            if(!flag){
                System.out.println(-1);
                return;
            }
        }
        if (isMonotonic(list)) {
            System.out.println(list.get(list.size() - 1));
        } else {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String l = sc.nextLine();

        System.out.println(getResult(s, l));
    }

    public static int getResult(String s, String l) {
        int i = 0;
        int j = 0;

        int ans = -1;

        while (i < s.length() && j < l.length()) {
            if (s.charAt(i) == l.charAt(j)) {
                i++;
                ans = j;
            }
            j++;
        }

        return ans;
    }


    public static boolean isMonotonic(List<Integer> a) {
        boolean up = true;
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) > a.get(i + 1)) {
                up = false;
                break;
            }
        }
        return up;
    }

    public boolean isMonotonic(int[] a) {
        boolean up = true, down = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) up = false;
            if (a[i] < a[i + 1]) down = false;
            if (!up && !down) return false;
        }
        return up || down;
    }
}
