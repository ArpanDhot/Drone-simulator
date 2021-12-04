package Java.DroneArena;

import Java.GUI.GUIControls;
import Java.Model.FighterDrone;
import Java.database.DataHandling;
import javafx.scene.control.ToolBar;
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
        this.getItems().add(GUIControls.getButton("start",
                14,
                25, 75,
                0, 0,
                e->{
                    // start the simulation.
                    arena.startSimulationControl();
                    if(debugObjects != null)
                        debugObjects.startDebug();
                }
        ));
        this.getItems().add(GUIControls.getButton("pause",
                14,
                25, 75,
                0, 0,
                e->{
                    // restart the simulation.
                    arena.pauseSimulation();
                    if(debugObjects != null)
                        debugObjects.pauseDebug();
                }));

        this.getItems().add(GUIControls.getButton("add drone",
                14,
                25, 75,
                0,0,
                e->{
                    // add drone in the arena
                    arena.addDrone();
                }));
        this.getItems().add(GUIControls.getButton("save",
                14,
                25, 75,
                0,0,e->{
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

        this.getItems().add(GUIControls.getButton("attack", 14, 25, 75,0,0,
                e->{
                    // attack
                    arena.attack();
                }));
        this.getItems().add(GUIControls.getButton("music", 14, 25, 75,0,0,
                e->{
                    // attack
                    arena.play_music();
                }));
        this.getItems().add(GUIControls.getButton("debug", 14, 25, 75,0,0,
                e->{
                    if(debugObjects == null){
                        debugObjects = new DebugObjects();
                        debugObjects.setArena(arena);
                        Stage stage = new Stage();
                        stage.setScene(debugObjects.getScene());
                        stage.show();
                    }
                }));
    }


}
