package Entity.Figure;

import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.player;

public class Bomber extends Figure {
    public int deadFrame = 1;
    private int countDead = 0;
    private boolean isDead = false;

    public Bomber() {

    }

    public Bomber(int currentSpeed, int currentFrame, String direction, int life) {
        super(currentSpeed, currentFrame, direction, life);
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    private void killBomber(Figure figure) {
        //Write code here

    }

    /**
     * Giao nhau.
     */
    private boolean hasIntersect(int pos1, int pos2) {
        int esp = 5;
        return (pos2 < pos1 + Sprite.SCALED_SIZE &&
                    pos1 + Sprite.SCALED_SIZE < pos2 + Sprite.SCALED_SIZE) ||
                (pos1 < pos2 + Sprite.SCALED_SIZE &&
                        pos2 + Sprite.SCALED_SIZE < pos1 + Sprite.SCALED_SIZE);
    }

    private void checkEnemy() {
        int pX = player.getX();
        int pY = player.getY();
        for(Figure figure : enemy) {
            int fX = figure.getX();
            int fY = figure.getY();

            if (fY == pY && hasIntersect(pX, fX)) {
                isDead = true;
                break;
            }

            if (fX == pX && hasIntersect(pY, fY)) {
                isDead = true;
                break;
            }

            if (hasIntersect(pX, fX) && hasIntersect(pY, fY)) {
                isDead = true;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkEnemy();

        if (isDead) {
            player.life--;
            killBomber(player);
            isDead = false;
        }

        countDead++;
    }
}
