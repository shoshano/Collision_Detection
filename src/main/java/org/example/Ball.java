package org.example;

import java.awt.*;

public class Ball {

    int x;
    int y;
    int diameter;
    int velocityX;
    int velocityY;
    int leftEdge, rightEdge, bottomEdge, upperEdge;



    Ball(int argX, int argY, int argVX, int argVY, int argLE, int argRE, int argUE, int argBE){
        this.x = argX;
        this.y = argY;
        this.diameter = 50;
        this.velocityX = argVX;
        this.velocityY = argVY;
        this.leftEdge = argLE;
        this.rightEdge = argRE;
        this.bottomEdge = argBE;
        this.upperEdge = argUE;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.magenta);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move(){

        int newX;
        int newY;

        newX = x + velocityX;
        newY = y + velocityY;

        if(newX < leftEdge || newX + diameter > rightEdge){
            velocityX = -1 * velocityX;
            newX = x + velocityX;

        }
        if(newY< upperEdge || newY + diameter > bottomEdge){
            velocityY = -1 * velocityY;
            newY = y + velocityY;
        }


        x = newX;
        y = newY;
    }

    public int getCenterX() {
        return x + (diameter/2);
    }

    public int getCenterY() {
        return y + (diameter/2);
    }

    public void setCenterX(int x){ }
    public void setCenterY(int y){ }

    public int getDiameter() {
        return diameter;
    }

    public boolean detectCollision(Ball ball2){
        double distance = Math.pow(getCenterX() - ball2.getCenterX(), 2) + Math.pow(getCenterY() - ball2.getCenterY(), 2);
        if (distance < Math.pow((diameter/2) + (ball2.getDiameter()/2), 2)) return true;
        return false;
    }

    public void collisionResponse(Ball ball2){
        // mid poit p = 1/2(center1 + center2)

        double midpointX = 0.5*(getCenterX() + ball2.getCenterX());
        double midpointY = 0.5*(getCenterY() + ball2.getCenterY());
        //new center newCenter = p - diameter*(center1-center2)/distance

        setCenterX(2);
        setCenterY(3);

    }
}
