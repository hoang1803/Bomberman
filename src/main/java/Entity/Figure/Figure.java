package Entity.Figure;

import Entity.Entity;
import GameRunner.RunBomberman;
import Graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Figure extends Entity {
    protected int defaultDelayTime = 4;
    public static final int speed = 8;

    protected int defaultCount;
    protected int currentSpeed;
    protected int currentFrame;
    protected String direction;
    protected int step;
    protected int count;
    protected int delayTime;
    protected int life;

    protected int decreaseDelay = 0;

    protected boolean transDirection = false;

    protected boolean renderedMobDead = false;

    protected boolean isDead = false;



    public Figure() {

    }

    public Figure(int x, int y, Image image) {
        super(x, y, image);
    }

    public Figure(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        this.currentSpeed = currentSpeed;
        this.currentFrame = currentFrame;
        this.defaultDelayTime = defaultDelayTime;
        this.direction = direction;
        this.life = life;
        this.count = 0;
        this.delayTime = 0;
    }

    public void setDefaultDelayTime(int defaultDelayTime) {
        this.defaultDelayTime = defaultDelayTime;
    }

    public void setDefaultCount(int defaultCount) {
        this.defaultCount = defaultCount;
    }

    public int getDefaultCount() {
        return defaultCount;
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

    public void setDecreaseDelay(int decreaseDelay) {
        this.decreaseDelay = decreaseDelay;
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

    public int getDecreaseDelay() {
        return decreaseDelay;
    }

    public boolean isRenderedMobDead() {
        return renderedMobDead;
    }

    public void setRenderedMobDead(boolean renderedMobDead) {
        this.renderedMobDead = renderedMobDead;
    }

    public void setTransDirection(boolean transDirection) {
        this.transDirection = transDirection;
    }
    public boolean isTransDirection() {
        return transDirection;
    }

    protected boolean checkBomb() {
        int esp = 5;
        int size = Sprite.SCALED_SIZE;
        int x = (this.x + esp) / size;
        int px = (this.x + size - 1 - esp) / size;
        int y = (this.y + esp) / size;
        int py = (this.y + size - 1 - esp) / size;

        int leftUp = RunBomberman.killObject[y][x];
        int leftDown = RunBomberman.killObject[py][x];
        int rightUp = RunBomberman.killObject[y][px];
        int rightDown = RunBomberman.killObject[py][px];

        if(leftUp != 0 || leftDown != 0 || rightUp != 0 || rightDown != 0) {
            isDead = true;
            return true;
        }
        return false;
    }

    public boolean canGo() {
        if (delayTime == 0) {
            delayTime = Math.max(1, defaultDelayTime - decreaseDelay);
            return true;
        }
        delayTime--;
        return false;
    }

    public void initDead() {

    }

    protected void kill() {

    }

    @Override
    public void update() {

    }
}
