package Entity.Items;

import Entity.Block.Bomb;
import Graphics.Map;
import Graphics.Sprite;
import javafx.scene.image.Image;
import static GameRunner.RunBomberman.*;

public class FlameItems extends Items {
    public FlameItems(int x, int y, Image image) {
        super(x, y, image);
    }

    public FlameItems() {

    }

    public FlameItems(boolean received) {
        this.received = received;
    }

    @Override
    public void update() {

        if (!received) {

            if (killObject[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != 0) {
                setImage(Sprite.powerup_flames.getFxImage());
                objectMap[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] = Map.GRASS;
            }

            if (player.getX() == this.x && player.getY() == this.y) {
                received = true;
                setImage(Sprite.grass.getFxImage());
                Bomb.range++;
            }
        }
    }


}
