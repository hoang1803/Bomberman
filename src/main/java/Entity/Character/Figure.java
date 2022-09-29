package Entity.Character;

import Entity.Entity;
import javafx.scene.image.Image;

public abstract class Figure extends Entity {
    protected double defaultSpeed;
    protected int currentFrame;
    protected String direction;
    protected int life;

    public Figure() {

    }

    public Figure(int x, int y, Image image) {
        super(x, y, image);
    }

    public Figure(double defaultSpeed, int currentFrame, String direction, int life) {
        this.defaultSpeed = defaultSpeed;
        this.currentFrame = currentFrame;
        this.direction = direction;
        this.life = life;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setDefaultSpeed(double defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public double getDefaultSpeed() {
        return defaultSpeed;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getLife() {
        return life;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public void update() {

    }
}
