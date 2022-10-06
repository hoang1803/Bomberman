package Entity.Items;

import Entity.Block.Bomb;
import Entity.Entity;
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
        int x = player.getX();
        int y = player.getY();
        if (!this.received && x == this.x && y == this.y) {
            this.received = true;
            this.image = Sprite.grass.getFxImage();
            Bomb.range++;
        }
    }


}
