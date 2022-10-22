package Entity.Figure.Enemies;

import Control.Move;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;


public class Oneal extends Enemy {

    private int timeLeftTransSpeed = 0;


    public Oneal() {

    }

    public Oneal(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.oneal_left1.getFxImage());
    }

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void autoSpeed() {
        timeLeftTransSpeed--;
        if(timeLeftTransSpeed <= 0) {
            setCurrentSpeed((int) Math.pow(2,RAND.nextInt(2) + 2));
            timeLeftTransSpeed = (RAND.nextInt(200) + 1000);
        }
    }

    @Override
    public void autoTransDirection() {
        int speed = currentSpeed;
        if (player.getY() < this.y) {
            currentSpeed = Math.min(currentSpeed, this.y - player.getY());
            direction = "up";
            if (Move.speedUp(this) > 0) {
                return;
            }
            currentSpeed = speed;
        }
        if (player.getY() > this.y) {
            currentSpeed = Math.min(currentSpeed, player.getY() - this.y);
            direction = "down";
            if (Move.speedDown(this) > 0) {
                return;
            }
            currentSpeed = speed;
        }
        if (player.getX() < this.x) {
            currentSpeed = Math.min(currentSpeed, this.x - player.getX());
            direction = "left";
            if (Move.speedLeft(this) > 0) {
                return;
            }
            currentSpeed = speed;
        }
        if (player.getX() > this.x) {
            currentSpeed = Math.min(currentSpeed, player.getX() - this.x);
            direction = "right";
            if (Move.speedRight(this) > 0) {
                return;
            }
            currentSpeed = speed;
        }
    }

    @Override
    public void update() {
        autoSpeed();
        this.autoTransDirection();
        super.update();
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.oneal_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }
}
