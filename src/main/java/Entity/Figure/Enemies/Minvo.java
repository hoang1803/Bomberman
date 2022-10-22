package Entity.Figure.Enemies;

import Entity.Figure.Figure;
import Graphics.Map;
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

    public static Figure createNewEnemy(int x, int y) {
        int rand = Enemy.RAND.nextInt(4);
        char c = (char) (rand + '1');
        Figure newEnemy = switch (c) {
            case Map.BALLOOM -> new Balloom(2, 1, 15, "up", 1);
            case Map.ONEAl -> new Oneal(2, 1, 15, "up", 1);
            case Map.DOLL -> new Doll(2, 1, 15, "up", 1);
            case Map.KONDORIA -> new Kondoria(2, 1, 15, "up", 1);
            default -> null;
        };
        newEnemy.setX(x);
        newEnemy.setY(y);
        return newEnemy;
    }
}
