package com.suanfa.leetcode;

//比较版本号
public class compareNum {
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int i = 0;
        int j = 0;
        int n = split1.length;
        int m = split2.length;
        while (i < n || j < m){
            int a = 0;
            int b = 0;
            if(i < n) {
                a = Integer.parseInt(split1[i++]);
            }
            if(j < m){
                b = Integer.parseInt(split2[j++]);
            }
            if(a != b) {
               return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
