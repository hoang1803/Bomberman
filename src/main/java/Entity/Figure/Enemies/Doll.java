package Entity.Figure.Enemies;

import Control.Move;
import Entity.Figure.Intelligent.AStar;
import Graphics.Sprite;
import javafx.scene.image.Image;



public class Doll extends Enemy {

    public Doll() {

    }

    public Doll(int currentSpeed, int currentFrame, int defaultDelayTime, String direction, int life) {
        super(currentSpeed, currentFrame, defaultDelayTime, direction, life);
        super.setImage(Sprite.doll_left1.getFxImage());
    }

    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }

    private void maxSpeed() {
        setCurrentSpeed(4);
    }

    private void normalSpeed() {
        setCurrentSpeed(2);
    }

    public void autoTransDirection() {
        String newDirection = AStar.getDirection(this);
        if (!newDirection.isEmpty()) {
            maxSpeed();
            boolean ok = switch (newDirection) {
                case "up" -> (Move.speedUp(this) > 0);
                case "down" -> (Move.speedDown(this) > 0);
                case "left" -> (Move.speedLeft(this) > 0);
                case "right" -> (Move.speedRight(this) > 0);
                default -> false;
            };
            if (ok && this.y % 4 == 0 && this.x % 4 == 0) {
                direction = newDirection;
                return;
            }
        }
        normalSpeed();
        super.autoTransDirection();
    }

    @Override
    public void update() {
        this.autoTransDirection();
        super.update();
    }

    @Override
    protected void kill() {
        this.setImage(Sprite.doll_dead.getFxImage());
        life = -1;
        this.setDelayTime(5);
    }
}
