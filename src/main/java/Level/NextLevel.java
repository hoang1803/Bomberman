package Level;


import Control.Menu;
import Entity.Block.Grass;
import Entity.Block.Portal;
import Entity.Entity;
import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;

import static Control.Menu.*;
import static GameRunner.RunBomberman.*;


public class NextLevel {
    public static final int numberOfLevel = 2;

    public static void nextLevel() {
        if (level < numberOfLevel) {
            if (enemy.isEmpty()) {
                block.add(new Portal(WIDTH - 2, HEIGHT - 2, Sprite.portal.getFxImage()));
//                Entity entity = null;
                objectMap[HEIGHT - 2][WIDTH - 2] = Map.PORTAL;
                RunBomberman.wait = true;
//                level++;
            }
        } else {
            if (enemy.isEmpty()) {
                winGame = true;
            }
        }

    }
}
