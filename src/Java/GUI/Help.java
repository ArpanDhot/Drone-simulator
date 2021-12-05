package Java.GUI;

import Java.DroneArena.MainContainer;
import Java.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Help extends VBox {

    private Scene scene;
    private double layout_width = 500, layout_height = 400;

    public Help(){
        scene = new Scene(this,layout_width,layout_height);


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


        this.getChildren().add(GUIControls.getLabel(
                "1-To start the game press the 'Start' button\n" +
                        "2-To pause the game press the 'Pause' button\n" +
                        "3-To add and drone press the 'Add drone' button\n" +
                        "4-To charge the drone press 'charge drone' button\n",
                Pos.CENTER,
                15,
                Color.DARKGRAY,
                80,
                400,
                0, 0
        ));


        this.getChildren().add(GUIControls.getLabel(
                "This is a drone simulator, that illustrates the autonomous\n" +
                        "drone moving in an arena trying to doge the enemies that\n" +
                        "are the dumb bullets.",
                Pos.CENTER,
                15,
                Color.DARKGRAY,
                80,
                400,
                0, 0
        ));


        this.getChildren().add(GUIControls.getButton("←",
                16,
                20, 50,
                0,0,e->{
                    if(Main.helpPageTracker == 1){
                        Main.mainStage.setScene(new Menu().getMenuScene());
                    }else if (Main.helpPageTracker == 2){
                        MainContainer container = new MainContainer();
                        Main.mainStage.setScene(container.getSceneMainContainer());
                        container.getArena().setup();
                        container.getArena().setup_timeline();
                    }
                }));


    }

    public Scene getHelpScene(){
        return scene;
    }
}