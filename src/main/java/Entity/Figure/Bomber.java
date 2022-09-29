package Entity.Figure;

import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.player;

public class Bomber extends Figure {
    public int deadFrame = 1;
    private int countDead = 0;
    private boolean isDead = false;

    public Bomber() {

    }

    public Bomber(int currentSpeed, int currentFrame, String direction, int life) {
        super(currentSpeed, currentFrame, direction, life);
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    private void killBomber(Figure figure) {
        //Write code here

    }

    private void checkEnemy() {
        int pX = player.getX();
        int pY = player.getY();
        int esp = 5;
        for(Figure figure : enemy) {
            int fX = figure.getX();
            int fY = figure.getY();

            if(Math.abs(pX - fX) <= esp &&
                fY - Sprite.SCALED_SIZE <= pY && pY <= fY + Sprite.SCALED_SIZE) {
                isDead = true;
                break;
            }

            if(Math.abs(pY - fY) <= esp &&
                    fX - Sprite.SCALED_SIZE <= pX && pX <= fX + Sprite.SCALED_SIZE) {
                isDead = true;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkEnemy();

        if (isDead) {
            player.life--;
            killBomber(player);
            isDead = false;
        }

        countDead++;
    }
}
