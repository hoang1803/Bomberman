package Entity.Figure.Enemies;

import Control.Move;
import Entity.Figure.Figure;
import Graphics.Map;
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
    protected boolean transDirection = false;

    protected static final int LEFT = 0;
    protected static final int RIGHT = 1;
    protected static final int UP = 2;
    protected static final int DOWN = 3;

    protected boolean[] canMove = {false, false, false, false};

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

    protected void renderDead() {
        Sprite.renderSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, this);
    }


    public void autoTransDirection() {
        timeLeft--;
        if (timeLeft <= 0) {
            transDirection = true;
            timeLeft = RAND.nextInt(100) * 10;
        }

        if (this.transDirection) {

            setTransDirection(false);

            int tmp = RAND.nextInt(4) + 1;

            canMove[UP] = Move.speedUp(this) > 0;
            canMove[DOWN] = Move.speedDown(this) > 0;
            canMove[LEFT] = Move.speedLeft(this) > 0;
            canMove[RIGHT] = Move.speedRight(this) > 0;

            for (int i = 0; i < 4; i++) {
                if (canMove[i]) {
                    tmp--;
                }
                if (tmp == 0) {
                    direction = switch (i) {
                        case UP -> "up";
                        case DOWN -> "down";
                        case LEFT -> "left";
                        case RIGHT -> "right";
                        default -> "";
                    };
                    return;
                }
            }

            direction = switch (direction) {
                case "up" -> "down";
                case "down" -> "up";
                case "left" -> "right";
                case "right" -> "left";
                default -> "";
            };
        }
    }

    public boolean isTransDirection() {
        return transDirection;
    }

    public void setTransDirection(boolean transDirection) {
        this.transDirection = transDirection;
        if (transDirection) {
            setDelayTime(0);
        }
    }

    public void initDead() {
        life = -2;
        setDelayTime(15);
        setCount(3);
        setCurrentFrame(1);
    }

    @Override
    public void update() {
        if (life == -2) {
            if(count > 0 && canGo()) {
                count--;
                renderDead();
            }
            return;
        }

        checkBomb();

        if (isDead) {
            life--;
            kill();
            isDead = false;
        }

        countDead++;
    }
}
