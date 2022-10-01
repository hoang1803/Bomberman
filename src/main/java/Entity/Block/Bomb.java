package Entity.Block;

import Entity.Entity;
import Graphics.Sprite;
import static Graphics.Sprite.SCALED_SIZE;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Sound.Sound;
import javafx.scene.image.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import static GameRunner.RunBomberman.*;
import static GameRunner.RunBomberman.player;

public class Bomb extends Entity {
    protected static double timeToExplode = 120; //2 seconds
    public int timeAfter = 20;
    protected boolean hasExploded = false;
    protected static int bombStatic = 0; //0 no bomb  //1 had bomb  //-1 explosion
    protected static int bombNumber = 0; // chuyển sang quản lý lớp menu
    private static Entity bomb;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);

    }

    public static void putBomb() {      // The function used for the bomber to place the bomb
        if (bombStatic == 0 && bombNumber > 0) {
            new Sound("src/sound/....", "putBomb");
            bombNumber--;
            bombStatic = 1;
            timeToExplode = System.currentTimeMillis();
            int x = player.getX() / SCALED_SIZE;
            int y = player.getY() / SCALED_SIZE;
            x = Math.round((float) x);
            y = Math.round((float) y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            block.add(bomb);
        }

    }

    @Override
    public void update() {
        if(timeToExplode > 0) {
            timeToExplode--;
        }
        else {
            if (!hasExploded) {
                explode();

            } else
                updateFlames();

            if (timeAfter > 0)
                timeAfter--;
        }
    }

    public void renderFlames() {

    }

    public void updateFlames() {

    }

    public void explode() {
        Sound music = new Sound("explosion", "explosion");

    }
}
