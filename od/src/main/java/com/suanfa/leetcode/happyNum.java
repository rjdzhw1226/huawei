package com.suanfa.leetcode;

import java.util.Hashtable;

public class happyNum {
    public static void main(String[] args) {
        System.out.println(isHappyQ(2));
    }
    //快慢指针找链表环的操作
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = findSum(n);
        while (slow != fast){
            slow = findSum(slow);
            fast = findSum(findSum(fast));
        }
        return slow == 1;
    }

    public static boolean isHappyQ(int n){
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        if(n == 1){
            return true;
        }
        while (n != 1 && hashtable.get(n) == null){
            hashtable.put(n, 1);
            int sum = 0;
            while (n != 0){
                int k = n % 10;
                sum += (k * k);
                n = n / 10;
            }
            if(sum == 1){
                return true;
            }
            n = sum;
        }
        return false;
    }

    public static int findSum(int n){
        int sum = 0;
        while (n != 0){
            int k = n % 10;
            sum += (k * k);
            n = n / 10;
        }
        return sum;
    }
}
