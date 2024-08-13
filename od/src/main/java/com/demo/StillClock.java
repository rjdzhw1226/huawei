package com.demo;

//import sun.util.calendar.Gregorian;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StillClock extends JPanel {
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        repaint();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        repaint();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        repaint();
    }

    private int hour;
    private int minute;
    private int second;

    public StillClock() {
        setCurrentTime();
    }

    public StillClock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //使用Graphics类绘制图形，需要重写paintComponent方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制时钟参数
        int clockRadius=(int)(Math.min(getWidth(),getHeight())*0.8*0.5);
        int xCenter=getWidth()/2;
        int yCenter=getHeight()/2;

        //绘制一个圆
        g.setColor(Color.BLACK);
        g.drawOval(xCenter-clockRadius,yCenter-clockRadius,2*clockRadius
        ,2*clockRadius);
        g.drawString("12",xCenter-5,yCenter-clockRadius+12);
        g.drawString("9",xCenter-clockRadius+3,yCenter+5);
        g.drawString("3",xCenter+clockRadius-10,yCenter
        +3);
        g.drawString("6",xCenter-3,yCenter+clockRadius-3);

        //绘制秒针
        int sLength=(int)(clockRadius*0.8);
        int xSecond=(int)(xCenter+sLength*Math.sin(second*(2*Math.PI/60)));
        int ySecond=(int)(xCenter-sLength*Math.cos(second*(2*Math.PI/60)));
        g.setColor(Color.red);
        g.drawLine(xCenter,yCenter,xSecond,ySecond);

        //绘制分针
        int mLength=(int)(clockRadius*0.65);
        int xMinute=(int)(xCenter+mLength*Math.sin(minute*(2*Math.PI/60)));
        int yMinute=(int)(xCenter-mLength*Math.cos(minute*(2*Math.PI/60)));
        g.setColor(Color.blue);
        g.drawLine(xCenter,yCenter,xMinute,yMinute);

        //绘制时针
        int hLength=(int)(clockRadius*0.5);
        int xHour=(int)(xCenter+hLength*Math.sin((hour%12+minute/60.0)*(2*Math.PI/12)));
        int yHour=(int)(xCenter-hLength*Math.cos((hour%12+minute/60.0)*(2*Math.PI/12)));
        g.setColor(Color.green);
        g.drawLine(xCenter,yCenter,xHour,yHour);


        }
    public void setCurrentTime(){
        //构造一个日历类设定当前日期和时间
        Calendar calendar=new GregorianCalendar();

        //设定时分秒
        this.hour=calendar.get(Calendar.HOUR_OF_DAY);
        this.minute=calendar.get(Calendar.MINUTE);
        this.second=calendar.get(Calendar.SECOND);
    }

    public Dimension getPreferredSize(){
        return new Dimension(200,200);
    }
}
