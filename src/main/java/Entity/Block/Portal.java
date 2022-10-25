package Entity.Block;

import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import javafx.scene.image.Image;
import Entity.Entity;

import static GameRunner.RunBomberman.*;


public class Portal extends Entity {
    public static boolean isPortal = false;
    private long waitingTime;

    /**
     * Constructor.
     */
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (player.getX() == this.x && player.getY() == this.y && !isPortal) {
            isPortal = true;
            waitingTime = System.currentTimeMillis();
        }

        if (isPortal) {
            long currentTime = System.currentTimeMillis();
            // The loading page
            System.out.println("Loading new map");
            if (currentTime - waitingTime > 3000) {
                player = new Bomber(Figure.speed * 4, 2, 5,"right", 1);
                Bomb.countBomb = 1;
                Bomb.range = 0;
                switch (level) {
                    case 1 -> Bomb.bombNumber = 20;
                    case 2 -> Bomb.bombNumber = 30;
                    case 3 -> Bomb.bombNumber = 40;
                }
                wait = false;
                map = new Map(level);
            }
        }
    }
}