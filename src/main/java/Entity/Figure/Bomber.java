package Entity.Figure;

import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.player;

public class Bomber extends Figure {
    public int deadFrame = 1;
    private int countDead = 0;

    public Bomber() {

    }

    public Bomber(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setX(Sprite.SCALED_SIZE);
        super.setY(Sprite.SCALED_SIZE);
        super.setImage(Sprite.player_right.getFxImage());
        super.setDefaultCount(4);
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    protected void kill() {
        if (countDead % 32 == 0) {
            if (deadFrame == 1) {
                setImage(Sprite.player_dead1.getFxImage());
                deadFrame = 2;
            } else if (deadFrame == 2) {
                setImage(Sprite.player_dead2.getFxImage());
                deadFrame = 3;
            } else if (deadFrame == 3) {
                setImage(Sprite.player_dead3.getFxImage());
                deadFrame = 4;
            } else {
                System.err.println("Ngu qua");
                System.exit(1);

            }
        }

        countDead++;
    }


    /**
     * Giao nhau.
     */
    private boolean hasIntersect(int pos1, int pos2) {
        int esp = 8;
        return (pos1 < pos2 && (pos1 + Sprite.SCALED_SIZE - 1) - pos2 >= esp)
                || (pos2 < pos1 && (pos2 + Sprite.SCALED_SIZE - 1) - pos1 >= esp);
    }

    private void checkEnemy() {
        int pX = player.getX();
        int pY = player.getY();
        for(Figure figure : enemy) {
            if (figure.getLife() <= 0) {
                continue;
            }
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
        checkBomb();

        if (isDead) {
            player.life--;
            isDead = false;
        }

        if (player.life <= 0) {
            kill();
        }
    }
}
