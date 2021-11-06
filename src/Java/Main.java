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

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setResizable(false);

        mainStage.setTitle("Drone Simulation");
        mainStage.setScene(new Menu().getMenuScene());
        mainStage.show();
    }

    public static Image loadImage(String path){
        try {
            System.out.println();
            Image drone = new Image(Main.class.getResource(path).toURI().getPath());

            return drone;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
           return null;
    }

    public static void main (String[] args){
        launch(args);
    }
}
