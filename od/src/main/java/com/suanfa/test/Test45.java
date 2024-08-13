package com.suanfa.test;

import java.util.Scanner;

//拼接URL
public class Test45 {

    public static void getResult() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        String[] split = line.split(",");
        if(split[0].length() == 0 && split[1].length() == 0){
            System.out.println("/");
            return;
        }
        String sb = "";
        String sa = "";
        if(split[0].endsWith("/")){
            sb = split[0].substring(0, split[0].length() - 1);
        } else {
            sb = split[0];
        }

        if(!split[1].startsWith("/")){
            sa = "/" + split[1];
        } else {
            sa = split[1];
        }
        System.out.println(sb + sa);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String[] arr = s.split(",");

        String prefix = arr.length > 0 && arr[0].length() > 0 ? arr[0] : "/";
        String suffix = arr.length > 1 && arr[1].length() > 0 ? arr[1] : "/";

        System.out.println(getResult(prefix, suffix));
    }

    public static String getResult(String prefix, String suffix) {
        prefix = prefix.replaceAll("/+$", "");
        suffix = suffix.replaceAll("^/+", "");
        return prefix + "/" + suffix;
    }
}

