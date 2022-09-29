package Entity.Figure;

import Entity.Entity;
import javafx.scene.image.Image;

public abstract class Figure extends Entity {
    protected int currentSpeed;
    protected int currentFrame;
    protected String direction;
    protected boolean life;

    public Figure() {

    }

    public Figure(int x, int y, Image image) {
        super(x, y, image);
    }

    public Figure(int currentSpeed, int currentFrame, String direction, boolean life) {
        this.currentSpeed = currentSpeed;
        this.currentFrame = currentFrame;
        this.direction = direction;
        this.life = life;
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

    public void setLife(boolean life) {
        this.life = life;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public boolean isLife() {
        return life;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public void update() {

    }
}
