package Entity.Figure.Enemies;

import Graphics.Sprite;
import javafx.scene.image.Image;

import static Entity.Figure.Intelligent.AStar.findDirection;
import static GameRunner.RunBomberman.*;


public class Oneal extends Enemy {

    private int timeLeftTransDirect = 0;

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
            if(currentFrame != 1) {
                return;
            }
            setCurrentSpeed((int) Math.pow(2,RAND.nextInt(2) + 2));
            setCount(Math.max(1, currentSpeed / 4));
            timeLeftTransSpeed = (RAND.nextInt(200) + 2000);
        }
    }

    public void autoTransDirection() {
        autoSpeed();
        super.autoTransDirection();
       // setDirection(findDirection(this, WIDTH, HEIGHT));
    }

    @Override
    public void update() {
        autoSpeed();
        autoTransDirection();
        super.update();
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.oneal_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }
}
