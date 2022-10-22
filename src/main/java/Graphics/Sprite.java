package Graphics;

import Entity.Entity;
import Entity.Figure.Figure;
import javafx.scene.image.*;

public class

Sprite {
    public static final int ORIGINAL_SIZE = 16;
    public static final int SCALED_SIZE = ORIGINAL_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff; //Pink color
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    protected int realWidth;
    protected int realHeight;
    private SpriteSheet sheet;

    /**
     * Load pixel from spritePixel to pixels.
     */
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.spritePixel[(x + this.x) + (y + this.y) * this.sheet.SIZE];
            }
        }

    }

    /**
     * Constructor 1.
     * @param SIZE Sprite size
     * @param x x-coordinate
     * @param y y-coordinate
     * @param spriteSheet spriteSheet
     * @param realWidth real Width
     * @param realHeight real Height
     */
    public Sprite(int SIZE, int x, int y, SpriteSheet spriteSheet, int realWidth, int realHeight) {
        this.SIZE = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.sheet = spriteSheet;
        this.pixels = new int[this.SIZE * this.SIZE];
        load();
    }

    /**
     * Constructor 2.
     * @param SIZE Sprite size
     * @param color color
     */
    public Sprite(int SIZE, int color) {
        this.SIZE = SIZE;
        this.pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    // This object creates transparent color in the outline of the interface.
    public static Sprite transparent = new Sprite(ORIGINAL_SIZE, 15, 15, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite grass = new Sprite(ORIGINAL_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite brick = new Sprite(ORIGINAL_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite wall = new Sprite(ORIGINAL_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite portal = new Sprite(ORIGINAL_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(ORIGINAL_SIZE, 0, 0, SpriteSheet.tiles, 12, 16);
    public static Sprite player_down = new Sprite(ORIGINAL_SIZE, 2, 0, SpriteSheet.tiles, 12, 15);
    public static Sprite player_left = new Sprite(ORIGINAL_SIZE, 3, 0, SpriteSheet.tiles, 10, 15);
    public static Sprite player_right = new Sprite(ORIGINAL_SIZE, 1, 0, SpriteSheet.tiles, 10, 16);

    public static Sprite player_up_1 = new Sprite(ORIGINAL_SIZE, 0, 1, SpriteSheet.tiles, 12, 16);
    public static Sprite player_up_2 = new Sprite(ORIGINAL_SIZE, 0, 2, SpriteSheet.tiles, 12, 15);

    public static Sprite player_down_1 = new Sprite(ORIGINAL_SIZE, 2, 1, SpriteSheet.tiles, 12, 15);
    public static Sprite player_down_2 = new Sprite(ORIGINAL_SIZE, 2, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_left_1 = new Sprite(ORIGINAL_SIZE, 3, 1, SpriteSheet.tiles, 11, 16);
    public static Sprite player_left_2 = new Sprite(ORIGINAL_SIZE, 3, 2, SpriteSheet.tiles, 12 ,16);

    public static Sprite player_right_1 = new Sprite(ORIGINAL_SIZE, 1, 1, SpriteSheet.tiles, 11, 16);
    public static Sprite player_right_2 = new Sprite(ORIGINAL_SIZE, 1, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite player_dead1 = new Sprite(ORIGINAL_SIZE, 4, 2, SpriteSheet.tiles, 14, 16);
    public static Sprite player_dead2 = new Sprite(ORIGINAL_SIZE, 5, 2, SpriteSheet.tiles, 13, 15);
    public static Sprite player_dead3 = new Sprite(ORIGINAL_SIZE, 6, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOM
    public static Sprite balloom_left1 = new Sprite(ORIGINAL_SIZE, 9, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left2 = new Sprite(ORIGINAL_SIZE, 9, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_left3 = new Sprite(ORIGINAL_SIZE, 9, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_right1 = new Sprite(ORIGINAL_SIZE, 10, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right2 = new Sprite(ORIGINAL_SIZE, 10, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloom_right3 = new Sprite(ORIGINAL_SIZE, 10, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloom_dead = new Sprite(ORIGINAL_SIZE, 9, 3, SpriteSheet.tiles, 16, 16);

    //ONEAL
    public static Sprite oneal_left1 = new Sprite(ORIGINAL_SIZE, 11, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left2 = new Sprite(ORIGINAL_SIZE, 11, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left3 = new Sprite(ORIGINAL_SIZE, 11, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_right1 = new Sprite(ORIGINAL_SIZE, 12, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right2 = new Sprite(ORIGINAL_SIZE, 12, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right3 = new Sprite(ORIGINAL_SIZE, 12, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_dead = new Sprite(ORIGINAL_SIZE, 11, 3, SpriteSheet.tiles, 16, 16);

    //Doll
    public static Sprite doll_left1 = new Sprite(ORIGINAL_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left2 = new Sprite(ORIGINAL_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left3 = new Sprite(ORIGINAL_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_right1 = new Sprite(ORIGINAL_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right2 = new Sprite(ORIGINAL_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right3 = new Sprite(ORIGINAL_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_dead = new Sprite(ORIGINAL_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);

    //Minvo
    public static Sprite minvo_left1 = new Sprite(ORIGINAL_SIZE, 8, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left2 = new Sprite(ORIGINAL_SIZE, 8, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left3 = new Sprite(ORIGINAL_SIZE, 8, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_right1 = new Sprite(ORIGINAL_SIZE, 9, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right2 = new Sprite(ORIGINAL_SIZE, 9, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right3 = new Sprite(ORIGINAL_SIZE, 9, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_dead = new Sprite(ORIGINAL_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);

    //Kondoria
    public static Sprite kondoria_left1 = new Sprite(ORIGINAL_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left2 = new Sprite(ORIGINAL_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left3 = new Sprite(ORIGINAL_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_right1 = new Sprite(ORIGINAL_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right2 = new Sprite(ORIGINAL_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right3 = new Sprite(ORIGINAL_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_dead = new Sprite(ORIGINAL_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

    //ALL
    public static Sprite mob_dead1 = new Sprite(ORIGINAL_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(ORIGINAL_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(ORIGINAL_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(ORIGINAL_SIZE, 0, 3, SpriteSheet.tiles, 15, 15);
    public static Sprite bomb_1 = new Sprite(ORIGINAL_SIZE, 1, 3, SpriteSheet.tiles, 13, 15);
    public static Sprite bomb_2 = new Sprite(ORIGINAL_SIZE, 2, 3, SpriteSheet.tiles, 12, 14);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(ORIGINAL_SIZE, 0, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded1 = new Sprite(ORIGINAL_SIZE, 0, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded2 = new Sprite(ORIGINAL_SIZE, 0, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical = new Sprite(ORIGINAL_SIZE, 1, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical1 = new Sprite(ORIGINAL_SIZE, 2, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical2 = new Sprite(ORIGINAL_SIZE, 3, 5, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal = new Sprite(ORIGINAL_SIZE, 1, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal1 = new Sprite(ORIGINAL_SIZE, 1, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal2 = new Sprite(ORIGINAL_SIZE, 1, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_left_last = new Sprite(ORIGINAL_SIZE, 0, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(ORIGINAL_SIZE, 0, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(ORIGINAL_SIZE, 0, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_right_last = new Sprite(ORIGINAL_SIZE, 2, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(ORIGINAL_SIZE, 2, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(ORIGINAL_SIZE, 2, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_top_last = new Sprite(ORIGINAL_SIZE, 1, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top_last1 = new Sprite(ORIGINAL_SIZE, 2, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top_last2 = new Sprite(ORIGINAL_SIZE, 3, 4, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_down_last = new Sprite(ORIGINAL_SIZE, 1, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_down_last1 = new Sprite(ORIGINAL_SIZE, 2, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_down_last2 = new Sprite(ORIGINAL_SIZE, 3, 6, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(ORIGINAL_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded1 = new Sprite(ORIGINAL_SIZE, 7, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded2 = new Sprite(ORIGINAL_SIZE, 7, 3, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(ORIGINAL_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames = new Sprite(ORIGINAL_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed = new Sprite(ORIGINAL_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_wallpass = new Sprite(ORIGINAL_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_detonator = new Sprite(ORIGINAL_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_bombpass = new Sprite(ORIGINAL_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flamepass = new Sprite(ORIGINAL_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

    /**
     * setColor: set sprite into color.
     * @param color color
     */
    private void setColor(int color) {
        for(int y = 0; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = color;
            }
        }
    }

    /**
     * reSample: resize proportionally.
     * @param input Image
     * @param scaleFactor scale
     * @return Image
     */
    private Image reSample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(W * S, H * S);

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for(int y = 0; y < H; y++) {
            for(int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }
        return output;
    }

    /**
     * getFxImage: remove font.
     * @return Image
     */
    public Image getFxImage() {
        WritableImage output = new WritableImage(SIZE, SIZE);

        PixelWriter writer = output.getPixelWriter();

        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++){
                if (pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    writer.setArgb(x, y, 0);
                } else {
                    writer.setArgb(x, y, pixels[x + y * SIZE]);
                }
            }
        }

        Image input = new ImageView(output).getImage();

        return reSample(input, SCALED_SIZE / ORIGINAL_SIZE);

    }

    public static void renderSprite(Sprite normal, Sprite firstFrame, Sprite secondFrame, Figure figure) {
        int curFrame = figure.getCurrentFrame();
        if (curFrame == 1) {
            figure.setImage(normal.getFxImage());
            figure.setCurrentFrame(2);
        } else if (curFrame == 2) {
            figure.setImage(firstFrame.getFxImage());
            figure.setCurrentFrame(3);
        } else if (curFrame == 3) {
            figure.setImage(secondFrame.getFxImage());
            figure.setCurrentFrame(4);
        } else {
            figure.setImage(firstFrame.getFxImage());
            figure.setCurrentFrame(1);
        }
    }

    public static void renderSpriteBomb(Sprite normal, Sprite firstFrame, Sprite secondFrame, Entity entity) {
        int curFrame = entity.getCurrentFrame();
        if (curFrame == 1) {
            entity.setImage(normal.getFxImage());
            entity.setCurrentFrame(2);
        } else if (curFrame == 2) {
            entity.setImage(firstFrame.getFxImage());
            entity.setCurrentFrame(3);
        } else if (curFrame == 3) {
            entity.setImage(secondFrame.getFxImage());
            entity.setCurrentFrame(4);
        } else {
            entity.setImage(firstFrame.getFxImage());
            entity.setCurrentFrame(1);
        }
    }

    public static void renderSpriteForBomber(Sprite normal, Sprite firstFrame, Sprite secondFrame, Figure figure) {
        int curFrame = figure.getCurrentFrame();
        if (curFrame == 1) {
            figure.setImage(normal.getFxImage());
            figure.setCurrentFrame(2);
        } else if (curFrame == 2) {
            figure.setImage(firstFrame.getFxImage());
            figure.setCurrentFrame(3);
        } else if (curFrame == 3) {
            figure.setImage(normal.getFxImage());
            figure.setCurrentFrame(4);
        } else {
            figure.setImage(secondFrame.getFxImage());
            figure.setCurrentFrame(1);
        }
    }


}
