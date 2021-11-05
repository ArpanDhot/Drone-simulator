package Java.Models;

import java.util.Random;

public class Bullet extends Position{

    private Direction direction;
    private int moveSpeedX = 5;
    private int moveSpeedY = 5;

    public Bullet(double yPos, double xPos) {
        super(yPos, xPos);

        int x = new Random().nextInt(3) - 1; // it return -1 , 0, 1
        int y = 1;

        setUpDirection(x, y);
    }

    public Direction getDir() {
        return direction;
    }

    public void changeDirection(int y){
        moveSpeedX -= 1;
        moveSpeedY = y;
        setUpDirection(moveSpeedX-(moveSpeedX-1), moveSpeedY-(moveSpeedY-1));
    }

    public void setUpDirection(double x, double y){
        if(x == 0 && y == 1) direction = Direction.DOWN;
        if(x == 0 && y == -1) direction = Direction.UP;
        if(x == 1 && y == 1) direction = Direction.DOWN_RIGHT;
        if(x == 1 && y == -1) direction = Direction.DOWN_LEFT;
        if(x == -1 && y == 1) direction = Direction.UP_LEFT;
        if(x == -1 && y == 1) direction = Direction.UP_RIGHT;
    }

    public void setDir(Direction dir) {
        this.direction = dir;
    }

    public void Move(){
        updatePosition(moveSpeedX, moveSpeedY);
    }
}
