package Java.DroneArena;

import Java.Sprites;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Explosion extends Circle {

    private final int max_frames = 48;
    private int frames = 0;

    public Explosion(double x, double y){
        setRadius(30);
        setLayoutX(x);
        setLayoutY(y);
    }

    public int countDown(){
        if(frames == max_frames) return -1;
        setFill(new ImagePattern(Sprites.explosions[frames++]));
        return frames;
    }
}
