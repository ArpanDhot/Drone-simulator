package Java.DroneArena;

import Java.Main;
import Java.Models.Bullet;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class BulletUI {

    // boolean isClever to know the type of bullet
    private boolean isClever; // true mean clever bullet else it's dumb
    private Rectangle rect;
    private boolean dead;

    private Timeline timeline;

    private Bullet bullet;


    public BulletUI(Bullet bullet, boolean clever){

        this.bullet = bullet;
        this.isClever = clever;

        rect = new Rectangle();
        setupBullet();
        setRandomDirection();
        setupTimeline();
    }

    public void setupBullet(){
        if(isClever){
            rect.setFill(Color.RED); // red for clever
        }else{
            rect.setFill(Color.YELLOW);
        }

        rect.setWidth(20);
        rect.setHeight(20);
    }

    void checkBoundaries(){
        if(bullet.getxPos() < 2) bullet.changeDirection(1);
        if(bullet.getxPos() > Main.width-rect.getWidth()) bullet.changeDirection(1);
        if(bullet.getyPos() < 0){
            // destory the bullet.
            dead = true;
        }
        if(bullet.getyPos() > Main.height-rect.getHeight()) bullet.changeDirection(-1);
    }

    void setupTimeline(){
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(50), e->{
            // check if the bullet is clever follow the drone
            if(isClever){
                //
            }else{
                // other wise just move to random direction;
                checkBoundaries();
                bullet.Move();
                rect.setLayoutX(bullet.getxPos());
                rect.setLayoutY(bullet.getyPos());
            }

        });

        timeline.getKeyFrames().add(frame);
    }

    public void setRandomDirection(){
        // new Random().nextInt(3); 0 , 1 ,2
        int x = new Random().nextInt(3) - 1; // it return -1 , 0, 1
        int y = new Random().nextInt(3) -1; // (0 , 1, 3) these are the possible.

        if(x == 0 && y == 0) setRandomDirection();

        bullet.setUpDirection(x, y);
    }

    public Rectangle getRect() {
        return rect;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
