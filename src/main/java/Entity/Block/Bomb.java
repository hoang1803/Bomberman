package Entity.Block;

import Control.Blocked;
import Entity.Entity;
import Graphics.Sprite;

import static Graphics.Map.BOMB;
import static Graphics.Map.GRASS;
import static Graphics.Sprite.SCALED_SIZE;

import java.util.ArrayList;
import java.util.List;

import Sound.Sound;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;
import static GameRunner.RunBomberman.player;

public class Bomb extends Entity {
    protected static double timeToExplode = 120; //2 seconds
    protected static double waitTime = 20;
    protected static int bombStatic = 0; //0 no bomb  //1 had bomb  //-1 explosion
    protected static int bombNumber = 20; // chuyen sang quan ly o lop control/menu
    private static final List<Entity> listBombX = new ArrayList<>();
    private static final List<Entity> listBombY = new ArrayList<>();
    public static int range = 0; // bomb's extra explosive range
    private static Entity bomb;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    // 0-left // 1-right // 2-up // 3-down
    private static int[] bombPower = {0, 0, 0, 0};         // Bomb's destructive power
    private static Entity[] edgeBlocked = new Entity[4];   // The edge of the block blocks the character from going through
    private static boolean isEdge = false;     // Check if that edge exists
    private static boolean isMiddle = true;   // Check if the bomb explodes in the center (plus sign, not T )
    private static int swapExplosion = 1;     // Show flame's animation

    public Bomb(int x, int y, Image img) {
        super(x, y, img);

    }

    public static void putBomb() {      // The function used for the bomber to place the bomb
        if (bombStatic == 0 && bombNumber > 0) {
            //new Sound("sound/put_bombs.wav", "putBomb");
            bombNumber--;
            bombStatic = 1;
            timeToExplode = System.currentTimeMillis();
            waitTime = timeToExplode;
            int x = player.getX() / SCALED_SIZE;
            int y = player.getY() / SCALED_SIZE;
            x = Math.round((float) x);
            y = Math.round((float) y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            bomb.setCurrentFrame(1);
            block.add(bomb);
            objectMap[y][x] = BOMB;
        }

    }

    public static void createEdge() {   // Create an edge to prevent the character's movement as well as the explosion range of the bomb
        if (Blocked.blockDownBomb(bomb, 0)) {
            edgeBlocked[DOWN] = new Bomb(bomb.getX() / SCALED_SIZE, bomb.getY() / SCALED_SIZE + 1, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockDownBomb(bomb, i); ++i) {
                    edgeBlocked[DOWN].setY(bomb.getY() + (i + 1) * SCALED_SIZE);
                    ++bombPower[DOWN];
                }
            }
            block.add(edgeBlocked[DOWN]);
        }

        if (Blocked.blockUpBomb(bomb, 0)) {
            edgeBlocked[UP] = new Bomb(bomb.getX() / SCALED_SIZE, bomb.getY() / SCALED_SIZE - 1, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockUpBomb(bomb, i); ++i) {
                    edgeBlocked[UP].setY(bomb.getY() - (i + 1) * SCALED_SIZE);
                    ++bombPower[UP];
                }
            }
            block.add(edgeBlocked[UP]);
        }

        if (Blocked.blockLeftBomb(bomb, 0)) {
            edgeBlocked[LEFT] = new Bomb(bomb.getX() / SCALED_SIZE - 1, bomb.getY() / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockLeftBomb(bomb, i); ++i) {
                    edgeBlocked[LEFT].setX(bomb.getX() - (i + 1) * SCALED_SIZE);
                    ++bombPower[LEFT];
                }
            }
            block.add(edgeBlocked[LEFT]);
        }

        if (Blocked.blockRightBomb(bomb, 0)) {
            edgeBlocked[RIGHT] = new Bomb(bomb.getX() / SCALED_SIZE + 1, bomb.getY() / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockRightBomb(bomb, i); ++i) {
                    edgeBlocked[RIGHT].setX(bomb.getX() + (i + 1) * SCALED_SIZE);
                    ++bombPower[RIGHT];
                }
            }
            block.add(edgeBlocked[RIGHT]);
        }

    }

    public static void createMiddle() {     // Adjust the bomb to explode at the center position
        Entity middle;
        for (int i = 1; i <= bombPower[DOWN]; i++) {
            middle = new Bomb(bomb.getX() / SCALED_SIZE, bomb.getY() / SCALED_SIZE + i, Sprite.bomb_exploded.getFxImage());
            listBombY.add(middle);
        }

        for (int i = 1; i <= bombPower[UP]; i++) {
            middle = new Bomb(bomb.getX() / SCALED_SIZE, bomb.getY() / SCALED_SIZE - i, Sprite.bomb_exploded.getFxImage());
            listBombY.add(middle);
        }

        for (int i = 1; i <= bombPower[LEFT]; i++) {
            middle = new Bomb(bomb.getX() / SCALED_SIZE - i, bomb.getY() / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            listBombX.add(middle);
        }

        for (int i = 1; i <= bombPower[RIGHT]; i++) {
            middle = new Bomb(bomb.getX() / SCALED_SIZE + i, bomb.getY() / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            listBombX.add(middle);
        }
        block.addAll(listBombX);
        block.addAll(listBombY);
    }

    public static void explosionCenter() {      // Determine the explosion center of the bomb
        if (swapExplosion == 1) {
            bomb.setImage(Sprite.bomb_exploded.getFxImage());
            killObject[bomb.getY() / SCALED_SIZE][bomb.getX() / SCALED_SIZE] = 4;
            if (Blocked.blockDownBomb(bomb, bombPower[DOWN])) {
                edgeBlocked[DOWN].setImage(Sprite.explosion_vertical_down_last.getFxImage());
                killObject[edgeBlocked[DOWN].getY() / SCALED_SIZE][edgeBlocked[DOWN].getX() / SCALED_SIZE] = 4;
            }

            if (Blocked.blockUpBomb(bomb, bombPower[UP])) {
                edgeBlocked[UP].setImage(Sprite.explosion_vertical_top_last.getFxImage());
                killObject[edgeBlocked[UP].getY() / SCALED_SIZE][edgeBlocked[UP].getX() / SCALED_SIZE] = 4;
            }

            if (Blocked.blockLeftBomb(bomb, bombPower[LEFT])) {
                edgeBlocked[LEFT].setImage(Sprite.explosion_horizontal_left_last.getFxImage());
                killObject[edgeBlocked[LEFT].getY() / SCALED_SIZE][edgeBlocked[LEFT].getX() / SCALED_SIZE] = 4;
            }

            if (Blocked.blockRightBomb(bomb, bombPower[RIGHT])) {
                edgeBlocked[RIGHT].setImage(Sprite.explosion_horizontal_right_last.getFxImage());
                killObject[edgeBlocked[RIGHT].getY() / SCALED_SIZE][edgeBlocked[RIGHT].getX() / SCALED_SIZE] = 4;
            }

            if (listBombY.size() > 0) {
                for (Entity e : listBombY) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                    killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = 4;
                }
            }

            if (listBombX.size() > 0) {
                for (Entity e : listBombX) {
                    e.setImage(Sprite.explosion_horizontal.getFxImage());
                    killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = 4;
                }
            }
            swapExplosion = 2;
        } else if (swapExplosion == 2) {
            bomb.setImage(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.blockDownBomb(bomb, bombPower[DOWN])) {
                edgeBlocked[DOWN].setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            }

            if (Blocked.blockUpBomb(bomb, bombPower[UP])) {
                edgeBlocked[UP].setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            }

            if (Blocked.blockLeftBomb(bomb, bombPower[LEFT])) {
                edgeBlocked[LEFT].setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
            }

            if (Blocked.blockRightBomb(bomb, bombPower[RIGHT])) {
                edgeBlocked[RIGHT].setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
            }

            if (isMiddle) {
                for (Entity e : listBombY) {
                    e.setImage(Sprite.explosion_vertical1.getFxImage());
                }
                for (Entity e : listBombX) {
                    e.setImage(Sprite.explosion_horizontal1.getFxImage());
                }
            }
            swapExplosion = 3;
        }
        else if (swapExplosion == 3) {
            bomb.setImage(Sprite.bomb_exploded2.getFxImage());
            if (Blocked.blockDownBomb(bomb, bombPower[DOWN])) {
                edgeBlocked[DOWN].setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (Blocked.blockUpBomb(bomb, bombPower[UP])) {
                edgeBlocked[UP].setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (Blocked.blockLeftBomb(bomb, bombPower[LEFT])) {
                edgeBlocked[LEFT].setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (Blocked.blockRightBomb(bomb, bombPower[RIGHT])) {
                edgeBlocked[RIGHT].setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
            }

            if (isMiddle) {
                for (Entity e : listBombY) {
                    e.setImage(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : listBombX) {
                    e.setImage(Sprite.explosion_horizontal2.getFxImage());
                }
            }
            swapExplosion = 1;
        }

    }

    private static void checkActive() {     // Check what stages the bomb has gone through before detonating
        if (bombStatic == 1) {
            if (System.currentTimeMillis() - timeToExplode < 2000L) {
                if (System.currentTimeMillis() - waitTime > 100L) {
                    Sprite.renderSpriteBomb(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, bomb);
                    waitTime += 100L;
                }
            }
            else {
                bombStatic = -1;
                timeToExplode = System.currentTimeMillis();
                waitTime = timeToExplode;
            }
        }

    }

    private static void checkExplosion() {      // Check the bomb's detonation time after the bomb is activated
        if (bombStatic == -1) {
            if (System.currentTimeMillis() - timeToExplode < 1000L) {
                if (System.currentTimeMillis() - waitTime > 100L) {
                    if (!isEdge) {
                        createEdge();
                        isEdge = true;
                    }

                    if (range > 0 && !isMiddle) {
                        createMiddle();
                        isMiddle = true;
                    }

                    //new Sound("sound/bomb_explosion.wav", "explosion");
                    explosionCenter();
                    waitTime += 100L;
                }
            }
            else {
                bombStatic = 0;
                objectMap[bomb.getY() / SCALED_SIZE][bomb.getX() / SCALED_SIZE] = GRASS;
                killObject[bomb.getY() / SCALED_SIZE][bomb.getX() / SCALED_SIZE] = 0;
                bomb.setImage(Sprite.transparent.getFxImage());
                if (Blocked.blockDownBomb(bomb, bombPower[DOWN])) {
                    edgeBlocked[DOWN].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[DOWN].getY() / SCALED_SIZE][edgeBlocked[DOWN].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[DOWN].getY() / SCALED_SIZE][edgeBlocked[DOWN].getX() / SCALED_SIZE] = 0;
                }

                if (Blocked.blockUpBomb(bomb, bombPower[UP])) {
                    edgeBlocked[UP].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[UP].getY() / SCALED_SIZE][edgeBlocked[UP].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[UP].getY() / SCALED_SIZE][edgeBlocked[UP].getX() / SCALED_SIZE] = 0;
                }

                if (Blocked.blockLeftBomb(bomb, bombPower[LEFT])) {
                    edgeBlocked[LEFT].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[LEFT].getY() / SCALED_SIZE][edgeBlocked[LEFT].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[LEFT].getY() / SCALED_SIZE][edgeBlocked[LEFT].getX() / SCALED_SIZE] = 0;
                }

                if (Blocked.blockRightBomb(bomb, bombPower[RIGHT])) {
                    edgeBlocked[RIGHT].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[RIGHT].getY() / SCALED_SIZE][edgeBlocked[RIGHT].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[RIGHT].getY() / SCALED_SIZE][edgeBlocked[RIGHT].getX() / SCALED_SIZE] = 0;
                }

                if (isMiddle) {
                    for (Entity e : listBombX) {
                        killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = 0;
                        objectMap[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = GRASS;
                    }
                    for (Entity e : listBombY) {
                        killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = 0;
                        objectMap[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = GRASS;
                    }
                }

                block.removeAll(listBombY);
                block.removeAll(listBombX);
                listBombY.clear();
                listBombX.clear();
                isEdge = false;
                isMiddle = false;
                bombPower[DOWN] = 0;
                bombPower[UP] = 0;
                bombPower[LEFT] = 0;
                bombPower[RIGHT] = 0;
            }
        }

    }

    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}