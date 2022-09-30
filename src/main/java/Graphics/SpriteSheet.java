package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    private String path;
    public final int SIZE;
    public int[] spritePixel;
    public BufferedImage image;

    /**
     * Load image pixel into spritePixel.
     */
    private void load() {
        try {
            image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, spritePixel, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Constructor.
     * @param path path
     * @param SIZE SpriteSheet size
     */
    public SpriteSheet(String path, int SIZE) {
        this.path = path;
        this.SIZE = SIZE;
        spritePixel = new int[SIZE * SIZE];
        load();
    }

    public static SpriteSheet tiles = new SpriteSheet("res/textures/classic.png", 256);
}
