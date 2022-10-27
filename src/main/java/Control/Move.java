package Control;

import Entity.Figure.Bomber;
import Entity.Figure.Enemies.*;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;


import static Graphics.Sprite.*;

public class Move {
    public static void figureRun(Figure figure) {
        int count = figure.getCount();
        if(count > 0) {
            figure.setCount(count - 1);

            if (figure instanceof Bomber) {
                setDirection(figure);
            }

            if (figure instanceof Enemy) {
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
                    default -> {

                    }
                }
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
            default -> {

            }
        }
    }

    public static boolean hasBlock(char current) {
        return (current == Map.WALL || current == Map.BRICK || current == Map.BOMB ||
                current == Map.BOMB_ITEM || current == Map.FLAME_ITEM || current == Map.SPEED_ITEM);
    }

    private static void renderUp(Figure figure) {
        if (figure instanceof Bomber) {
            Sprite.renderSpriteForBomber(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, figure);
        }

        if (figure instanceof Balloom) {
            Sprite.renderSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, figure);
        }

        if (figure instanceof Oneal) {
            Sprite.renderSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, figure);
        }

        if (figure instanceof Doll) {
            Sprite.renderSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, figure);
        }

        if (figure instanceof Kondoria) {
            Sprite.renderSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, figure);
        }

        if (figure instanceof Minvo) {
            Sprite.renderSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, figure);
        }

        // Write code here
    }

    public static int speedUp(Figure figure) {
        int speed = figure.getCurrentSpeed();

        int y = (figure.getY() - speed) / SCALED_SIZE;
        int x = figure.getX() / SCALED_SIZE;
        int px = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[y][px];

        if (hasBlock(cur) || hasBlock(cur2)) {
            if (figure instanceof Kondoria
                    && Kondoria.canGoThrough(cur) && Kondoria.canGoThrough(cur2)) {
                return speed;
            } else {
                speed = Math.min(speed, figure.getY() % SCALED_SIZE);
                if (figure instanceof Enemy) {
                    y = figure.getY() / SCALED_SIZE;
                    cur = RunBomberman.objectMap[y][x];
                    cur2 = RunBomberman.objectMap[y][px];
                    if (cur == Map.BOMB || cur2 == Map.BOMB) {
                        speed = 0;
                    }
                }
            }
        }

        return speed;
    }

    public static void moveUp(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = speedUp(figure);

            if (speed == 0) {
                if (figure instanceof Enemy) {
                    ((Enemy) figure).setTransDirection(true);
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
            Sprite.renderSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, figure);
        }

        if (figure instanceof Oneal) {
            Sprite.renderSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, figure);
        }

        if (figure instanceof Doll) {
            Sprite.renderSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, figure);
        }

        if (figure instanceof Kondoria) {
            Sprite.renderSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, figure);
        }

        if (figure instanceof Minvo) {
            Sprite.renderSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, figure);
        }

        // Write code here
    }

    public static int speedDown(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = (figure.getY() + speed + SCALED_SIZE - 1) / SCALED_SIZE;
        int x = figure.getX() / SCALED_SIZE;
        int px = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[y][px];

        if (hasBlock(cur) || hasBlock(cur2)) {
            if (figure instanceof Kondoria
                    && Kondoria.canGoThrough(cur) && Kondoria.canGoThrough(cur2)) {
                return speed;
            } else {
                speed = Math.min(speed, SCALED_SIZE - ((figure.getY() + SCALED_SIZE - 1) % SCALED_SIZE + 1));

                if (figure instanceof Enemy) {
                    y = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
                    cur = RunBomberman.objectMap[y][x];
                    cur2 = RunBomberman.objectMap[y][px];
                    if (cur == Map.BOMB || cur2 == Map.BOMB) {
                        speed = 0;
                    }
                }
            }
        }
        return speed;
    }

    public static void moveDown(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = speedDown(figure);

            if (speed == 0) {
                if (figure instanceof Enemy) {
                    ((Enemy) figure).setTransDirection(true);
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
            Sprite.renderSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, figure);
        }

        if (figure instanceof Oneal) {
            Sprite.renderSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, figure);
        }


        if (figure instanceof Doll) {
            Sprite.renderSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, figure);
        }

        if (figure instanceof Kondoria) {
            Sprite.renderSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, figure);
        }

        if (figure instanceof Minvo) {
            Sprite.renderSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, figure);
        }
        // Write code here
    }

    public static int speedLeft(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = figure.getY() / SCALED_SIZE;
        int py = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
        int x = (figure.getX() - speed) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[py][x];
        if (hasBlock(cur) || hasBlock(cur2)) {
            if (figure instanceof Kondoria
                    && Kondoria.canGoThrough(cur) && Kondoria.canGoThrough(cur2)) {
                return speed;
            } else {
                speed = Math.min(speed, figure.getX() % SCALED_SIZE);
                if (figure instanceof Enemy) {
                    x = figure.getX() / SCALED_SIZE;
                    cur = RunBomberman.objectMap[y][x];
                    cur2 = RunBomberman.objectMap[py][x];
                    if (cur == Map.BOMB || cur2 == Map.BOMB) {
                        speed = 0;
                    }
                }
            }
        }
        return speed;
    }

    public static void moveLeft(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = speedLeft(figure);

            if (speed == 0) {
                if (figure instanceof Enemy) {
                    ((Enemy) figure).setTransDirection(true);
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
            Sprite.renderSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, figure);
        }

        if (figure instanceof Oneal) {
            Sprite.renderSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, figure);
        }

        if (figure instanceof Doll) {
            Sprite.renderSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, figure);
        }

        if (figure instanceof Kondoria) {
            Sprite.renderSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, figure);
        }

        if (figure instanceof Minvo) {
            Sprite.renderSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, figure);
        }

        // Write code here
    }

    public static int speedRight(Figure figure) {
        int speed = figure.getCurrentSpeed();
        int y = figure.getY() / SCALED_SIZE;
        int py = (figure.getY() + SCALED_SIZE - 1) / SCALED_SIZE;
        int x = (figure.getX() + speed + SCALED_SIZE - 1) / SCALED_SIZE;
        char cur = RunBomberman.objectMap[y][x];
        char cur2 = RunBomberman.objectMap[py][x];
        if (hasBlock(cur) || hasBlock(cur2)) {
            if (figure instanceof Kondoria
                    && Kondoria.canGoThrough(cur) && Kondoria.canGoThrough(cur2)) {
                return speed;
            } else {
                speed = Math.min(speed, SCALED_SIZE - ((figure.getX() + SCALED_SIZE - 1) % SCALED_SIZE + 1));
                x = (figure.getX() + SCALED_SIZE - 1) / SCALED_SIZE;
                cur = RunBomberman.objectMap[y][x];
                cur2 = RunBomberman.objectMap[py][x];
                if (cur == Map.BOMB || cur2 == Map.BOMB) {
                    speed = 0;
                }
            }
        }
        return speed;
    }

    public static void moveRight(Figure figure) {
        if(figure.getCount() == 0) {
            int speed = speedRight(figure);

            if (speed == 0) {
                if (figure instanceof Enemy) {
                    ((Enemy) figure).setTransDirection(true);
                }
            }
            figure.setDirection("right");
            figure.setStep(speed / figure.getDefaultCount());
            figure.setCount(figure.getDefaultCount());
        }
    }
}