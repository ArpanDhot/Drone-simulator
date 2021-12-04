package Java.DroneArena;

import Java.Models.Bullet;
import Java.Sprites;
import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BulletUI extends Group {

    Bullet bullet;

    public BulletUI(double X, double Y){
        bullet = new Bullet(X, Y);

        this.layoutXProperty().bind(bullet.pos_XProperty());
        this.layoutYProperty().bind(bullet.pos_YProperty());

        Rectangle rect = new Rectangle();
        rect.setWidth(15);
        rect.setHeight(20);
        rect.setFill(new ImagePattern(Sprites.bullet));
        rect.rotateProperty().bind(bullet.angleProperty());
        this.getChildren().add(rect);
    }

    public Bullet getBullet() {
        return bullet;
    }
}
