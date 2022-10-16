package Control;

import Entity.Figure.Balloom;
import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;
import javafx.scene.image.Image;

import java.sql.Time;
import java.util.Objects;
import java.util.Timer;

import static Graphics.Sprite.SCALED_SIZE;
import static Graphics.Sprite.balloom_left1;

public class Move {
    public static void figureRun(Figure figure) {
        int count = figure.getCount();
        if(count > 0) {
            figure.setCount(count - 1);

            if (figure instanceof Bomber) {
                setDirection(figure);
            }

            if (figure instanceof Balloom) {
                if (figure.getLife() < 0) {
                    return;
                }
                switch(figure.getDirection()) {
                    case "up" -> {
                        moveUp(figure);
                    }
                    case "down" -> {
                        moveDown(figure);
                    }
                    case "left" -> {
                        moveLeft(figure);
                    }
                    case "right" -> {
                        moveRight(figure);
                    }
                }
                ((Balloom) figure).transDirection();
                setDirection(figure);
            }
        }
    }

    private static void setDirection(Figure figure) {
        int step = figure.getStep();
        switch (figure.getDirection()) {
            case "up" -> {
                renderUp(figure);
                figure.setY(figure.getY() - step);
            }
            case "down" -> {
                renderDown(figure);
                figure.setY(figure.getY() + step);
            }
            case "left" -> {
                renderLeft(figure);
                figure.setX(figure.getX() - step);
            }
            case "right" -> {
                renderRight(figure);
                figure.setX(figure.getX() + step);
            }
        }
    }

    public static boolean hasBlock(char current) {
        return (current == Map.WALL || current == Map.BRICK || current == Map.BOMB);
    }

    private static void renderUp(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, figure);
        }

        if (figure instanceof Balloom) {
            Sprite.renderSpriteForBalloom(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, figure);
        }

        // Write code here
    }

    public static void moveUp(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = figure.getCurrentSpeed();

            int y = (figure.getY() - speed) / SCALED_SIZE;
            int x = figure.getX() / SCALED_SIZE;
            int px = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
            char cur = RunBomberman.objectMap[y][x];
            char cur2 = RunBomberman.objectMap[y][px];

            if (hasBlock(cur) || hasBlock(cur2)) {
                int realY = y * SCALED_SIZE;
                speed = figure.getY() - (realY + SCALED_SIZE);

                if (figure instanceof Balloom) {
                    figure.setTransDirection(true);
                }
            }
            figure.setDirection("up");
            figure.setStep(speed / figure.getDefaultCount());
            figure.setCount(figure.getDefaultCount());
        }
    }

    private static void renderDown(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, figure);
        }

        if (figure instanceof Balloom) {
            Sprite.renderSpriteForBalloom(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, figure);
        }

        // Write code here
    }

    public static void moveDown(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = figure.getCurrentSpeed();
            int y = (figure.getY() + speed + SCALED_SIZE - 1) / SCALED_SIZE;
            int x = figure.getX() / SCALED_SIZE;
            int px = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
            char cur = RunBomberman.objectMap[y][x];
            char cur2 = RunBomberman.objectMap[y][px];

            if (hasBlock(cur) || hasBlock(cur2)) {
                int realY = y * SCALED_SIZE;
                speed = (realY - 1) - (figure.getY() + SCALED_SIZE - 1);

                if (figure instanceof Balloom) {
                    figure.setTransDirection(true);
                }
            }
            figure.setDirection("down");
            figure.setStep(speed / figure.getDefaultCount());
            figure.setCount(figure.getDefaultCount());
        }
    }

    private static void renderLeft(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, figure);
        }

        if (figure instanceof Balloom) {
            Sprite.renderSpriteForBalloom(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, figure);
        }
        // Write code here
    }

    public static void moveLeft(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = figure.getCurrentSpeed();
            int y = figure.getY() / SCALED_SIZE;
            int py = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
            int x = (figure.getX() - speed) / SCALED_SIZE;
            char cur = RunBomberman.objectMap[y][x];
            char cur2 = RunBomberman.objectMap[py][x];
            if (hasBlock(cur) || hasBlock(cur2)) {
                int realX = x * SCALED_SIZE;
                speed = figure.getX() - (realX + SCALED_SIZE);

                if (figure instanceof Balloom) {
                    figure.setTransDirection(true);
                }
            }
            figure.setDirection("left");
            figure.setStep(speed / figure.getDefaultCount());
            figure.setCount(figure.getDefaultCount());
        }
    }

    private static void renderRight(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, figure);
        }
        if (figure instanceof Balloom) {
            Sprite.renderSpriteForBalloom(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, figure);
        }
        // Write code here
    }

    public static void moveRight(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = figure.getCurrentSpeed();
            int y = figure.getY() / SCALED_SIZE;
            int py = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
            int x = (figure.getX() + speed + SCALED_SIZE - 1) / SCALED_SIZE;
            char cur = RunBomberman.objectMap[y][x];
            char cur2 = RunBomberman.objectMap[py][x];
            if (hasBlock(cur) || hasBlock(cur2)) {
                int realX = x * SCALED_SIZE;
                speed = (realX - 1) - (figure.getX() + SCALED_SIZE - 1);

                if (figure instanceof Balloom) {
                    figure.setTransDirection(true);
                }
            }
            figure.setDirection("right");
            figure.setStep(speed / figure.getDefaultCount());
            figure.setCount(figure.getDefaultCount());
        }
    }
}
