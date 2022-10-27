package Level;

import Entity.Block.Portal;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;

import static Control.Menu.*;
import static GameRunner.RunBomberman.*;


public class NextLevel {
    public static final int numberOfLevel = 3;

    public static void nextLevel() {
        if (level < numberOfLevel) {
            if (enemy.isEmpty()) {
                if (objectMap[HEIGHT - 2][WIDTH - 2] != Map.PORTAL) {
                    block.add(new Portal(WIDTH - 2, HEIGHT - 2, Sprite.portal.getFxImage()));
                    objectMap[HEIGHT - 2][WIDTH - 2] = Map.PORTAL;
                }
                RunBomberman.wait = true;
            }
        } else {
            if (enemy.isEmpty()) {
                winGame = true;
            }
        }

    }
}
