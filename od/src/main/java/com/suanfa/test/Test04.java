package com.suanfa.test;

import java.util.ArrayList;

public class Test04 {
    public static void f(int s, int[] a, ArrayList<Integer> al) {
        if (s == a.length) System.out.println(al);
        else {
            for (int i = 0; i <= al.size(); i++) {
                ArrayList<Integer> al1 = new ArrayList(al);
                al1.add(i, a[s]);
                f(s + 1, a, al1);
            }
        }
    }

    public static void main(String[] args) {
        //定义一个数组
        int[] a = {1, 2, 3};
        ArrayList<Integer> al = new ArrayList();
        System.out.println(al.size());
        f(0,a,al);
    }
}
