package Java.GUI;

import Java.DroneArena.MainContainer;
import Java.Main;
import Java.Database.DataHandling;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.json.JSONArray;

import java.io.File;

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
                    container.getArena().setup();
                    container.getArena().setup_timeline();
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
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Load Simulation State");
                    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JSON FILES (*.json)", "*.json");
                    chooser.getExtensionFilters().add(filter);
                    File file = chooser.showOpenDialog(null);
                    JSONArray array = DataHandling.load_simulation_state(file);

                    MainContainer container = new MainContainer();
                    Main.mainStage.setScene(container.getSceneMainContainer());
                    if(array == null) return;
                    container.getArena().load_data(array);
                    container.getArena().setup_timeline();

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
                    Help help = new Help();
                    Main.helpPageTracker=1;
                    Main.mainStage.setScene(help.getHelpScene());
                }
        ));
    }

    public Scene getMenuScene() {
        return scene;
    }


}
