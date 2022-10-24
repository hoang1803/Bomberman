package Entity;

import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.io.FileNotFoundException;

public abstract class Entity{
    protected int x;
    protected int y;
    protected Image image;
    protected int currentFrame;
    /**
     * Constructor 1.
     */
    public Entity() {

    }

    /**
     * Constructor 2.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param image Image
     */
    public Entity(int x, int y, Image image) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.image = image;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }
    public int getCurrentFrame() {
        return currentFrame;
    }
    public abstract void update();
}
