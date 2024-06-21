package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JComponent{

    private int WIDTH;
    private int HEIGHT;
    int numberOfBalls;
    public Ball[] balls;



    MyFrame(int argNB){
        WIDTH = 1000;
        HEIGHT = 700;
        this.numberOfBalls = argNB;

        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Java Projekt");
        gui.setPreferredSize(new Dimension(WIDTH+5, HEIGHT+30));
        gui.setResizable(false);
        gui.getContentPane().add(this);


        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        balls = new Ball[numberOfBalls];
        for(int i = 0; i < numberOfBalls; i++){
            balls[i] = new Ball((int)(Math.random()*600) + 200, (int)(Math.random()*500) + 100, (int)(Math.random()*9) + 1, (int)(Math.random()*9) + 1, 0 , WIDTH, 0 , HEIGHT);
        }

    }

    public void paintComponent(Graphics g){
        if(balls != null){
            for(Ball ball : balls){
                if(ball != null) ball.drawSelf(g);
            }
        }

    }

    public void loop(){
        for(Ball ball : balls){
            ball.move();
        }

        for(int i = 0; i < numberOfBalls-1; i++ ){
            for(int j = i + 1; j < numberOfBalls; j++ ){
                if(balls[i].detectCollision(balls[j])) System.out.println("Colision!");
            }
        }

        repaint();
    }

    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try {
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }



}
