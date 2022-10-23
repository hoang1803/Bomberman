package Control;

import Entity.Entity;

import static GameRunner.RunBomberman.*;
import static Graphics.Map.*;
import static Graphics.Sprite.SCALED_SIZE;

public class Blocked {
    public static boolean blockDownBomb(Entity entity, int power) {   //Limit the scope and animation of the explosion downward
        return objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == GRASS
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == BRICK
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == FLAME_ITEM
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == SPEED_ITEM
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == BOMB_ITEM
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == BALLOOM
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == ONEAl
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == DOLL
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == KONDORIA
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == MINVO
                || objectMap[entity.getY() / SCALED_SIZE + 1 + power][entity.getX() / SCALED_SIZE] == PLAYER;
    }

    public static boolean blockUpBomb(Entity entity, int power) {     //Limit the scope and animation of the explosion upward
        return objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == GRASS
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == BRICK
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == FLAME_ITEM
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == SPEED_ITEM
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == BOMB_ITEM
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == BALLOOM
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == ONEAl
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == DOLL
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == KONDORIA
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == MINVO
                || objectMap[entity.getY() / SCALED_SIZE - 1 - power][entity.getX() / SCALED_SIZE] == PLAYER;
    }

    public static boolean blockLeftBomb(Entity entity, int power) {   //Limit the scope and animation of the explosion to the left
        return objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == GRASS
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == BRICK
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == FLAME_ITEM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == SPEED_ITEM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == BOMB_ITEM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == BALLOOM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == ONEAl
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == DOLL
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == KONDORIA
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == MINVO
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE - 1 - power] == PLAYER;
    }

    public static boolean blockRightBomb(Entity entity, int power) {      //Limit the scope and animation of the explosion to the right
        return objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == GRASS
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == BRICK
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == FLAME_ITEM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == SPEED_ITEM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == BOMB_ITEM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == BALLOOM
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == ONEAl
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == DOLL
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == KONDORIA
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == MINVO
                || objectMap[entity.getY() / SCALED_SIZE][entity.getX() / SCALED_SIZE + 1 + power] == PLAYER;
    }
}