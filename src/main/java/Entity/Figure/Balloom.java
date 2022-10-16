package Entity.Figure;

import Control.Move;
import Graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

import static GameRunner.RunBomberman.*;


public class Balloom extends Figure {
    public int deadFrame = 1;
    private int countDead = 0;

    private boolean transDirect = false;
    private Random rand = new Random();

    private int timeLeft = 0;

    public Balloom() {

    }

    public Balloom(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.balloom_left1.getFxImage());
        super.setDefaultCount(1);
        super.setCount(1);
    }

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setTransDirection(boolean transDirection) {
        super.setTransDirection(transDirection);
        if (transDirection) {
            setDelayTime(0);
        }
    }

    public void transDirection() {
        timeLeft--;
        int pX = x / Sprite.SCALED_SIZE;
        int pY = y / Sprite.SCALED_SIZE;

        if (transDirect || pX * Sprite.SCALED_SIZE == x && pY * Sprite.SCALED_SIZE == y) {

            if (transDirect || timeLeft <= 0) {
                timeLeft = rand.nextInt(8) * 4;
            } else {
                return;
            }
            setTransDirection(false);

            boolean left = ((pX >= 1) && (!Move.hasBlock(objectMap[pY][pX-1])));
            boolean right = ((pX + 1 < WIDTH) && (!Move.hasBlock(objectMap[pY][pX+1])));
            boolean up = ((pY >= 1) && (!Move.hasBlock(objectMap[pY-1][pX])));
            boolean down = ((pY + 1 < HEIGHT) && (!Move.hasBlock(objectMap[pY+1][pX])));
            int tmp = rand.nextInt(2) + 1;
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
                    if (up && tmp == 1) {
                        direction = "up";
                    } else if (down && tmp == 2) {
                        direction = "down";
                    } else {
                        direction = "left";
                    }
                }
                break;

                case "up": {
                    if (left && tmp == 1) {
                        direction = "left";
                    } else if (right && tmp == 2) {
                        direction = "right";
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
            System.out.println(direction);
        }
    }

    @Override
    protected void kill() {
        life = -1;
        this.setDelayTime(5);
        this.setImage(Sprite.balloom_dead.getFxImage());
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
