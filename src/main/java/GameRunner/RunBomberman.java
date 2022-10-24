package GameRunner;

import Control.Move;
import Entity.Block.Bomb;
import Entity.Entity;
import Entity.Figure.Bomber;
import Entity.Figure.Enemies.Minvo;
import Entity.Figure.Figure;
import Graphics.Map;
import Graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Sound.Sound.updateSound;

public class RunBomberman extends Application {
    public static final int HEIGHT = 13;
    public static final int WIDTH = 31;

    public static int level = 1;
    public static List<Figure> enemy = new ArrayList<>();
    public static List<Entity> block = new ArrayList<>();
    public static List<Figure> enemyDead = new ArrayList<>(); // enemy chết
    public static Figure player;
    public static char[][] objectMap; // Include: WALL, BRICK, PORTAL, BOMB, GRASS
    public static int[][] killObject; // Bomb sau khi nổ thì lưu vết lửa vẫn còn ở đây.
    public static Map map;

    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean isPause = false;

    public static boolean running = true;

    public static Stage stage = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);


        gc = canvas.getGraphicsContext2D();
        Group group = new Group();
        group.getChildren().add(canvas);

        Scene scene = new Scene(group);

        map = new Map(level);
        scene.setOnKeyPressed(keyEvent -> {
            if (player.getLife() > 0) {
                switch (keyEvent.getCode()) {
                    case UP -> Move.moveUp(player);
                    case DOWN -> Move.moveDown(player);
                    case LEFT -> Move.moveLeft(player);
                    case RIGHT -> Move.moveRight(player);
                    case SPACE -> {
                        if (Bomb.countBomb != 0 && Bomb.bombNumber > 0) {
                            Bomb.putBomb();
                        }
                    }
                }
            }
        });
        primaryStage.setTitle("Bomberman");
        primaryStage.setScene(scene);
        stage = primaryStage;
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    if(!isPause) {
                        update();
                    }
//                    updateMenu();
                }
            }
        };
        timer.start();
        player = new Bomber(Figure.speed * 4, 2, 4,"right", 1);
    }

    public void update() {
        block.forEach(Entity::update);
        player.update();
        enemy.forEach(Figure::update);

        for (Figure figure: enemyDead) {
            if (figure.getCount() <= 0) {
                enemyDead.remove(figure);
            }
        }

        for (Figure figure: enemy) {
            if (figure.getLife() >= 0 || (!figure.canGo()))
                figure.update();
            else {
                if (figure instanceof Minvo) {
                    Figure newEnemy = Minvo.createNewEnemy(figure.getX(), figure.getY());
                    enemy.remove(figure);
                    enemy.add(newEnemy);
                } else {
                    figure.initDead();
                    enemyDead.add(figure);
                    enemy.remove(figure);
                }
            }
        }

        if (player.canGo()) {
            Move.figureRun(player);
        }

        for (Figure figure: enemy) {
            if (figure.canGo()) {
                Move.figureRun(figure);
            }
        }

        enemyDead.forEach(Figure::update);

        updateSound();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        block.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        enemyDead.forEach(g -> g.render(gc));
        player.render(gc);
    }
}
