package Entity.Figure.Enemies;

import Graphics.Sprite;
import javafx.scene.image.Image;

public class Minvo extends Enemy {

    private int timeLeftTransDirect = 0;

    private int timeLeftTransSpeed = 0;


    public Minvo() {

    }

    public Minvo(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.minvo_left1.getFxImage());
    }

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        autoTransDirection();
        super.update();
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.minvo_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }
}
