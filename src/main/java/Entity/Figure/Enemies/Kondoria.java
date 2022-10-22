package Entity.Figure.Enemies;

import Graphics.Map;
import Graphics.Sprite;
import javafx.scene.image.Image;

public class Kondoria extends Enemy {

    private int timeLeftTransDirect = 0;

    private int timeLeftTransSpeed = 0;


    public Kondoria() {

    }

    public Kondoria(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.kondoria_left1.getFxImage());
    }

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
    }

    public static boolean canGoThrough(char c) {
        return (c != Map.WALL) && (c != Map.BOMB);
    }
    @Override
    public void update() {
        super.autoTransDirection();
        super.update();
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.kondoria_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }
}
