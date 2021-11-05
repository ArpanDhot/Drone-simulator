package Java;

import Java.GUI.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        mainStage.setTitle("Drone Simulation");
        mainStage.setScene(new Menu().getMenuScene());
        mainStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
