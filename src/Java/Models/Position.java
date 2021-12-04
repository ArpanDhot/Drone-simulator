package Java.Models;

import Java.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Position {

    //
    private DoubleProperty pos_X;
    private DoubleProperty pos_Y;

    public Position(double xPosition, double yPosition) {
        pos_X = new SimpleDoubleProperty(xPosition);
        pos_Y = new SimpleDoubleProperty(yPosition);
    }

    public double getPos_X() {
        return pos_X.get();
    }

    public DoubleProperty pos_XProperty() {
        return pos_X;
    }

    public double getPos_Y() {
        return pos_Y.get();
    }

    public DoubleProperty pos_YProperty() {
        return pos_Y;
    }

    public boolean checkBorderX(){
        if( pos_X.get() < 0) return true;
        return pos_X.get() > Main.width;
    }

    public boolean checkBorderY(){
        if(pos_Y.get() < 0) return true;
        return pos_Y.get() > Main.height;
    }

    public void update_position(double x, double y){
        pos_X.set(pos_X.get() + x);
        pos_Y.set(pos_Y.get() + y);
    }

    public void setPosition(double x, double y){
        pos_X.set(x);
        pos_Y.set(y);
    }

    public static double distance(Position a, Position b){
        return Math.sqrt( Math.pow(b.getPos_X()- a.getPos_X(), 2) + Math.pow(b.getPos_Y() - a.getPos_Y(), 2));
    }


    public String toString(){
        return getPos_X()+","+getPos_Y();
    }
}
