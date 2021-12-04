package Java;

import Java.GUI.Menu;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Main extends Application {

    public static Stage mainStage;
    public static double width=600;
    public  static double height=400;
    public static int helpPageTracker=0;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        mainStage.getIcons().add(new Image("C:/Users/Ik-Omkar/Desktop/DroneSimulator/resources/Java/logoTab/pngkit_drone-logo-png_921123.png"));
        mainStage.setResizable(false);

        mainStage.setTitle("Drone Simulation");
        mainStage.setScene(new Menu().getMenuScene());
        mainStage.show();

        Sprites.load_images();
    }

    public static Image loadImage(String path){
        try {
            return new Image(Main.class.getResource(path).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main (String[] args){
        launch(args);
    }
}
