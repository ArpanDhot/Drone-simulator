package Java.DroneArena;

import Java.Models.Drone;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DroneUI extends Group {

    Rectangle rectangle;
    Circle circle;
    Drone drone;
    double targetX;
    double targetY;

//    class Target {
//        double x;
//        double y;
//
//        public Target(double x, double y){
//            this.x=x;
//            this.y=y;
//        }
//
//        boolean isEqual(double x, double y){
//            return this.x == x && this.y == y;
//        }
//    }

    public DroneUI(double x, double y) {

        rectangle = new Rectangle();
        rectangle.setWidth(20);
        rectangle.setHeight(20);
        rectangle.setFill(Color.RED);
        this.getChildren().add(rectangle);

        circle = new Circle();
        circle.setRadius(40);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.ORANGE);
        this.getChildren().add(circle);

        drone = new Drone(y, x);


    }


    private void setTargetPoint(double x, double y) {
        this.targetX = x;
        this.targetY = y;


    }


}
