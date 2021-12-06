package Java.DroneArena;

import Java.Main;
import Java.Models.FighterDrone;
import Java.Models.Position;
import Java.Models.Tank;
import Java.Sprites;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class Arena extends Pane {

    private double fireDelay = 1000f;
    private double delay = fireDelay;

    private Timeline timeline;

    private PlaneUI plane;
    public ArrayList<DroneUI> drones;
    public ArrayList<BulletUI> bullets;
    public ArrayList<Explosion> explosions;
    public Tank tank;
    public ArrayList<FighterDrone> fighter_drones = new ArrayList<>();

    private AudioClip background_music, explosion_sound;

    public Arena() {
        new Scene(this);
        this.setPrefSize(Main.width, Main.height);
        drones = new ArrayList<>();
        bullets = new ArrayList<>();
        explosions = new ArrayList<>();
        timeline = new Timeline();
        background();

        try {
            background_music = new AudioClip(getClass().getResource("../sound/background_music.wav").toURI().toString());
            background_music.setCycleCount(AudioClip.INDEFINITE);
            background_music.setVolume(.5);

            explosion_sound = new AudioClip(getClass().getResource("../sound/sound.wav").toURI().toString());
            explosion_sound.setCycleCount(1);
            explosion_sound.setVolume(1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }





    public void load_data(JSONArray array){
        for (Object o : array) {
            JSONObject object = (JSONObject) o;
            String name = object.getString("model_name");
            switch (name) {
                case "Bullet" -> {
                    BulletUI bulletUI = new BulletUI(0, 0);
                    bullets.add(bulletUI);
                    this.getChildren().add(bulletUI);
                    bulletUI.getBullet().unpack_json(object);
                }
                case "Drone" -> {
                    DroneUI droneUI = new DroneUI(0, 0);
                    drones.add(droneUI);
                    this.getChildren().add(droneUI.getBase());
                    this.getChildren().add(droneUI.getTop_right());
                    this.getChildren().add(droneUI.getTop_left());
                    this.getChildren().add(droneUI.getDown_right());
                    this.getChildren().add(droneUI.getDown_left());
                    droneUI.getDrone().unpack_json(object);
                }
                case "Fighter Drone" -> {
                    FighterDrone fighterDrone = new FighterDrone(0, 0);
                    fighterDrone.unpack_json(object);
                    fighter_drones.add(fighterDrone);
                    this.getChildren().add(fighterDrone.makeShape());
                }
                case "Plane" -> {
                    plane = new PlaneUI(0, 0);
                    plane.getPlane().unpack_json(object);
                    this.getChildren().add(plane);
                }
                case "Tank" -> {
                    tank = new Tank(0, 0);
                    tank.unpack_json(object);
                    this.getChildren().add(tank.makeShape());
                }
            }
        }
    }

    void background(){
        //Changed the colour of Pane.
        this.setStyle("""
                -fx-background-color: #8ae0ff;
                """);

        //Adding the ten clouds in here.
        //Assigning random x from 0 to width and y from 0 to 40.
        for(int i = 0; i < 10; i++){
            double x = new Random().nextDouble(Main.width);
            double y = new Random().nextDouble(40);
            ImageView imgView = new ImageView(Sprites.clouds[new Random().nextInt(6)]);
            imgView.setLayoutX(x);
            imgView.setLayoutY(y);
            this.getChildren().add(imgView);
        }

        //To calculate the amount of sprites can be loaded in the given width.
        int tile_width = 100;
        int d = (int) (Main.width/tile_width);
        int y = (int) (Main.height-10);

        //Placing the images
        for(int i = 0; i<d; i++){
            ImageView img = new ImageView(Sprites.grass[i%2]);
            img.setLayoutX(i*tile_width);
            img.setLayoutY(y);
            this.getChildren().add(img);
        }

        // Pipe
        ImageView imgView = new ImageView(Sprites.pipe);
        imgView.setFitHeight(80);
        imgView.setFitWidth(60);
        imgView.setLayoutX(Main.width/1.5);
        imgView.setLayoutY(Main.height-50);
        this.getChildren().add(imgView);

    }

    /**
     * Setting up the plane and the tank.
     */
    public void setup(){
        plane = new PlaneUI(Main.width/2, 20);
        this.getChildren().add(plane);

        tank = new Tank(0, Main.height-59);
        tank.setWidth(120);
        tank.setHeight(60);
        tank.setDir(1);
        this.getChildren().add(tank.makeShape());
    }


    /**
     * Method updates all the objects
     */
    public void setup_timeline(){
        //Setting up the timeline
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(50), e->{
            //Updating the plane and tank
            plane.update();
            tank.update();


            //FighterDrone
            for (FighterDrone fighter_drone : fighter_drones) {
                //If we have any fighter drone in the linked list then it will update it.
                fighter_drone.update();
                if(fighter_drone.getTarget() == null){
                    if(!drones.isEmpty()){
                        //It also checks if fighter drone has got the target if not it assigns a new target.
                        fighter_drone.setTarget(drones.get(new Random().nextInt(drones.size())).getDrone());
                    }
                }
            }

/**
 *
 */
            drones.forEach(drone ->{
                drone.getDrone().update();

                //Checking if the drone collides with the plane
                if(drone.getBase().getBoundsInParent().intersects(plane.getBoundsInParent())) {
                    drone.getDrone().setDir(1, 1);
                    //when it collides then explode
                    if (Position.distance(drone.getDrone(), plane.getPlane()) < 15) {
                        drone.getDrone().setDead(true);
                        explode(drone.getDrone().getPos_X(), drone.getDrone().getPos_Y());
                    }
                }

                //Checking if the drone collides with the tank
                if(drone.getBase().getBoundsInParent().intersects(tank.getShape().getBoundsInParent())) {
                    drone.getDrone().setDir(-1, -1);
                    //when it collides then explode
                    if (Position.distance(drone.getDrone(), plane.getPlane()) < 15) {
                        drone.getDrone().setDead(true);

                        explode(drone.getDrone().getPos_X(), drone.getDrone().getPos_Y());
                    }
                }

                //Drone collision detection to other drones
                for (DroneUI d : drones) {
                    if(d == drone) continue;
                    //When the distance is less than 30 move the opposite direction
                    if(Position.distance(drone.getDrone(), d.getDrone()) < 30){
                        drone.getDrone().setDir(d.getDrone().getDirX()*-1, d.getDrone().getDirY()*-1);
                    }
                }

                //Bullet collision and hit drone explode
                for (BulletUI bullet : bullets) {
                    // TOP LEFT COLLISION
                    if(drone.getTop_left().getBoundsInParent().intersects(bullet.getBoundsInParent())) { //Using the sensor to detect the collision
                        drone.setFillSensors(Color.RED);
                        drone.getDrone().setDir(1, 1);
                    }
                    // TOP RIGHT COLLISION
                    if(drone.getTop_right().getBoundsInParent().intersects(bullet.getBoundsInParent())) {
                        drone.setFillSensors(Color.RED);
                        drone.getDrone().setDir(-1, 1);
                    }
                    // DOWN LEFT COLLISION
                    if(drone.getDown_left().getBoundsInParent().intersects(bullet.getBoundsInParent())) {
                        drone.setFillSensors(Color.RED);
                        drone.getDrone().setDir(1, -1);
                    }
                    // DOWN RIGHT COLLISION
                    if(drone.getDown_right().getBoundsInParent().intersects(bullet.getBoundsInParent())) {
                        drone.setFillSensors(Color.RED);
                        drone.getDrone().setDir(-1, -1);
                    }

                    //When the distance is less than 20 drone explode
                    if(Position.distance(drone.getDrone(),bullet.getBullet()) < 20) {
                        drone.getDrone().setDead(true);
                        explode(drone.getDrone().getPos_X(), drone.getDrone().getPos_Y());
                        this.getChildren().remove(bullet);
                        bullet.getBullet().setDir(0,-1);
                        bullet.getBullet().setPosition(0,-50);
                    }
                }

                //Fighter Drone collision and explosion
                for (FighterDrone fighter_drone : fighter_drones) {
                    //When the distance is less than 20 drone explode
                    if(Position.distance(fighter_drone, drone.getDrone()) < 20 ){
                        drone.getDrone().setDead(true);
                        fighter_drone.setDead(true);
                        explode(drone.getDrone().getPos_X(), drone.getDrone().getPos_Y());
                        explode(fighter_drone.getPos_X(), fighter_drone.getPos_Y());

                    }
                }
            });

            //Updating the bullet
            for (BulletUI bullet : bullets) {
                bullet.getBullet().update();
            }

            //Method in explosion class
            explosions.forEach(Explosion::countDown);

            //To remove the explosion if the countdown is less than 0
            explosions.removeIf(explosion -> {
                if(explosion.countDown() < 0) {
                    this.getChildren().remove(explosion);
                    return true;
                }
                return false;
            });

            //To remove the bullet if the Y axis is less than -30
            bullets.removeIf(b -> {
                if(b.getBullet().getPos_Y() < -30){
                    this.getChildren().remove(b);
                    return true;
                }
                return false;
            });

            //To remove the drone when is dead
            drones.removeIf(drone ->{
                if(drone.getDrone().isDead()){
                    this.getChildren().remove(drone.getBase());
                    this.getChildren().remove(drone.getTop_left());
                    this.getChildren().remove(drone.getTop_right());
                    this.getChildren().remove(drone.getDown_left());
                    this.getChildren().remove(drone.getDown_right());
                    return true;
                }
                return false;
            });

            //To remove the fighter drone when is dead
            fighter_drones.removeIf(d->{
                if(d.isDead()){
                    this.getChildren().remove(d.getShape());
                    return true;
                }
                return false;
            });

            //Fire delay of the plane shooting
            if(delay < 0){
                fire();
                delay = fireDelay;
            }
            delay -= 20;

        });
        timeline.getKeyFrames().add(frame);
    }

    /**
     * Explosion
     */
    private void explode(double x, double y) {
        Explosion explosion = new Explosion(x, y);
        explosions.add(explosion);
        this.getChildren().add(explosion);
        explosion_sound.play();
    }

    /**
     * Shoot bullet
     */
    public void fire(){
        BulletUI bullet = new BulletUI(plane.getLayoutX(), plane.getLayoutY()+25);
        bullets.add(bullet);
        this.getChildren().add(bullet);
    }

    //Used in the debug to pint the axis
    public PlaneUI getPlane() {
        return plane;
    }

    //play the simulation
    public void startSimulationControl(){
        timeline.play();
    }

    //play the music
    public void play_music(){
        if(background_music.isPlaying())
            background_music.stop();
        else
            background_music.play();
    }

    //pause the simulation
    public void pauseSimulation(){
        timeline.pause();
    }

    //Method to add the drone
    public void addDrone(){
        DroneUI drone = new DroneUI(Main.width/1.5, Main.height-55);
        this.getChildren().add(drone.getBase());
        this.getChildren().add(drone.getTop_left());
        this.getChildren().add(drone.getTop_right());
        this.getChildren().add(drone.getDown_left());
        this.getChildren().add(drone.getDown_right());
        drones.add(drone);
    }

    //Used in the debug to pint the axis
    public Tank getTank() {
        return tank;
    }

    //Method used to give random first target
    public void attack(){
        FighterDrone fighterDrone = new FighterDrone(tank.getPos_X()+tank.getWidth()/2, tank.getPos_Y()-10);
        fighterDrone.setWidth(25);
        fighterDrone.setHeight(25);
        this.getChildren().add(fighterDrone.makeShape());
        if(!drones.isEmpty())
            fighterDrone.setTarget(drones.get(new Random().nextInt(drones.size())).getDrone());
        fighter_drones.add(fighterDrone);
    }
}
