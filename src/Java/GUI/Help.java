package Java.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Help extends VBox {

    private Scene scene;

    public Help(){
        scene = new Scene(this);

        // vertical layout properties
        setAlignment(Pos.CENTER);
        setSpacing(20);

        this.getChildren().add(GUIControls.getLabel("Help",
                Pos.CENTER,
                30,
                Color.DARKGRAY,
                50,
                200,
                0, 0
        ));

    }

    public Scene getHelpScene(){
        return scene;
    }
}