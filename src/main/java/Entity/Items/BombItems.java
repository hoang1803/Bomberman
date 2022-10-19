package Entity.Items;

import Entity.Block.Bomb;
import Entity.Figure.Bomber;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.player;

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
        int x = player.getX();
        int y = player.getY();
        if (!this.received && x == this.x && y == this.y) {
            this.received = true;
            this.image = Sprite.grass.getFxImage();
            ((Bomber) player).setCountBomb(((Bomber) player).getCountBomb() + 1);
        }
    }
}
