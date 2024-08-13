package com.suanfa.test;

import java.util.Hashtable;
import java.util.Scanner;

//连续字母长度
public class Test44 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Hashtable<Character, Integer> hash = new Hashtable<>();
        int k = Integer.parseInt(in.nextLine());
        char[] charArray = line.toCharArray();
        int s = 0;
        while (s < charArray.length){
            char c = charArray[s];
            int len = 1;
            int next = s + 1;
            while (next < charArray.length){
                if (c == charArray[next]) {
                    len++;
                    next++;
                } else {
                    hash.put(charArray[s], len);
                    break;
                }
            }
            if(next >= charArray.length){
                hash.put(charArray[s], len);
                break;
            } else {
                s = next;
            }
        }
        if(k > hash.size() || k <= 0){
            System.out.println(-1);
            return;
        }
        System.out.println(hash.entrySet().stream().max((o1, o2) -> o2.getValue() - o1.getValue()).get().getKey());

    }
}
