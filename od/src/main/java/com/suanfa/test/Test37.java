package com.suanfa.test;

import java.util.Scanner;
import java.util.Stack;
//火星文计算2 替換方法待研究
public class Test37 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] charArray = in.nextLine().toCharArray();

        String operateNum = "";
        Stack<String> stack = new Stack<>();
        int length = charArray.length;
        int i = 0;
        String sign = "";
        int numL;
        int numR;

        while (i < length){
            char ch = charArray[i];
            if(ch >= 48 && ch <= 57){
                operateNum += "" + ch;
                i++;
            } else {
                if(stack.isEmpty()){
                    stack.push(operateNum);
                    stack.push(ch + "");
                    operateNum = "";
                } else if(stack.peek().equals('#' + "")){
                    sign = stack.pop();
                    numL = Integer.parseInt(stack.pop());
                    numR = Integer.parseInt(operateNum);
                    stack.push(calc(numL, sign, numR));
                    stack.push(ch + "");
                    operateNum = "";
                } else {
                    stack.push(operateNum);
                    stack.push(ch + "");
                    operateNum = "";
                }
                i++;
            }
        }

        if("#".equals(stack.peek())){
            sign = stack.pop();
            numL = Integer.parseInt(stack.pop());
            numR = Integer.parseInt(operateNum);
            stack.push(calc(numL, sign, numR));
            operateNum = null;
        } else {
            stack.push(operateNum);
        }
        if(stack.size() == 1){
            System.out.println(stack.pop());
        } else {
            while (stack.size() > 1){
                numL = Integer.parseInt(stack.pop());
                sign = stack.pop();
                numR = Integer.parseInt(stack.pop());
                stack.push(calc(numR, sign, numL));
            }
            System.out.println(stack.pop());
        }


    }

    private static String calc(int numL, String sign, int numR) {
        if("#".equals(sign)){
            return String.valueOf(4 * numL + 3 * numR + 2);
        } else {
            return String.valueOf(2 * numL + numR  +3);
        }
    }
}
