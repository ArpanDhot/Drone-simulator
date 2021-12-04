package Java.DroneArena;

import Java.Models.Plane;
import Java.Sprites;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class PlaneUI extends Group {

    private Plane plane;
    private ImageView imgView;

    public PlaneUI(double x, double y){
        plane = new Plane(x, y);

        this.layoutXProperty().bind(plane.pos_XProperty());
        this.layoutYProperty().bind(plane.pos_YProperty());

        imgView = new ImageView(plane.getDir() == -1 ? Sprites.left_plane : Sprites.right_plane);
        imgView.setFitWidth(120);
        imgView.setFitHeight(60);

        plane.setWidth(imgView.getFitWidth());
        this.getChildren().add(imgView);
    }

    public void update(){
        plane.update();
        imgView.setImage(plane.getDir() == -1 ? Sprites.left_plane : Sprites.right_plane);
    }

    public Plane getPlane() {
        return plane;
    }
}
