package Entity.Items;

import Entity.Block.Bomb;
import Entity.Entity;
import Entity.Figure.Bomber;
import Graphics.Map;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;

public class BombItems extends Items {
    public BombItems() {

    }

    public BombItems(int x, int y, Image image) {
        super(x, y, image);
    }

    public BombItems(boolean receive) {
        this.received = receive;
    }

    @Override
    public void update() {

        if (!received) {

            if (killObject[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] != 0) {
                setImage(Sprite.powerup_bombs.getFxImage());
                objectMap[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] = Map.GRASS;
            }

            if (player.getX() == this.x && player.getY() == this.y) {
                received = true;
                setImage(Sprite.grass.getFxImage());
                Bomb.countBomb++;
            }
        }
    }
}
