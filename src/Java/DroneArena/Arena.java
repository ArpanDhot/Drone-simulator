package Java.DroneArena;

import Java.Main;
import Java.Models.Bullet;
import Java.Models.Plane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Arena extends Pane {

    private Timeline timelinePlane;
    private Rectangle rectangle;
    private Plane plane;
    private int movePlaneSpeed = 1;
    private int bulletSpawnDelay = 1000;
    private int delay = bulletSpawnDelay;

    private ArrayList<BulletUI> bullets = new ArrayList<>();

    public Arena() {
        this.setPrefSize(Main.width, Main.height);
        this.setStyle("-fx-background-color: darkgrey");
        setUpPlane();
    }

    public void startSimulationControl(){
        timelinePlane.play();
    }

    private void setUpPlane() {
        plane = new Plane(50, Main.width / 2);
        //TODO make rectangle at the position of the plane
        //TODO make timeline and the keyframe. Update plane position and shoot the two type of bullets.

        rectangle = new Rectangle();
        rectangle.setLayoutX(plane.getxPos());
        rectangle.setLayoutY(plane.getyPos());
        rectangle.setWidth(80);
        rectangle.setHeight(40);
        rectangle.setFill(Color.LIGHTCYAN);

        this.getChildren().add(rectangle);

        timelinePlane = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e -> {
            //Plane movement on x axis
            if (plane.getxPos() <= 2 || plane.getxPos() >= Main.width - rectangle.getWidth()) {
                movePlaneSpeed *= -1;
            }

            if(delay <= 0){
                delay = bulletSpawnDelay;
                spawnBullet();
            }
            delay -= 5;

            plane.updatePosition(movePlaneSpeed, 0);
            rectangle.setLayoutX(plane.getxPos());
            bullets.removeIf(BulletUI::isDead);
        });
        timelinePlane.getKeyFrames().add(keyFrame);
        timelinePlane.setCycleCount(Animation.INDEFINITE);
    }

    // making new Bullet
    void spawnBullet(){
        Bullet bullet = new Bullet( plane.getyPos(), plane.getxPos());
        BulletUI currentBullet = new BulletUI(bullet, false);
        // add the bullet into the pane layout or container
        this.getChildren().add(currentBullet.getRect());
        currentBullet.getTimeline().play();
        this.bullets.add(currentBullet);
    }
}
