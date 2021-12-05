package Java.DroneArena;

import Java.Models.Drone;
import Java.Sprites;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class DroneUI {

    private Drone drone;
    private Rectangle base, top_left, top_right, down_left, down_right;

    private final double sensors_width = 10, sensors_height = 30;
    private Color sensor_fill;
    private ObjectProperty<Paint> sensor_stroke;

    public DroneUI(double X, double Y){
        drone = new Drone(X, Y);
        drone.setWidth(50);
        drone.setHeight(25);
        sensor_fill = Color.TRANSPARENT;
        sensor_stroke = new SimpleObjectProperty<>(Color.BLUE);
        setup_drone();
    }

    public void setup_drone(){
        base = getShape(new ImagePattern(Sprites.drone), null, 50, 25, drone.pos_XProperty(), drone.pos_YProperty(), 0);
        top_left = getShape(sensor_fill, sensor_stroke, sensors_width, sensors_height, drone.topLeftXProperty(), drone.topLeftYProperty(), -45);
        top_right = getShape(sensor_fill, sensor_stroke, sensors_width, sensors_height, drone.topRightXProperty(), drone.topRightYProperty(), 45);
        down_left = getShape(sensor_fill, sensor_stroke, sensors_width, sensors_height, drone.downLeftXProperty(), drone.downLeftYProperty(),-145);
        down_right = getShape(sensor_fill, sensor_stroke, sensors_width, sensors_height, drone.downRightXProperty(), drone.downRightYProperty(), 145);
    }

    public void setFillSensors(Paint stroke){
        sensor_stroke.set(stroke);
    }

    //
    Rectangle getShape(Paint body, ObjectProperty<Paint> stroke, double width, double height, DoubleProperty x, DoubleProperty y, double angle){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(body);
        if(stroke != null)
            rectangle.strokeProperty().bind(stroke);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.layoutXProperty().bind(x);
        rectangle.layoutYProperty().bind(y);
        rectangle.setRotate(angle);
        return rectangle;
    }

    public Drone getDrone() {
        return drone;
    }

    public Rectangle getBase() {
        return base;
    }

    public Rectangle getTop_left() {
        return top_left;
    }

    public Rectangle getTop_right() {
        return top_right;
    }

    public Rectangle getDown_left() {
        return down_left;
    }

    public Rectangle getDown_right() {
        return down_right;
    }
}
