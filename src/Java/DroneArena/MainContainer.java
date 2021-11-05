package Java.DroneArena;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MainContainer extends VBox {

    public Scene sceneMainContainer;
    private Arena arena;


    //Main container is a Vbox and it will hold the pane container as well as the VBox. The VBox will contain the toolbar that will give the controls to the user.
    public MainContainer() {
        sceneMainContainer = new Scene(this);
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(5);

        arena = new Arena();
        this.getChildren().add(arena);
        this.getChildren().add(new BottomMenu(arena));
    }


    public Scene getSceneMainContainer() {
        return sceneMainContainer;
    }
}
