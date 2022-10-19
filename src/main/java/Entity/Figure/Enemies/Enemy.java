package Entity.Figure.Enemies;

import Control.Move;
import Entity.Figure.Figure;
import Graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

import static GameRunner.RunBomberman.*;
import static GameRunner.RunBomberman.objectMap;

abstract public class Enemy extends Figure {
    public int deadFrame = 1;
    protected int countDead = 0;
    public static final Random RAND = new Random();

    protected int timeLeft = 0; // thoi gian chuyen huong

    public Enemy() {

    }

    public Enemy(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setDefaultCount(1);
        super.setCount(1);
    }

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

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
    public void setTransDirection(boolean transDirection) {
        super.setTransDirection(transDirection);
        if (transDirection) {
            setDelayTime(0);
        }
    }

    @Override
    public void initDead() {
        setDelayTime(15);
        setCount(3);
        setCurrentFrame(1);
    }

    @Override
    public void update() {
        checkBomb();

        if (isDead) {
            life--;
            kill();
            isDead = false;
        }

        countDead++;
    }
}
