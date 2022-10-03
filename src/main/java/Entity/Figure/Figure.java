package Entity.Figure;

import Entity.Entity;
import javafx.scene.image.Image;

public abstract class Figure extends Entity {
    public static final int DELAY_TIME = 4;
    public static final int speed = 8;
    public static final int DEFAULT_COUNT = 4;
    protected int currentSpeed;
    protected int currentFrame;
    protected String direction;
    protected int step;
    protected int count;
    protected int delayTime;
    protected int life;



    public Figure() {

    }

    public Figure(int x, int y, Image image) {
        super(x, y, image);
    }

    public Figure(int currentSpeed, int currentFrame, String direction, int life) {
        this.currentSpeed = currentSpeed;
        this.currentFrame = currentFrame;
        this.direction = direction;
        this.life = life;
        this.count = 0;
        this.delayTime = 0;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getLife() {
        return life;
    }

    public int getCount() {
        return count;
    }

    public int getDelayTime() {
        return delayTime;
    }


    public String getDirection() {
        return direction;
    }

    public int getStep() {
        return step;
    }

    @Override
    public void update() {

    }
}
