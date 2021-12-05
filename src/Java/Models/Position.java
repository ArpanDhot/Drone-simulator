package Java.Models;

import Java.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Position {

    /**
     * Other double properties can bind to this one, and once this property gets updated, the attached properties will get updated automatically.
     */
    private DoubleProperty pos_X;
    private DoubleProperty pos_Y;


    public Position(double xPosition, double yPosition) {
        /**
         * Initialising the double property using the "SimpleDoubleProperty()" method.
         */
        pos_X = new SimpleDoubleProperty(xPosition);
        pos_Y = new SimpleDoubleProperty(yPosition);
    }


    /**
     * Getting the value stored in pos_x using the "get()" method.
     */
    public double getPos_X() {
        return pos_X.get();
    }

    /**
     * This method returns the object the double property.
     */
    public DoubleProperty pos_XProperty() {
        return pos_X;
    }

    public double getPos_Y() {
        return pos_Y.get();
    }

    public DoubleProperty pos_YProperty() {
        return pos_Y;
    }

    /**
     * These two methods check the boundaries on the x and the y axis.
     */
    public boolean checkBorderX(){
        if( pos_X.get() < 0) return true;
        return pos_X.get() > Main.width;
    }
    public boolean checkBorderY(){
        if(pos_Y.get() < 0) return true;
        return pos_Y.get() > Main.height;
    }

    /**
     * This method updates the position based on the current position.
     */
    public void update_position(double x, double y){
        pos_X.set(pos_X.get() + x);
        pos_Y.set(pos_Y.get() + y);
    }

    /**
     * To set x and y.
     */
    public void setPosition(double x, double y){
        pos_X.set(x);
        pos_Y.set(y);
    }

    /**
     * Method gets the distance between two objects "d=√((x_2-x_1)²+(y_2-y_1)²)".
     * The passed values is the entire position class on object a and b.
     */
    public static double distance(Position a, Position b){
        return Math.sqrt( Math.pow(b.getPos_X()- a.getPos_X(), 2) + Math.pow(b.getPos_Y() - a.getPos_Y(), 2));
    }

    /**
     * Method returns the x, and y position of the object. Used to print values in debug.
     */
    public String toString(){
        return getPos_X()+","+getPos_Y();
    }
}
