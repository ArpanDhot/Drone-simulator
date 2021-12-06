package Java;

import javafx.scene.image.Image;

public class Sprites {

    public static Image drone;
    public static Image left_plane;
    public static Image right_plane;
    public static Image bullet;
    public static Image pipe;

    public static Image[] clouds = new Image[6];
    public static Image[] grass = new Image[2];

    public static Image[] explosions = new Image[48];

    public static Image fighter_drone;
    public static Image tank;

    /**
     * We are assigning the name of the images to the variable of type Image.
     */

    public static void load_images(){
        drone = Main.loadImage("Drone.png");
        pipe = Main.loadImage("Pipe.png");
        bullet = Main.loadImage("bullet.png");
        left_plane = Main.loadImage("left_look.png");
        right_plane = Main.loadImage("right_look.png");

        fighter_drone = Main.loadImage("fighter_drone.png");
        tank = Main.loadImage("tank.png");


        int index = 0;
        for(int row = 1; row<=6; row++){
            for(int col = 1; col<=8; col++){
                explosions[index++] = Main.loadImage("explosions/row-"+row+"-column-"+col+".png"); // The path of it starts from the directory and then the names.  "explosions" == folder
            }
        }

        for(int i = 0; i< 6; i++){
            clouds[i] = Main.loadImage("clouds/cloud"+(i+1)+".png");
        }

        grass[0] = Main.loadImage("grass/tile000.png");
        grass[1] = Main.loadImage("grass/tile001.png");
    }
}
