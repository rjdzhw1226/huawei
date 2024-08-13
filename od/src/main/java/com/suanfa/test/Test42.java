package com.suanfa.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

//英文输入法
public class Test42 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String sign = in.nextLine();

        find(line, sign);
    }

    private static void find(String line, String sign) {
        String[] temp = line.split("[^a-zA-Z]");
        String st = "";
        for (String s : Arrays.stream(temp).filter(e -> e.length() >= 1).filter(e -> e.startsWith(sign)).sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList())) {
            st += s + " ";
        }
        if(st.length() > 0){
            System.out.println(st.substring(0, st.length() - 1));
        } else {
            System.out.println(sign);
        }

    }
}
