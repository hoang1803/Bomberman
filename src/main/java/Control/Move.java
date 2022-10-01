package Control;

import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;

import static Graphics.Sprite.SCALED_SIZE;

public class Move {

    public static void setDirection(String direction, Figure figure) {
        switch (direction) {
            case "up":
                moveUp(figure);
                break;
            case "down":
                moveDown(figure);
                break;
            case "left":
                moveLeft(figure);
                break;
            case "right":
                moveRight(figure);
                break;
        }
    }

    private static void renderUp(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, figure);
        }

        // Write code here
    }

    private static void moveUp(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = (figure.getY() - speed) / SCALED_SIZE;
        int x = figure.getX() / SCALED_SIZE;
        int px = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[y][px];
        if (cur == Map.WALL || cur == Map.BRICK || cur2 == Map.WALL || cur2 == Map.BRICK) {
            int realY = y * SCALED_SIZE;
            speed = figure.getY() - (realY + SCALED_SIZE);
        }

        figure.setY(figure.getY() - speed);
        figure.setDirection("up");
        renderUp(figure);
    }

    private static void renderDown(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, figure);
        }

        // Write code here
    }

    private static void moveDown(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = (figure.getY() + speed + SCALED_SIZE - 1) / SCALED_SIZE;
        int x = figure.getX() / SCALED_SIZE;
        int px = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[y][px];
        if (cur == Map.WALL || cur == Map.BRICK || cur2 == Map.WALL || cur2 == Map.BRICK) {
            int realY = y * SCALED_SIZE;
            speed = (realY - 1) - (figure.getY() + SCALED_SIZE - 1);
        }

        figure.setY(figure.getY() + speed);
        figure.setDirection("down");
        renderDown(figure);
    }

    private static void renderLeft(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, figure);
        }

        // Write code here
    }

    private static void moveLeft(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = figure.getY() / SCALED_SIZE;
        int py = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
        int x = (figure.getX() - speed) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[py][x];
        if (cur == Map.WALL || cur == Map.BRICK || cur2 == Map.WALL || cur2 == Map.BRICK) {
            int realX = x * SCALED_SIZE;
            speed = figure.getX() - (realX + SCALED_SIZE);
        }

        figure.setX(figure.getX() - speed);
        figure.setDirection("left");
        renderLeft(figure);
    }

    private static void renderRight(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, figure);
        }

        // Write code here
    }

    private static void moveRight(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = figure.getY() / SCALED_SIZE;
        int py = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
        int x = (figure.getX() + speed + SCALED_SIZE - 1) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[py][x];
        if (cur == Map.WALL || cur == Map.BRICK || cur2 == Map.WALL || cur2 == Map.BRICK) {
            int realX = x * SCALED_SIZE;
            speed = (realX - 1) - (figure.getX() + SCALED_SIZE - 1);
        }

        figure.setX(figure.getX() + speed);
        figure.setDirection("right");
        renderRight(figure);
    }
}
