package GameRunner;

import Entity.Block.Grass;
import Entity.Block.Wall;
import Entity.Entity;
import Entity.Figure.Figure;
import Graphics.Map;
import Graphics.Sprite;
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
    public static char[][] objectMap;

    private GraphicsContext gc;
    private Canvas canvas;
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

        primaryStage.setTitle("Bomberman");
        primaryStage.setScene(scene);
        primaryStage.show();
        render();
    }

    public void update() {

    }

    public void render() {

    }
}
