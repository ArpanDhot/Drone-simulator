package Java.Models;

import Java.Main;
import Java.Sprites;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.json.JSONObject;

public class Tank extends Position {

    private double dir;

    private double width, height;

    private final double move_speed = 1.5f;

    private Rectangle shape;

    public Tank(double xPosition, double yPosition) {
        super(xPosition, yPosition);
    }

    public Rectangle makeShape(){
        shape = new Rectangle();
        shape.setFill(new ImagePattern(Sprites.tank));
        shape.setWidth(width);
        shape.setHeight(height);
        shape.layoutYProperty().bind(pos_YProperty());
        shape.layoutXProperty().bind(pos_XProperty());
        return shape;
    }


    public boolean checkBorderX(){
        if( getPos_X() < 0) return true;
        return getPos_X() > Main.width-width*2.5;
    }

    public void update(){
        if(checkBorderX()) dir *= -1;
        update_position(move_speed*dir, 0);
    }

    public void setDir(double dir) {
        this.dir = dir;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle getShape() {
        return shape;
    }

    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("model_name", "Tank");
        object.put("x_pos", getPos_X());
        object.put("y_pos", getPos_Y());
        object.put("dir", dir);
        object.put("width", width);
        return object;
    }

    public void unpack_json(JSONObject json){
        setPosition(json.getDouble("x_pos"), json.getDouble("y_pos"));
        setWidth(json.getDouble("width"));
        dir = json.getInt("dir");
    }
}
