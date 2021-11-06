package Java.DroneArena;

import Java.Main;
import Java.Models.Bullet;
import Java.Models.Plane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class Arena extends Pane {

    private Timeline timelinePlane;
    private Rectangle rectanglePLane;
    private Plane plane;
    private int movePlaneSpeed = 1;
    private int bulletSpawnDelay = 1000;
    private int delay = bulletSpawnDelay;

    private ArrayList<BulletUI> bullets = new ArrayList<>();

    private ArrayList<DroneUI> drones = new ArrayList<>();
    private Rectangle rectanglePlatform;

    public Arena() {
        this.setPrefSize(Main.width, Main.height);
        this.setStyle("-fx-background-color: darkgrey");
        setUpPlane();
        platform();

    }

    public void startSimulationControl(){
        timelinePlane.play();
    }

    private void setUpPlane() {
        plane = new Plane(50, Main.width / 2);
        //TODO make rectangle at the position of the plane
        //TODO make timeline and the keyframe. Update plane position and shoot the two type of bullets.

        rectanglePLane = new Rectangle();
        rectanglePLane.setLayoutX(plane.getxPos());
        rectanglePLane.setLayoutY(plane.getyPos());
        rectanglePLane.setWidth(80);
        rectanglePLane.setHeight(40);
        rectanglePLane.setFill(Color.LIGHTCYAN);

        this.getChildren().add(rectanglePLane);

        timelinePlane = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e -> {
            //Plane movement on x axis
            if (plane.getxPos() <= 2 || plane.getxPos() >= Main.width - rectanglePLane.getWidth()) {
                movePlaneSpeed *= -1;
            }

            if(delay <= 0){
                delay = bulletSpawnDelay;
                spawnBullet();
            }
            delay -= 10;

            plane.updatePosition(movePlaneSpeed, 0);
            rectanglePLane.setLayoutX(plane.getxPos());
            bullets.removeIf(BulletUI::isDead);
        });
        timelinePlane.getKeyFrames().add(keyFrame);
        timelinePlane.setCycleCount(Animation.INDEFINITE);
    }

    // making new Bullet
    private void spawnBullet(){
        Bullet bullet = new Bullet( plane.getyPos()+rectanglePLane.getHeight(), plane.getxPos()+rectanglePLane.getWidth()/2);
        bullet.setUpDirection(plane.getxPos() > Main.width/2 ? -1 : 1,1);
        BulletUI currentBullet = new BulletUI(bullet, false);
        // add the bullet into the pane layout or container
        this.getChildren().add(currentBullet.getRect());
        currentBullet.getTimeline().play();
        this.bullets.add(currentBullet);
    }



    private void platform(){
        rectanglePlatform = new Rectangle();
        rectanglePlatform.setHeight(40);
        rectanglePlatform.setWidth(25);
        Image image = Main.loadImage("Pipe.png");
        if(image != null){
            rectanglePlatform.setFill(new ImagePattern(image));
        }else{
            rectanglePlatform.setFill(Color.DARKGREEN);
        }
        rectanglePlatform.setLayoutX(Main.width/2);
        rectanglePlatform.setLayoutY(Main.height-rectanglePlatform.getHeight());
        this.getChildren().add(rectanglePlatform);
    }


    public void addDrone(){
        DroneUI droneUI = new DroneUI(rectanglePlatform.getLayoutX(), rectanglePlatform.getLayoutY());






    }






}
