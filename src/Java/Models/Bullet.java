package Java.Models;

import Java.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.json.JSONObject;

public class Bullet extends Position{

    private int dirX;
    private int dirY;

    private DoubleProperty angle;

    private final double move_speed = 2f;

    public Bullet(double xPosition, double yPosition) {
        super(xPosition, yPosition);

        angle = new SimpleDoubleProperty(0);

        //Dictates the direction that to bullet gets shoot from the plane.
        dirX = xPosition > Main.width/2 ? -1 : 1;
        dirY = 1;
    }


    public void update(){
        //Checking the boundaries.
        if(checkBorderX()) dirX *= -1;
        if(checkBorderY()) dirY *= -1;

        //Methods to rotate the bullet according to the direction.
        if(dirX == -1 && dirY == -1) angle.set(-45);
        if(dirX == 1 && dirY == 1) angle.set(140);
        if(dirX == 1 && dirY == -1) angle.set(45);
        if (dirX == -1 && dirY == 1) angle.set(-140);

        update_position(move_speed* dirX, move_speed* dirY);
    }

    /**
     * Used when loading the file.
     */
    public void setDir(int x, int y){
        dirX = x;
        dirY = y;
    }

    public DoubleProperty angleProperty() {
        return angle;
    }

    /**
     *Checking the Y axis.
     */
    public boolean checkBorderY(){
        return getPos_Y() > Main.height;
    }

    /**
     *
     */
    public JSONObject getJson(){

        JSONObject object = new JSONObject();

        object.put("model_name", "Bullet");
        object.put("x_pos", getPos_X());
        object.put("y_pos", getPos_Y());
        object.put("dir_x", dirX);
        object.put("dir_y",dirY);
        object.put("angle",angle.get());
        return object;
    }

    /**
     *
     */
    public void unpack_json(JSONObject json){
        setPosition(json.getDouble("x_pos"), json.getDouble("y_pos"));
        setDir(json.getInt("dir_x"),json.getInt("dir_y"));
        angle.set(json.getDouble("angle"));
    }
}
