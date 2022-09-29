package GameRunner;

import Entity.Figure.Figure;
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
    public static final int HEIGHT = 15;
    public static final int WIDTH = 25;

    public static int level = 1;
    public static List<Figure> enemy = new ArrayList<>();
    public static Figure player;

    private GraphicsContext gc;
    private Canvas canvas;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
    }
}
