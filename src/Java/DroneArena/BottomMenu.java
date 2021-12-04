package Java.DroneArena;

import Java.GUI.GUIControls;
import Java.GUI.Help;
import Java.Main;
import Java.Models.FighterDrone;
import Java.Database.DataHandling;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.json.JSONArray;

import java.io.File;

public class BottomMenu extends ToolBar {

    private Arena arena;
    private DebugObjects debugObjects;

    public BottomMenu(Arena arena) {
        this.arena = arena;

        //TODO start , restart, pause, add drone, charge drone
        this.getItems().add(GUIControls.getButton("Start",14,25, 65, 0, 0,
                e->{
                    // start the simulation.
                    arena.startSimulationControl();
                    if(debugObjects != null)
                        debugObjects.startDebug();
                }
        ));

        this.getItems().add(GUIControls.getButton("Pause",14,25, 65, 0, 0,
                e->{
                    // restart the simulation.
                    arena.pauseSimulation();
                    if(debugObjects != null)
                        debugObjects.pauseDebug();
                }));

        this.getItems().add(GUIControls.getButton("AddDrone", 14, 25, 85, 0,0,
                e->{
                    // add drone in the arena
                    arena.addDrone();
                }));


        this.getItems().add(GUIControls.getButton("Attack", 14, 25, 65,0,0,
                e->{
                    // attack
                    arena.attack();
                }));

        this.getItems().add(GUIControls.getButton("Save",14,25, 65,0,0,
                e->{
                    // charge the drone.
                    arena.pauseSimulation();
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Save Simulation State");
                    FileChooser.ExtensionFilter filter = new ExtensionFilter("JSON FILES (*.json)", "*.json");
                    chooser.getExtensionFilters().add(filter);
                    File file = chooser.showSaveDialog(null);
                    JSONArray array = new JSONArray();

                    array.put(arena.getPlane().getPlane().getJson());
                    array.put(arena.getTank().toJson());
                    for (BulletUI bullet : arena.bullets) {
                        array.put(bullet.getBullet().getJson());
                    }
                    for (DroneUI drone : arena.drones) {
                        array.put(drone.getDrone().getJson());
                    }
                    for (FighterDrone fighter_drone : arena.fighter_drones) {
                        array.put(fighter_drone.toJson());
                    }
                    DataHandling.save_simulation_state(file, array);
                }));

        this.getItems().add(GUIControls.getButton("Music", 14, 25, 65,0,0,
                e->{
                    // attack
                    arena.play_music();
                }));

        this.getItems().add(GUIControls.getButton("Debug", 14, 25, 65,0,0,
                e->{
                    if(debugObjects == null){
                        debugObjects = new DebugObjects();
                        debugObjects.setArena(arena);
                        Stage stage = new Stage();
                        stage.getIcons().add(new Image("C:/Users/Ik-Omkar/Desktop/DroneSimulator/resources/Java/logoTab/pngkit_drone-logo-png_921123.png"));
                        stage.setScene(debugObjects.getScene());
                        stage.show();
                    }
                }));

        this.getItems().add(GUIControls.getButton("Help",14,25,65,0,0,
                e -> {
                    // load the help menu.
                    Help help = new Help();
                    Main.helpPageTracker=2;
                    Main.mainStage.setScene(help.getHelpScene());
                }
        ));
    }


}
