package com.demo;

import com.suanfa.gpthelp.ExpressionSolver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayClock extends JFrame {
    public DisplayClock(){
        Thread thread = new Thread(){
            public void run(){
                while(true){
                    SimpleDateFormat s = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");

                    String s1 = s.format(new Date());

                    int[] array = Arrays.stream(s1.split("-")).mapToInt(Integer::parseInt).toArray();

                    MessagePanel messagePanel1=new MessagePanel(ExpressionSolver.binary(array[3]) +"时："+ExpressionSolver.binary(array[4])+"分："+ExpressionSolver.binary(array[5]) + "秒");

                    messagePanel1.setCentered(true);

                    messagePanel1.setForeground(Color.black);

                    messagePanel1.setFont(new Font("Courier",Font.BOLD,16));

                    add(messagePanel1,BorderLayout.CENTER);
                    validate();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        DisplayClock frame=new DisplayClock();
        frame.setTitle("DisplayClock");
        frame.setSize(300,350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
