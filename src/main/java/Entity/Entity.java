package Entity;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileNotFoundException;

interface SCREEN {
    int SCREEN_WIDTH = 880;
    int SCREEN_HEIGHT = 500;
}

public abstract class Entity implements SCREEN {
    private int x;
    private int y;
    private Image image;
    private int height;
    private int width;

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
        this.x = x;
        this.y = y;
        this.image = image;
    }

    /**
     * Constructor 3.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param image Image
     * @param height Image height
     * @param width Image width
     */
    public Entity(int x, int y, Image image, int height, int width) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.height = height;
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Get Image from path.
     * @param path Path name
     * @return Image
     * @throws FileNotFoundException
     */
    public static Image getImage(String path) throws FileNotFoundException {
        Image img = new Image(path);
        return img;
    }

    /**
     * Render an image.
     * @param stage stage
     */
    public void render(Stage stage) {
        ImageView imageView = new ImageView(image);

        imageView.setX(x);
        imageView.setY(y);

        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
