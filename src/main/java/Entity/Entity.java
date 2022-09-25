package Entity;

import Graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.io.FileNotFoundException;

public abstract class Entity{
    protected int x;
    protected int y;
    protected Image image;

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

    public abstract void update();
}
