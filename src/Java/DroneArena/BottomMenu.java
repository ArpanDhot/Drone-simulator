package Java.DroneArena;

import Java.GUI.GUIControls;
import javafx.scene.control.ToolBar;

public class BottomMenu extends ToolBar {

    private Arena arena;

    public BottomMenu(Arena arena) {
        this.arena = arena;

        //TODO start , restart, pause, add drone, charge drone
        this.getItems().add(GUIControls.getButton("start",
                16,
                25, 100,
                0, 0,
                e->{
                    // start the simulation.
                    arena.startSimulationControl();
                }
        ));
        this.getItems().add(GUIControls.getButton("restart",
                16,
                25, 100,
                0, 0,
                e->{
                    // restart the simulation.
                }));

        this.getItems().add(GUIControls.getButton("add drone",
                16,
                25, 100,
                0,0,
                e->{
                    // add drone in the arena
                }));
        this.getItems().add(GUIControls.getButton("charge drone",
                16,
                25, 100,
                0,0,e->{
                    // charge the drone.
                }));
    }


}
