package Entity.Figure.Enemies;

import Control.Move;
import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;


public class Balloom extends Enemy {

    public Balloom() {

    }

    public Balloom(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.balloom_left1.getFxImage());
    }

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void autoTransDirection() {
        timeLeft--;
        int pX = x / Sprite.SCALED_SIZE;
        int pY = y / Sprite.SCALED_SIZE;

        if (this.transDirection || pX * Sprite.SCALED_SIZE == x && pY * Sprite.SCALED_SIZE == y) {

            if (this.transDirection || timeLeft <= 0) {
                timeLeft = RAND.nextInt(20) * 4;
            } else {
                return;
            }
            setTransDirection(false);

            boolean left = ((pX >= 1) && (!Move.hasBlock(objectMap[pY][pX - 1])));
            boolean right = ((pX + 1 < WIDTH) && (!Move.hasBlock(objectMap[pY][pX + 1])));
            boolean up = ((pY >= 1) && (!Move.hasBlock(objectMap[pY - 1][pX])));
            boolean down = ((pY + 1 < HEIGHT) && (!Move.hasBlock(objectMap[pY + 1][pX])));
            int tmp = RAND.nextInt(2) + 1;
            switch (direction) {
                case "left": {
                    if (up && tmp == 1) {
                        direction = "up";
                    } else if (down && tmp == 2) {
                        direction = "down";
                    } else {
                        direction = "right";
                    }
                }
                break;

                case "right": {
                    if (down && tmp == 1) {
                        direction = "down";
                    } else if (up && tmp == 2) {
                        direction = "up";
                    } else {
                        direction = "left";
                    }
                }
                break;

                case "up": {
                    if (right && tmp == 1) {
                        direction = "right";
                    } else if (left && tmp == 2) {
                        direction = "left";
                    } else {
                        direction = "down";
                    }
                }
                break;

                case "down": {
                    if (left && tmp == 1) {
                        direction = "left";
                    } else if (right && tmp == 2) {
                        direction = "right";
                    } else {
                        direction = "up";
                    }
                }
                break;

                default: {

                }
            }
        }
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.balloom_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }

    @Override
    public void update() {
        autoTransDirection();
        super.update();
    }
}
