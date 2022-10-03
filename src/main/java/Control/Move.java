package Control;

import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Objects;

import static Graphics.Sprite.SCALED_SIZE;

public class Move {

    public static void figureRun(Figure figure) {
        int count = figure.getCount();
        if(count > 0) {
            setDirection(figure);
            figure.setCount(count - 1);
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

    private static boolean hasBlock(char current) {
        return (current == Map.WALL || current == Map.BRICK || current == Map.BOMB);
    }

    private static void renderUp(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, figure);
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
            }
            figure.setDirection("up");
            figure.setStep(speed / Figure.DEFAULT_COUNT);
            figure.setCount(Figure.DEFAULT_COUNT);
        }
    }

    private static void renderDown(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, figure);
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
            }
            figure.setDirection("down");
            figure.setStep(speed / Figure.DEFAULT_COUNT);
            figure.setCount(Figure.DEFAULT_COUNT);
        }
    }

    private static void renderLeft(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, figure);
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
            }

            figure.setDirection("left");
            figure.setStep(speed / Figure.DEFAULT_COUNT);
            figure.setCount(Figure.DEFAULT_COUNT);
        }
    }

    private static void renderRight(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, figure);
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
            }

            figure.setDirection("right");
            figure.setStep(speed / Figure.DEFAULT_COUNT);
            figure.setCount(Figure.DEFAULT_COUNT);
        }
    }
}
