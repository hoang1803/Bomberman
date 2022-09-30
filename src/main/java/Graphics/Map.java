package Graphics;

import Entity.Block.Brick;
import Entity.Block.Grass;
import Entity.Block.Portal;
import Entity.Block.Wall;
import Entity.Entity;
import GameRunner.RunBomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static GameRunner.RunBomberman.*;

public class Map {
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

            for(int y = 0; y < height; y++) {
                line = sc.nextLine();

                for(int x = 0; x < width; x++) {
                    objectMap[y][x] = line.charAt(x);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {

                Entity entity;

                switch (objectMap[y][x]) {
                    case '#':
                        entity = new Wall(x, y, Sprite.wall.getFxImage());
                        break;
                    case '*':
                        entity = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'x':
                        entity = new Portal(x, y, Sprite.portal.getFxImage());
                        break;
                    default:
                        entity = new Grass(x, y, Sprite.grass.getFxImage());
                }

                block.add(entity);
            }
        }

    }

}
