package Java.Models;

import Java.Main;
import org.json.JSONObject;

public class Plane extends Position{

    private final double move_speed = 2.5f;
    private int dir;

    private double width;

    public Plane(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        dir = 1;
    }

    /**
     * Updating the direction of the plan and the position of the plane.
     */
    public void update(){
        if(checkBorderX()) dir *= -1;
        update_position(move_speed*dir, 0);
    }

    public int getDir() {
        return dir;
    }

    /**
     * Not using the method from the position class because it doesn't have the with of the object.
     */
    public boolean checkBorderX(){
        if( getPos_X() < 0) return true;
        return getPos_X() > Main.width-width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     *  Creating a Json object of this model data.
     */
    public JSONObject getJson(){
        JSONObject object = new JSONObject();
        object.put("model_name", "Plane");
        object.put("pos_x", getPos_X());
        object.put("pos_y", getPos_Y());
        object.put("dir", getDir());
        object.put("width", width);
        return object;
    }

    /**
     *  Loading the Json object.
     */
    public void unpack_json(JSONObject json){
        setPosition(json.getDouble("pos_x"), json.getDouble("pos_y"));
        setWidth(json.getDouble("width"));
        dir = json.getInt("dir");
    }
}
