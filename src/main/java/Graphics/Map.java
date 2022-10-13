package Graphics;

import Entity.Block.Brick;
import Entity.Block.Grass;
import Entity.Block.Portal;
import Entity.Block.Wall;
import Entity.Entity;
import Entity.Figure.Balloom;
import Entity.Figure.Figure;
import GameRunner.RunBomberman;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static GameRunner.RunBomberman.*;

public class Map {
    public static final char WALL = '#';
    public static final char BRICK = '*';
    public static final char PORTAL = 'x';
    public static final char GRASS = '.';
    public static final char BOMB = 'z';

    public static final char PLAYER = 'p';
    public static final char BALLOON = '1';
    public static final char ONEAl = '2';
    public static final char DOLL = '3';
    public static final char KONDORIA = '4';
    public static final char MINVO = '5';

    public static final char BOMB_ITEM = 'b';
    public static final char FLAME_ITEM = 'f';
    public static final char SPEED_ITEM = 's';
    public Map(int level) {
        final String path = "res/levels/level" + level + ".txt";
        File file = new File(path);
        int height = 0;
        int width = 0;
        try {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);

            RunBomberman.level = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());

            block = new ArrayList<>();

            objectMap = new char[height][width];
            killObject = new int[height][width];

            for(int y = 0; y < height; y++) {
                line = sc.nextLine();

                for(int x = 0; x < width; x++) {
                    objectMap[y][x] = line.charAt(x);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        block.clear();
        enemy.clear();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {

                Entity entity = switch (objectMap[y][x]) {
                    case WALL -> new Wall(x, y, Sprite.wall.getFxImage());
                    case BRICK -> new Brick(x, y, Sprite.brick.getFxImage());
                    case PORTAL -> new Portal(x, y, Sprite.portal.getFxImage());
                    default -> new Grass(x, y, Sprite.grass.getFxImage());
                };
                block.add(entity);

                Figure figure = switch (objectMap[y][x]) {
                    case BALLOON -> new Balloom(Figure.speed * 2, 1, 15, "left", 1);
                    default -> null;
                };
                if (figure != null)  {
                    figure.setX(x * Sprite.SCALED_SIZE);
                    figure.setY(y * Sprite.SCALED_SIZE);
                    enemy.add(figure);
                }
            }
        }

    }

}
