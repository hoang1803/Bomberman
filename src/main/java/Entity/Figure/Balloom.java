package Entity.Figure;

import Control.Move;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.player;

public class Balloom extends Figure {
    public int deadFrame = 1;
    private int countDead = 0;
    private boolean isDead = false;

    private boolean transDirection = false;

    public Balloom() {

    }

    //Update
    public Balloom(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setX(Sprite.SCALED_SIZE);
        super.setY(Sprite.SCALED_SIZE);
        super.setImage(Sprite.balloom_left1.getFxImage());
        super.setDefaultCount(1);
        super.setCurrentSpeed(5);
    }

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    private void killBalloom(Figure figure) {
        life = -1;
        figure.setDelayTime(5);
        figure.setImage(Sprite.balloom_dead.getFxImage());
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

    private void checkBomb() {
        int size = Sprite.SCALED_SIZE;
        int x = this.x / size;
        int px = (this.x + size - 1) / size;
        int y = this.y / size;
        int py = (this.y + size - 1) / size;

        int leftUp = RunBomberman.killObject[y][x];
        int leftDown = RunBomberman.killObject[py][x];
        int rightUp = RunBomberman.killObject[y][px];
        int rightDown = RunBomberman.killObject[py][px];

        if(leftUp != 0 || leftDown != 0 || rightUp != 0 || rightDown != 0) {
            isDead = true;
        }
    }

    @Override
    public void update() {
        checkBomb();

        if (isDead) {
            life--;
            killBalloom(this);
            isDead = false;
        }

        countDead++;
    }
}
