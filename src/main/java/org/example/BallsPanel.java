package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class BallsPanel extends JPanel {

    int numberOfBalls;
    public Ball[] balls;
    boolean isRandom;

    BallsPanel(int argNrBalls, boolean isRandom){

        this.setBounds(200, 0, 1000, 700);
        this.setBorder(
                BorderFactory.createEtchedBorder(BevelBorder.LOWERED));


        numberOfBalls = argNrBalls;
        this.isRandom = isRandom;


        balls = new Ball[numberOfBalls];
        for(int i = 0; i < numberOfBalls; i++){
            int ballSize = (isRandom) ? (int)(Math.random()*7) + 2 : 3;
            balls[i] = new Ball(ballSize,i*100+50, i*100+50, (int)(Math.random()*9) + 5, (int)(Math.random()*9) + 5,  0, 1000, 0 , 700);
        }


        this.setVisible(true);
        this.setLayout(null);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(balls != null) {
            for (Ball ball : balls) {
                if (ball != null) ball.drawSelf(g);
            }
        }

    }

    public void loop(){
        for(Ball ball : balls){
            ball.move();
        }

        for(int i = 0; i < numberOfBalls-1; i++ ){
            for(int j = i + 1; j < numberOfBalls; j++ ){
                if(balls[i].detectCollision(balls[j])){

                    balls[i].dynamicCollisionResponse(balls[j]);
                    System.out.println("Velocity1: ( " + balls[i].getVelocityX() + ", " + balls[i].getVelocityY() + " )" );
                    System.out.println("Velocity2: ( " + balls[j].getVelocityX() + ", " + balls[j].getVelocityY() + " )" );
                }
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
