package Control;

import Entity.Entity;

import static GameRunner.RunBomberman.*;
import static Graphics.Map.*;
import static Graphics.Sprite.SCALED_SIZE;

public class Blocked {
    public static boolean blockDownBomb(Entity entity, int power) {   //Limit the scope and animation of the explosion downward
        return objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE + 1 + power] == GRASS
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE + 1 + power] == BRICK
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE + 1 + power] == FLAME_ITEM
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE + 1 + power] == SPEED_ITEM
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE + 1 + power] == PLAYER;
    }

    public static boolean blockUpBomb(Entity entity, int power) {     //Limit the scope and animation of the explosion upward
        return objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE - 1 - power] == GRASS
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE - 1 - power] == BRICK
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE - 1 - power] == FLAME_ITEM
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE - 1 - power] == SPEED_ITEM
                || objectMap[entity.getX() / SCALED_SIZE][entity.getY() / SCALED_SIZE - 1 - power] == PLAYER;
    }

    public static boolean blockLeftBomb(Entity entity, int power) {   //Limit the scope and animation of the explosion to the left
        return objectMap[entity.getX() / SCALED_SIZE - 1 - power][entity.getY() / SCALED_SIZE] == GRASS
                || objectMap[entity.getX() / SCALED_SIZE - 1 - power][entity.getY() / SCALED_SIZE] == BRICK
                || objectMap[entity.getX() / SCALED_SIZE - 1 - power][entity.getY() / SCALED_SIZE] == FLAME_ITEM
                || objectMap[entity.getX() / SCALED_SIZE - 1 - power][entity.getY() / SCALED_SIZE] == SPEED_ITEM
                || objectMap[entity.getX() / SCALED_SIZE - 1 - power][entity.getY() / SCALED_SIZE] == PLAYER;
    }

    public static boolean blockRightBomb(Entity entity, int power) {      //Limit the scope and animation of the explosion to the right
        return objectMap[entity.getX() / SCALED_SIZE + 1 + power][entity.getY() / SCALED_SIZE] == GRASS
                || objectMap[entity.getX() / SCALED_SIZE + 1 + power][entity.getY() / SCALED_SIZE] == BRICK
                || objectMap[entity.getX() / SCALED_SIZE + 1 + power][entity.getY() / SCALED_SIZE] == FLAME_ITEM
                || objectMap[entity.getX() / SCALED_SIZE + 1 + power][entity.getY() / SCALED_SIZE] == SPEED_ITEM
                || objectMap[entity.getX() / SCALED_SIZE + 1 + power][entity.getY() / SCALED_SIZE] == PLAYER;
    }
}