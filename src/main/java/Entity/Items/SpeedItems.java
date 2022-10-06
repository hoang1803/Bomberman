package Entity.Items;

import Entity.Block.Bomb;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;

public class SpeedItems extends Items {
    public SpeedItems(int x, int y, Image image) {
        super(x, y, image);
    }

    public SpeedItems() {

    }

    public SpeedItems(boolean receive) {
        this.received = receive;
    }

    @Override
    public void update() {
        int x = player.getX();
        int y = player.getY();
        if (!this.received && x == this.x && y == this.y) {
            this.received = true;
            this.image = Sprite.grass.getFxImage();
            int des = player.getDecreaseDelay();
            player.setDecreaseDelay(des + 1);
        }
    }
}
