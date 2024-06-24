package org.example;

import java.awt.*;

public class Ball {

    double x;
    double y;
    double diameter;
    double velocityX;
    double velocityY;
    int leftEdge, rightEdge, bottomEdge, upperEdge;
    private double mass;



    Ball(double d, double argX, double argY, double argVX, double argVY, int argLE, int argRE, int argUE, int argBE){
        this.x = argX;
        this.y = argY;
        this.diameter = d*10;
        this.velocityX = argVX;
        this.velocityY = argVY;
        this.leftEdge = argLE;
        this.rightEdge = argRE;
        this.bottomEdge = argBE;
        this.upperEdge = argUE;
        this.mass = 1;
    }

    public void drawSelf(Graphics g){
        g.setColor(Color.magenta);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
    }

    public void move(){

        double newX;
        double newY;

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

    public double getCenterX() {
        return x + (diameter/2);
    }

    public double getCenterY() {
        return y + (diameter/2);
    }

    public void setCenterX(double newCenterX){
        x = newCenterX - (diameter/2);
    }
    public void setCenterY(double newCenterY){
        y = newCenterY - (diameter/2);
    }

    public double getDiameter() {
        return diameter;
    }

    public double getVelocityX(){
        return velocityX;
    }

    public double getVelocityY(){
        return velocityY;
    }

    public void setVelocityX(double vX){
        this.velocityX = vX;
    }

    public void setVelocityY(double vY){
        this.velocityY = vY;
    }

    public double getMass() {
        return mass;
    }

    public boolean detectCollision(Ball ball2){
        double distance = Math.pow(getCenterX() - ball2.getCenterX(), 2) + Math.pow(getCenterY() - ball2.getCenterY(), 2);
        if (distance < Math.pow((diameter/2) + (ball2.getDiameter()/2), 2)) return true;
        return false;
    }

    public void staticCollisionResponse(Ball ball2){
        // mid poit p = 1/2(center1 + center2)

        double midpointX = (getCenterX() + ball2.getCenterX())/2;
        double midpointY = (getCenterY() + ball2.getCenterY())/2;
        //new center newCenter = p - diameter*(center1-center2)/distance

        setCenterX((midpointX + (diameter/2)* (getCenterX() - ball2.getCenterX()))/2);
        setCenterY((midpointY + (diameter/2)* (getCenterY() - ball2.getCenterY()))/2);

        ball2.setCenterX((midpointX + (ball2.getDiameter()/2)* (ball2.getCenterX() - getCenterX()))/2);
        ball2.setCenterY((midpointY + (ball2.getDiameter()/2)* (ball2.getCenterY() - getCenterY()))/2);



    }

    public void dynamicCollisionResponse(Ball ball2){
       double distance = Math.sqrt(Math.pow(getCenterX()-ball2.getCenterX(),2) + Math.pow(getCenterY()-ball2.getCenterY(),2));

        double newXVelocity1 = (getVelocityX() * (getMass() - ball2.getMass()) + (2 * ball2.getMass() * ball2.getVelocityX())) / (getMass() + ball2.getMass());

        double newXVelocity2 = (ball2.getVelocityX() * (ball2.getMass() - getMass()) + (2 * getMass() * getVelocityX())) / (getMass() + ball2.getMass());

        double newYVelocity1 = (getVelocityY() * (getMass() - ball2.getMass()) + (2 * ball2.getMass() * ball2.getVelocityY())) / (getMass() + ball2.getMass());

        double newYVelocity2 = (ball2.getVelocityY() * (ball2.getMass() - getMass()) + (2 * getMass() * getVelocityY())) / (getMass() + ball2.getMass());


        setVelocityX(newXVelocity1);
        setVelocityY(newYVelocity1);
        ball2.setVelocityX(newXVelocity2);
        ball2.setVelocityY(newYVelocity2);

    }
}
