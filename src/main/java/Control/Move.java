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
        char cur = RunBomberman.objectMap[y][x];
        if (cur == Map.WALL || cur == Map.BRICK) {
            int realY = y * SCALED_SIZE;
            speed = figure.getY() - (realY + SCALED_SIZE);
        }

        figure.setY(figure.getY() - speed);
        figure.setDirection("Up");
        renderUp(figure);
    }

    private static void renderDown(Figure figure) {
        // Write code here
    }

    private static void moveDown(Figure figure) {
        // Write code here
    }

    private static void renderLeft(Figure figure) {
        // Write code here
    }

    private static void moveLeft(Figure figure) {
        // Write code here
    }

    private static void renderRight(Figure figure) {
        // Write code here
    }

    private static void moveRight(Figure figure) {
        // Write code here
    }
}
