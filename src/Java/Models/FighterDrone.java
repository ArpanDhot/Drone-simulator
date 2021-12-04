package Java.Models;

import Java.Sprites;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.json.JSONObject;

public class FighterDrone extends Position{

    private double dirX;
    private double dirY;

    private DoubleProperty angle;

    private double width, height;

    private Drone target;

    private double move_speed = 3.8f;

    private boolean dead;

    private Rectangle shape;

    public FighterDrone(double xPosition, double yPosition) {
        super(xPosition, yPosition);
        angle = new SimpleDoubleProperty(0);
    }

    public Rectangle makeShape(){
        shape = new Rectangle();
        shape.setWidth(width);
        shape.setHeight(height);
        shape.layoutXProperty().bind(pos_XProperty());
        shape.layoutYProperty().bind(pos_YProperty());
        shape.setFill(new ImagePattern(Sprites.fighter_drone));
        shape.rotateProperty().bind(angle);
        return shape;
    }

    public void update(){
        if(target == null) return;
        if(target.isDead()) {
            target = null;
            return;
        }
        double a = Math.atan2(target.getPos_Y(), target.getPos_X())*(360/Math.PI) - 180f;
        angle.set(a);
        dirX = 0;
        dirY = 0;
        double x = target.getPos_X()-getPos_X();
        double y = target.getPos_Y()-getPos_Y();
        if(x > 10) dirX = 1;
        if(x < -10) dirX = -1;
        if(y > 10) dirY = 1;
        if(y < -10) dirY = -1;

        update_position(move_speed*dirX, move_speed*dirY);
    }


    public void setAngle(double angle) {
        this.angle.set(angle);
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

    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("model_name", "Fighter Drone");
        object.put("x_pos", getPos_X());
        object.put("y_pos", getPos_Y());
        object.put("dir_x", dirX);
        object.put("dir_y", dirY);
        object.put("angle", angle.get());
        object.put("width", width);
        object.put("height", height);
        return object;
    }

    public void unpack_json(JSONObject json){
        setPosition(json.getDouble("x_pos"), json.getDouble("y_pos"));
        setWidth(json.getDouble("width"));
        setHeight(json.getDouble("height"));
        setAngle(json.getDouble("angle"));
    }

    public Drone getTarget() {
        return target;
    }

    public void setTarget(Drone target) {
        this.target = target;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Rectangle getShape() {
        return shape;
    }
}
