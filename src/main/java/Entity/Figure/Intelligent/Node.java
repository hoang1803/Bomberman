package Entity.Figure.Intelligent;

import Entity.Figure.Figure;
import Graphics.Sprite;

public class Node {
    private int index;
    private int g;
    private int h;
    private int f;
    private int parent;
    private int width;
    private int height;


    public Node(int row, int col, int width, int height) {
        this.index = row * height + col;
        this.g = 0;
        this.h = 0;
        this.f = 0;
        this.parent = -1;
        this.width = width;
        this.height = height;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getIndex() {
        return index;
    }

    public int getParent() {
        return parent;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getHeuristic(Figure figure) {
        int row = index / height;
        int col = index % height;

        int row_finish = figure.getY() / Sprite.SCALED_SIZE;
        int col_finish = figure.getX() / Sprite.SCALED_SIZE;

        return Math.abs(row_finish - row) + Math.abs(col_finish - col);
    }
}
