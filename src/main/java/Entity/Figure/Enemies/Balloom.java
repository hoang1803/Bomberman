package Entity.Figure.Enemies;

import Graphics.Sprite;
import javafx.scene.image.Image;


public class Balloom extends Enemy {

    public Balloom() {

    }

    public Balloom(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.balloom_left1.getFxImage());
    }

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.balloom_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }

    @Override
    public void update() {
        autoTransDirection();
        super.update();
    }
}
