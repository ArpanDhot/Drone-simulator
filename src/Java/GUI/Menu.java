package Java.GUI;

import Java.DroneArena.MainContainer;
import Java.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Menu extends VBox {

    private Scene scene;
    private double layout_width = 500, layout_height = 600;

    public Menu() {
        scene = new Scene(this);

        // settings vertical layout properties
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPrefSize(layout_width, layout_height);

        this.getChildren().add(
                GUIControls.getLabel("DRONE SIMULATION",
                        Pos.CENTER,
                        30,
                        Color.DARKGRAY,
                        40, 300, 0, 0)
        );

        this.getChildren().add(GUIControls.getButton(
                "Start New Game",
                20,
                40,
                200,
                0,
                0,
                e -> {
                    // start the game here.
                    MainContainer container = new MainContainer();
                    Main.mainStage.setScene(container.getSceneMainContainer());
                }

        ));

        this.getChildren().add(GUIControls.getButton(
                "Load Game",
                20,
                40,
                200,
                0,
                0,
                e -> {
                    // load the game configuration file.
                }
        ));

        this.getChildren().add(GUIControls.getButton(
                "Settings",
                20,
                40,
                200,
                0,
                0,
                e -> {
                    // switch to the settings menu.
                    Settings settings = new Settings();
                    Main.mainStage.setScene(settings.getSettingsScene());
                }
        ));

        this.getChildren().add(GUIControls.getButton(
                "Help",
                20,
                40,
                200,
                0,
                0,
                e -> {
                    // load the help menu.
                    Main.mainStage.setScene(new Help().getHelpScene());
                }
        ));
    }

    public Scene getMenuScene() {
        return scene;
    }


}
