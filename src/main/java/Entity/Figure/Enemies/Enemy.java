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

    protected static final int LEFT = 1;
    protected static final int RIGHT = 2;
    protected static final int UP = 3;
    protected static final int DOWN = 4;

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
            timeLeft = RAND.nextInt(200) * 10;
        }

        if (this.transDirection) {

            setTransDirection(false);

            int tmp = RAND.nextInt(4) + 1;

            direction = switch (tmp) {
                case UP -> "up";
                case DOWN -> "down";
                case LEFT -> "left";
                case RIGHT -> "right";
                default -> direction;
            };
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
