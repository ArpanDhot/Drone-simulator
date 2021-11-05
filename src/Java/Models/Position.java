package Java.Models;

import javafx.geometry.Bounds;

public abstract class Position {

    private double yPos;
    private double xPos;


    public Position(double yPos, double xPos){
        this.yPos = yPos;
        this.xPos = xPos;
    }


    //Getter and setter methods
    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    //Check the collission between two objects
    public static Boolean checkCollission(Bounds a,Bounds b){
        return a.intersects(b);
    }

    //Will be used to update the object position
    public void updatePosition(double xPos, double yPos){
        this.yPos += yPos;
        this.xPos += xPos;
    }
}
