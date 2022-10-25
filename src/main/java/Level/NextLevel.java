package Level;


import Entity.Block.Grass;
import Entity.Block.Portal;
import Entity.Entity;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;

import static GameRunner.RunBomberman.*;


public class NextLevel {
    public static final int numberOfLevel = 3;

    public static void nextLevel() {

        if (level < numberOfLevel) {
            if (enemy.isEmpty()) {
                block.add(new Portal(WIDTH - 2, HEIGHT - 2, Sprite.portal.getFxImage()));
                Entity entity = null;
                objectMap[HEIGHT - 2][WIDTH - 2] = Map.PORTAL;
                RunBomberman.wait = true;
                level++;
            }
        } else {
            System.out.println("You win");
            //Có thể out luôn hoặc là chuyển về menu
            System.exit(0);
        }

    }
}
