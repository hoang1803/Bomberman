package Control;

import Entity.Block.Bomb;
import Entity.Block.Portal;
import Entity.Figure.Bomber;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;
import Graphics.Map;
import Graphics.Sprite;
import Level.NextLevel;
import Sound.Sound;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.net.MalformedURLException;

import static GameRunner.RunBomberman.*;
import static GameRunner.RunBomberman.level;
import static Sound.Sound.*;

public class Menu {
    public static ImageView statusGame;
    public static ImageView exit;
    private static Text level, bomb, time;
    public static int timeLeft = 120;
    public static ImageView background = null;
    public static Image pauseButton;
    public static Image startButton;
    public static Image resumeButton;
    public static Image restartButton;
    private static Pane statusBar;
    public static boolean startGame = true;
    public static boolean winGame = false;
    public static boolean loseGame = false;
    public static boolean nextLevel = false;
    private static int loadingGame = 1;
    private static long lastTime;

    public static final Color BRIGHT_BLUE = Color.rgb(115, 219, 255);
    public static final Color BLUE = Color.rgb(0, 153, 255);

    private static void initLevel() {
        level = new Text("");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        level.setFill(Color.WHITE);
        level.setX(400);
        level.setY(28);
    }

    private static void initBomb() {
        bomb = new Text("");
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        bomb.setFill(Color.WHITE);
        bomb.setX(520);
        bomb.setY(28);
    }

    private static void initTime() {
        time = new Text("");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        time.setFill(Color.WHITE);
        time.setX(650);
        time.setY(28);
    }

    private static void initStatusGame() {
        startButton = loadImg("res/menu/start.png");
        pauseButton = loadImg("res/menu/pause.png");
        resumeButton = loadImg("res/menu/resume.png");
        restartButton = loadImg("res/menu/restart.png");

        statusGame = new ImageView(startButton);
        statusGame.setX(185);
        statusGame.setY(160);
        statusGame.setScaleX(0.4);
        statusGame.setScaleY(0.4);
    }

    private static void initExit() {
        exit = new ImageView(loadImg("res/menu/exit.png"));
        exit.setX(185);
        exit.setY(260);
        exit.setScaleX(0.4);
        exit.setScaleY(0.4);
    }

    private static void initStatusBar() {
        initTime();
        initBomb();
        initLevel();
        statusBar = new Pane();
        statusBar.getChildren().addAll(time, bomb, level);
    }

    private static void initBackground() {
        background = new ImageView(loadImg("res/menu/menu.png"));
        background.setScaleX(0.5);
        background.setScaleY(0.5);
        background.setX(-496);
        background.setY(-228);
    }

    public static Image loadImg(String path) {
        File file = new File(path);
        try {
            return new Image(file.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            System.out.println("Can't load " + path);
            throw new RuntimeException(e);
        }
    }

    private static void restartGame() {
        running = true;
        runningLevel = true;
        isPause = false;
        startGame = false;
        winGame = loseGame = false;
        wait = false;
        nextLevel = false;

        Sound.isSoundTitle = false;
        Sound.isSoundDied = false;
        background.setImage(loadImg("res/menu/transparent.png"));

        RunBomberman.level = 1;
        map = new Map(1);
        player = new Bomber(Figure.speed * 4, 2, 4,"right", 1);
        timeLeft = 120;
        Bomb.bombNumber = 20;
        Bomb.countBomb = 1;
        Bomb.range = 0;
    }

    private static void loading() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 300l) {
            lastTime = currentTime;
            if (loadingGame == 1) {
                background.setImage(loadImg("res/menu/Loading_1.png"));
                loadingGame = 2;
            } else if (loadingGame == 2) {
                background.setImage(loadImg("res/menu/Loading_2.png"));
                loadingGame = 3;
            } else if (loadingGame == 3) {
                background.setImage(loadImg("res/menu/Loading_3.png"));
                loadingGame = 1;
            }
        }
    }

    public static void create(Group group) {
        new Sound("welcome.wav", "welcome");
        initStatusBar();
        initBackground();
        initStatusGame();
        initExit();

        group.getChildren().addAll(background, statusBar, statusGame, exit);

        statusGame.setOnMouseClicked(event -> {
            if(runningLevel) {
                isPause = !isPause;
            } else if (startGame || winGame || loseGame) {
                if (startGame) {
                    welcome.close();
                }
                restartGame();
            }

            updateMenu();
        });

        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

    private static void win() {
        music.close();
        runningLevel = false;
        running = false;
        statusGame.setImage(restartButton);
        background.setImage(loadImg("res/menu/You win!.png"));
    }

    private static void lose() {
        music.close();
        runningLevel = false;
        running = false;
        statusGame.setImage(restartButton);
        background.setImage(loadImg("res/menu/You lose!.png"));
    }

    private static void nextLevel() {
        runningLevel = false;
        statusGame.setScaleX(0);
        statusGame.setScaleY(0);
        exit.setScaleX(0);
        exit.setScaleY(0);
        loading();
    }

    public static void updateMenu() {
        if (winGame || loseGame || nextLevel) {
            level.setText("");
            bomb.setText("");
            time.setText("");
            statusGame.setImage(startButton);
            statusGame.setX(185);
            statusGame.setY(160);
            statusGame.setScaleX(0.4);
            statusGame.setScaleY(0.4);

            exit.setX(185);
            exit.setY(260);
            exit.setScaleX(0.4);
            exit.setScaleY(0.4);

            if (winGame) {
                win();
            } else if (loseGame) {
                lose();
            } else if (nextLevel) {
                nextLevel();
            }
        } else if (runningLevel) {
            background.setImage(loadImg("res/menu/transparent.png"));
            level.setText("Level: " + RunBomberman.level);
            bomb.setText("Bombs: " + Bomb.bombNumber);
            time.setText("Times: " + timeLeft + "s");

            statusGame.setX(-160);
            statusGame.setY(-87);
            statusGame.setScaleX(0.18);
            statusGame.setScaleY(0.18);

            exit.setScaleX(0);
            exit.setScaleY(0);

            if (isPause) {
                statusGame.setImage(resumeButton);
            } else {
                statusGame.setImage(pauseButton);
            }
        }
    }
}