package Entity.Block;

import javafx.scene.image.Image;
import Entity.Entity;

public class Portal extends Entity {
    public static boolean IsPortal = false;

    /**
     * Constructor.
     */
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}