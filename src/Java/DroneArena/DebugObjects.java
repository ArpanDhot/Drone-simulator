package Java.DroneArena;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class DebugObjects extends TextFlow {

    private Arena arena;
    private Text text;
    private Timeline timeline;

    public DebugObjects(){
        new Scene(this, 400, 400);
        this.setWidth(400);
        this.setHeight(400);

        timeline = new Timeline();

        KeyFrame frame = new KeyFrame(Duration.millis(50), e->{
            text.setText(debug_data());
        });

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();

        text = new Text();
        text.setFont(Font.font("Arial", 14));
        this.getChildren().add(text);
    }

    public void startDebug(){
        timeline.play();
    }

    public void pauseDebug(){
        timeline.pause();
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public String debug_data(){
        StringBuilder data = new StringBuilder("Debugging Models Data.\n");

        data.append("Plane (").append(arena.getPlane().getPlane().toString()).append(")\t\t\t");
        data.append("Tank (").append(arena.tank.toString()).append(")\n");
        int index = 0;
        for (DroneUI drone : arena.drones) {
            data.append("Drone ").append(++index).append("(").append(drone.getDrone().toString()).append(")");
            if(index%2==0)
                data.append("\n");
            else
                data.append("\t\t\t");
        }
        data.append("\n");
        index = 0;
        for (BulletUI bullet : arena.bullets) {
            data.append("Bullet ").append(++index).append("(").append(bullet.getBullet().toString()).append(")");
            if(index%2==0)
                data.append("\n");
            else
                data.append("\t\t\t");
        }
        data.append("\n");
        return data.toString();
    }
}
