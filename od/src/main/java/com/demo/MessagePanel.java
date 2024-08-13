package com.demo;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
   //显示的信息
    private String message="Welcome to java";
    //显示信息x的坐标
    private int xCoordinate=20;
    //显示信息y的坐标
    private int yCoordinate=20;
    //信息是否被显示在中心部位
    private boolean centered;
    //水平和垂直的移动显示信息
    private int interval=10;

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
        repaint();
    }

    //无参构造
    public MessagePanel() {
    }
    //带参构造
    public MessagePanel(String message) {
        this.message = message;
        repaint();

    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
        repaint();

    }

    public boolean isCentered() {
        return centered;

    }

    public void setCentered(boolean centered) {
        this.centered = centered;
        repaint();

    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(centered){
            //设定字体
            FontMetrics fm=g.getFontMetrics();
            //设置显示字体
            int stringWidth=fm.stringWidth(message);
            int stringAscent=fm.getAscent();
            xCoordinate=getWidth()/2-stringWidth/2;
            yCoordinate=getHeight()/2+stringAscent/2;
        }
        g.drawString(message,xCoordinate,yCoordinate);
    }
    //让信息往左边移动
    public void moveLeft(){
        xCoordinate-=interval;
        repaint();
}
    //让信息往右边移动
    public void moveRight(){
        xCoordinate+=interval;
        repaint();
    }
    //让信息向上移动
    public void moveUp(){
        yCoordinate+=interval;
        repaint();
    }
    public void moveDown(){
        yCoordinate-=interval;
        repaint();
    }
    //固定写法，不必探究
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,30);
    }
}
