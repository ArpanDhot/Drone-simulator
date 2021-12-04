package Java.Models;

import Java.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.json.JSONObject;

import java.util.Random;

public class Drone extends Position {

    private double move_speed = 4f;

    private DoubleProperty topRightX, topLeftX, downLeftX, downRightX;
    private DoubleProperty topRightY, topLeftY, downLeftY, downRightY;

    private int dirX, dirY;

    private double width, height;

    private boolean dead;


    public Drone(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        dirX = new Random().nextInt(50) < 10 ? 1 : -1;
        dirY = 1;

        topLeftX = new SimpleDoubleProperty();
        topRightX = new SimpleDoubleProperty();
        downLeftX = new SimpleDoubleProperty();
        downRightX = new SimpleDoubleProperty();

        topLeftY = new SimpleDoubleProperty();
        topRightY = new SimpleDoubleProperty();
        downLeftY = new SimpleDoubleProperty();
        downRightY = new SimpleDoubleProperty();

        topLeftX.set(getPos_X()-12);
        topLeftY.set(getPos_Y()-22);
        topRightX.set(getPos_X()+52);
        topRightY.set(getPos_Y()-25);
        downLeftX.set(getPos_X()-11);
        downLeftY.set(getPos_Y()+20);
        downRightX.set(getPos_X()+50);
        downRightY.set(getPos_Y()+18);
    }

    public DoubleProperty topRightYProperty() {
        return topRightY;
    }

    public DoubleProperty topLeftYProperty() {
        return topLeftY;
    }

    public DoubleProperty downLeftYProperty() {
        return downLeftY;
    }

    public DoubleProperty downRightYProperty() {
        return downRightY;
    }

    public DoubleProperty topRightXProperty() {
        return topRightX;
    }

    public DoubleProperty topLeftXProperty() {
        return topLeftX;
    }

    public DoubleProperty downLeftXProperty() {
        return downLeftX;
    }

    public DoubleProperty downRightXProperty() {
        return downRightX;
    }

    public void changeDir(int x, int y){
        dirX *= x;
        dirY *= y;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDir(int x, int y){
        dirX = x;
        dirY = y;
    }

    public void update() {

        checkBorders();

        update_position(move_speed * dirX, move_speed * dirY);

        // updating sensors
        topLeftX.set(getPos_X()-12);
        topLeftY.set(getPos_Y()-22);
        topRightX.set(getPos_X()+52);
        topRightY.set(getPos_Y()-25);
        downLeftX.set(getPos_X()-11);
        downLeftY.set(getPos_Y()+20);
        downRightX.set(getPos_X()+50);
        downRightY.set(getPos_Y()+18);
    }

    public void checkBorders(){
        if(getPos_Y() < 0) dirY = 1;
        if(getPos_Y() > Main.height-height) dirY = -1;
        if( getPos_X() < 0) dirX = 1;
        if(getPos_X() > Main.width-width) dirX = -1;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public JSONObject getJson(){
        JSONObject object = new JSONObject();
        object.put("model_name","Drone");
        object.put("x_pos", getPos_X());
        object.put("y_pos", getPos_Y());
        object.put("dir_x", dirX);
        object.put("dir_y", dirY);
        object.put("width", width);
        object.put("height", height);

        return object;
    }

    public void unpack_json(JSONObject json){
        setPosition(json.getDouble("x_pos"), json.getDouble("y_pos"));
        setDir(json.getInt("dir_x"),json.getInt("dir_y"));
        setWidth(json.getDouble("width"));
        setHeight(json.getDouble("height"));
    }
}
