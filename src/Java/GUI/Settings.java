package Java.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import Java.Main;

public class Settings extends VBox {

    private final Scene scene;

    private double layout_width = 500, layout_height = 400;

    public Settings(){
        scene = new Scene(this, layout_width, layout_height);

        // settings vertical layout properties
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPrefSize(layout_width, layout_height);

        // adding heading label
        this.getChildren().add(GUIControls.getLabel("Settings",
                Pos.CENTER,
                30,
                Color.DARKGRAY,
                60, 300,
                0,0
        ));

        HBox areaWidth_container = GUIControls.getHorizontalBox(Pos.CENTER, 30, layout_width, 60, 0, 0);

        areaWidth_container.getChildren().add(
                GUIControls.getLabel("Arena Width",
                        Pos.CENTER_LEFT,
                        22,
                        Color.DARKGRAY,
                        40,
                        150,
                        0,
                        0)
        );

        TextField width_input = GUIControls.getTextField("width",
                20,
                150,
                40,
                0,
                0);

        areaWidth_container.getChildren().add(width_input);

        this.getChildren().add(areaWidth_container);

        HBox areaHeight_container = GUIControls.getHorizontalBox(Pos.CENTER, 30, layout_width, 60, 0, 0);

        areaHeight_container.getChildren().add(
                GUIControls.getLabel("Arena Height",
                        Pos.CENTER_LEFT,
                        22,
                        Color.DARKGRAY,
                        40,
                        150,
                        0,
                        0)
        );

        TextField height_input = GUIControls.getTextField("height",
                20,
                150,
                40,
                0,
                0);

        areaHeight_container.getChildren().add(height_input);
        this.getChildren().add(areaHeight_container);

        this.getChildren().add(GUIControls.getButton("SAVE",
                24,
                50, 250,
                0, 0,
                e->{
                    // save the settings here and get back to the menu.
                    Menu menu = new Menu();
                    Main.mainStage.setScene(menu.getMenuScene());
                }));
    }

    public Scene getSettingsScene(){
        return scene;
    }
}
