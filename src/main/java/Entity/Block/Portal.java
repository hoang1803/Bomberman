package Entity.Block;

import Control.Menu;
import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import Graphics.Map;
import javafx.scene.image.Image;
import Entity.Entity;

import static GameRunner.RunBomberman.*;


public class Portal extends Entity {
    public boolean isPortal = false;
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
            Menu.nextLevel = true;
            long currentTime = System.currentTimeMillis();
            if (currentTime - waitingTime > 3000) {
                player = new Bomber(Figure.speed * 4, 2, 5,"right", 1);
                Bomb.countBomb = 1;
                Bomb.range = 0;
                level++;
                System.out.println(level);
                switch (level) {
                    case 1 -> {
                        Bomb.bombNumber = 20;
                        Menu.timeLeft = 100;
                    }
                    case 2 -> {
                        Bomb.bombNumber = 30;
                        Menu.timeLeft = 150;
                    }
                    case 3 -> {
                        Bomb.bombNumber = 50;
                        Menu.timeLeft = 250;
                    }
                }
                wait = false;
                map = new Map(level);
                Menu.nextLevel = false;
                runningLevel = true;
            }
        }
    }
}