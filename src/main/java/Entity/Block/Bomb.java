package Entity.Block;

import Control.Blocked;
import Control.Move;
import Entity.Entity;
import Graphics.Sprite;

import static Graphics.Map.BOMB;
import static Graphics.Map.GRASS;
import static Graphics.Sprite.SCALED_SIZE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Sound.Sound;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;
import static GameRunner.RunBomberman.player;

public class Bomb extends Entity {
    protected double timeToExplode = 120; //2 seconds
    protected double waitTime = 20;
    protected int bombStatic = 0; //0 no bomb  //1 had bomb  //-1 explosion
    public static int bombNumber = 20; // chuyen sang quan ly o lop control/menu
    public static int countBomb = 1;
    private final List<Entity> listBombX = new ArrayList<>();
    private final List<Entity> listBombY = new ArrayList<>();
    public static int range = 0; // bomb's extra explosive range
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    // 0-left // 1-right // 2-up // 3-down
    private int[] bombPower = {0, 0, 0, 0};         // Bomb's destructive power
    private Entity[] edgeBlocked = new Entity[4];   // The edge of the block blocks the character from going through
    private boolean isEdge = false;     // Check if that edge exists
    private boolean isMiddle = false;   // Check if the bomb explodes in the center (plus sign, not T )
    private int swapExplosion = 1;     // Show flame's animation
    private int counterKill = 0;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);

    }

    public Bomb() {
        new Sound("putBombs.wav", "putBomb");
        bombNumber--;
        bombStatic = 1;
        timeToExplode = System.currentTimeMillis();
        waitTime = timeToExplode;
        countBomb--;
    }
    public static void putBomb() {      // The function used for the bomber to place the bomb

        int x = player.getX() / SCALED_SIZE;
        int y = player.getY() / SCALED_SIZE;
        x = Math.round((float) x);
        y = Math.round((float) y);

        if (Move.hasBlock(objectMap[y][x])) {

            return;
        }

        Bomb bomb = new Bomb();
        bomb.setX(x * SCALED_SIZE);
        bomb.setY(y * SCALED_SIZE);
        bomb.setImage(Sprite.bomb.getFxImage());
        bomb.setCurrentFrame(1);
        block.add(bomb);
        objectMap[y][x] = BOMB;

    }

    public void createEdge() {   // Create an edge to prevent the character's movement as well as the explosion range of the this
        if (Blocked.blockDownBomb(this, 0)) {
            edgeBlocked[DOWN] = new Bomb(x / SCALED_SIZE, y / SCALED_SIZE + 1, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockDownBomb(this, i); ++i) {
                    edgeBlocked[DOWN].setY(y + (i + 1) * SCALED_SIZE);
                    ++bombPower[DOWN];
                }
            }
            block.add(edgeBlocked[DOWN]);
        }

        if (Blocked.blockUpBomb(this, 0)) {
            edgeBlocked[UP] = new Bomb(x / SCALED_SIZE, y / SCALED_SIZE - 1, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockUpBomb(this, i); ++i) {
                    edgeBlocked[UP].setY(y - (i + 1) * SCALED_SIZE);
                    ++bombPower[UP];
                }
            }
            block.add(edgeBlocked[UP]);
        }

        if (Blocked.blockLeftBomb(this, 0)) {
            edgeBlocked[LEFT] = new Bomb(x / SCALED_SIZE - 1, y / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockLeftBomb(this, i); ++i) {
                    edgeBlocked[LEFT].setX(x - (i + 1) * SCALED_SIZE);
                    ++bombPower[LEFT];
                }
            }
            block.add(edgeBlocked[LEFT]);
        }

        if (Blocked.blockRightBomb(this, 0)) {
            edgeBlocked[RIGHT] = new Bomb(x / SCALED_SIZE + 1, y / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            if (range > 0) {
                for (int i = 1; i <= range && Blocked.blockRightBomb(this, i); ++i) {
                    edgeBlocked[RIGHT].setX(x + (i + 1) * SCALED_SIZE);
                    ++bombPower[RIGHT];
                }
            }
            block.add(edgeBlocked[RIGHT]);
        }

    }

    public void createMiddle() {     // Adjust the bomb to explode at the center position
        Entity middle;
        for (int i = 1; i <= bombPower[DOWN]; i++) {
            middle = new Bomb(x / SCALED_SIZE, y / SCALED_SIZE + i, Sprite.bomb_exploded.getFxImage());
            listBombY.add(middle);
        }

        for (int i = 1; i <= bombPower[UP]; i++) {
            middle = new Bomb(x / SCALED_SIZE, y / SCALED_SIZE - i, Sprite.bomb_exploded.getFxImage());
            listBombY.add(middle);
        }

        for (int i = 1; i <= bombPower[LEFT]; i++) {
            middle = new Bomb(x / SCALED_SIZE - i, y / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            listBombX.add(middle);
        }

        for (int i = 1; i <= bombPower[RIGHT]; i++) {
            middle = new Bomb(x / SCALED_SIZE + i, y / SCALED_SIZE, Sprite.bomb_exploded.getFxImage());
            listBombX.add(middle);
        }
        block.addAll(listBombX);
        block.addAll(listBombY);
        System.out.println("UP: " + bombPower[UP]);
        System.out.println("DOWN: " + bombPower[DOWN]);
        System.out.println("LEFT: " + bombPower[LEFT]);
        System.out.println("RIGHT: " + bombPower[RIGHT]);
    }

    public void explosionCenter() {      // Determine the explosion center of the bomb
        if (swapExplosion == 1) {
            this.setImage(Sprite.bomb_exploded.getFxImage());
            killObject[y / SCALED_SIZE][x / SCALED_SIZE]++;
            counterKill++;
            if (Blocked.blockDownBomb(this, bombPower[DOWN])) {
                edgeBlocked[DOWN].setImage(Sprite.explosion_vertical_down_last.getFxImage());
                killObject[edgeBlocked[DOWN].getY() / SCALED_SIZE][edgeBlocked[DOWN].getX() / SCALED_SIZE]++;
            }

            if (Blocked.blockUpBomb(this, bombPower[UP])) {
                edgeBlocked[UP].setImage(Sprite.explosion_vertical_top_last.getFxImage());
                killObject[edgeBlocked[UP].getY() / SCALED_SIZE][edgeBlocked[UP].getX() / SCALED_SIZE]++;
            }

            if (Blocked.blockLeftBomb(this, bombPower[LEFT])) {
                edgeBlocked[LEFT].setImage(Sprite.explosion_horizontal_left_last.getFxImage());
                killObject[edgeBlocked[LEFT].getY() / SCALED_SIZE][edgeBlocked[LEFT].getX() / SCALED_SIZE]++;
            }

            if (Blocked.blockRightBomb(this, bombPower[RIGHT])) {
                edgeBlocked[RIGHT].setImage(Sprite.explosion_horizontal_right_last.getFxImage());
                killObject[edgeBlocked[RIGHT].getY() / SCALED_SIZE][edgeBlocked[RIGHT].getX() / SCALED_SIZE]++;
            }

            if (listBombY.size() > 0) {
                for (Entity e : listBombY) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                    killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE]++;
                }
            }

            if (listBombX.size() > 0) {
                for (Entity e : listBombX) {
                    e.setImage(Sprite.explosion_horizontal.getFxImage());
                    killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE]++;
                }
            }
            swapExplosion = 2;
        } else if (swapExplosion == 2) {
            this.setImage(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.blockDownBomb(this, bombPower[DOWN])) {
                edgeBlocked[DOWN].setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            }

            if (Blocked.blockUpBomb(this, bombPower[UP])) {
                edgeBlocked[UP].setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            }

            if (Blocked.blockLeftBomb(this, bombPower[LEFT])) {
                edgeBlocked[LEFT].setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
            }

            if (Blocked.blockRightBomb(this, bombPower[RIGHT])) {
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
            this.setImage(Sprite.bomb_exploded2.getFxImage());
            if (Blocked.blockDownBomb(this, bombPower[DOWN])) {
                edgeBlocked[DOWN].setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (Blocked.blockUpBomb(this, bombPower[UP])) {
                edgeBlocked[UP].setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (Blocked.blockLeftBomb(this, bombPower[LEFT])) {
                edgeBlocked[LEFT].setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (Blocked.blockRightBomb(this, bombPower[RIGHT])) {
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

    private void checkActive() {     // Check what stages the bomb has gone through before detonating
        if (bombStatic == 1) {
            if (System.currentTimeMillis() - timeToExplode < 2000L) {
                if (System.currentTimeMillis() - waitTime > 100L) {
                    Sprite.renderSpriteBomb(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, this);
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

    private void checkExplosion() {      // Check the bomb's detonation time after the bomb is activated
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

                    new Sound("bombExplosion.wav", "explosion");
                    explosionCenter();
                    waitTime += 100L;
                }
            }
            else {
                bombStatic = 0;
                objectMap[y / SCALED_SIZE][x / SCALED_SIZE] = GRASS;
                killObject[y / SCALED_SIZE][x / SCALED_SIZE] -= counterKill;
                this.setImage(Sprite.transparent.getFxImage());
                if (Blocked.blockDownBomb(this, bombPower[DOWN])) {
                    edgeBlocked[DOWN].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[DOWN].getY() / SCALED_SIZE][edgeBlocked[DOWN].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[DOWN].getY() / SCALED_SIZE][edgeBlocked[DOWN].getX() / SCALED_SIZE] -= counterKill;
                }

                if (Blocked.blockUpBomb(this, bombPower[UP])) {
                    edgeBlocked[UP].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[UP].getY() / SCALED_SIZE][edgeBlocked[UP].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[UP].getY() / SCALED_SIZE][edgeBlocked[UP].getX() / SCALED_SIZE] -= counterKill;
                }

                if (Blocked.blockLeftBomb(this, bombPower[LEFT])) {
                    edgeBlocked[LEFT].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[LEFT].getY() / SCALED_SIZE][edgeBlocked[LEFT].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[LEFT].getY() / SCALED_SIZE][edgeBlocked[LEFT].getX() / SCALED_SIZE] -= counterKill;
                }

                if (Blocked.blockRightBomb(this, bombPower[RIGHT])) {
                    edgeBlocked[RIGHT].setImage(Sprite.transparent.getFxImage());
                    objectMap[edgeBlocked[RIGHT].getY() / SCALED_SIZE][edgeBlocked[RIGHT].getX() / SCALED_SIZE] = GRASS;
                    killObject[edgeBlocked[RIGHT].getY() / SCALED_SIZE][edgeBlocked[RIGHT].getX() / SCALED_SIZE] -= counterKill;
                }

                if (isMiddle) {
                    for (Entity e : listBombX) {
                        killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] -= counterKill;
                        objectMap[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = GRASS;
                    }
                    for (Entity e : listBombY) {
                        killObject[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] -= counterKill;
                        objectMap[e.getY() / SCALED_SIZE][e.getX() / SCALED_SIZE] = GRASS;
                    }
                }

                block.removeAll(listBombY);
                block.removeAll(listBombX);

//                listBombY.clear();
//                listBombX.clear();
//                isEdge = false;
//                isMiddle = false;
//                bombPower[DOWN] = 0;
//                bombPower[UP] = 0;
//                bombPower[LEFT] = 0;
//                bombPower[RIGHT] = 0;

                countBomb++;
                System.err.println(Arrays.toString(bombPower));
                block.remove(this);
            }
        }

    }

    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}