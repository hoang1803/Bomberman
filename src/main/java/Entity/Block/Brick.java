package Entity.Block;

import Entity.Entity;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.block;
import static GameRunner.RunBomberman.killObject;
import static Graphics.Sprite.SCALED_SIZE;

public class Brick extends Entity {
    public Brick(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void update() {
        for (Entity entity : block) {
            if (entity instanceof Brick)
                if (killObject[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE] == 4) {    // At the element of the 2-dimensional killObject array with the value 4, Brick and Grass will appear
                    entity.setImage(Sprite.grass.getFxImage());
                }
        }
    }
}
