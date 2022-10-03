package GameRunner;

import Control.Move;
import Entity.Block.Grass;
import Entity.Block.Wall;
import Entity.Entity;
import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import Graphics.Map;
import Graphics.Sprite;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RunBomberman extends Application {
    public static final int HEIGHT = 13;
    public static final int WIDTH = 31;

    public static int level = 1;
    public static List<Figure> enemy = new ArrayList<>();
    public static List<Entity> block = new ArrayList<>();
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
                if(running) {
                    render();
                    if(!isPause) {
                        update();
                    }
                }
            }
        };
        timer.start();
        player = new Bomber(Figure.speed * 4, 2,"right", 3);

    }

    public void update() {
        block.forEach(Entity::update);
        player.update();

        int delay = player.getDelayTime();
        if (delay == 0) {
           Move.figureRun(player);
           delay = Figure.DELAY_TIME;
        }
        player.setDelayTime(delay - 1);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        block.forEach(g -> g.render(gc));
        player.render(gc);
    }
}
