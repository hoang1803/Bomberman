package Entity.Figure.Intelligent;

import Control.Move;
import Entity.Figure.Figure;
import Graphics.Sprite;

import static GameRunner.RunBomberman.*;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    private int row;
    private int col;
    private int g;
    private int h;
    private int f;
    private Node parent;

    private List<Integer> neighbors;


    public Node(int x, int y) {
        this.col = x / Sprite.SCALED_SIZE;
        this.row = y / Sprite.SCALED_SIZE;
        this.g = 0;
        this.f = 0;
        this.h = 0;
        this.parent = null;
        neighbors = new ArrayList<>();
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

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Node getParent() {
        return parent;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
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

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setNeighbors(List<Integer> neighbors) {
        this.neighbors = neighbors;
    }

    public int getHeuristic(Figure figure) {
        int row = figure.getY() / Sprite.SCALED_SIZE;
        int col = figure.getX() / Sprite.SCALED_SIZE;

        return Math.abs(this.row - row) + Math.abs(this.col - col);
    }

    private boolean isNeighbor(int row, int col) {

        if (row < 0 || row > HEIGHT) {
            return false;
        }

        if (col < 0 || col > WIDTH) {
            return false;
        }

        int dx = this.row - row;
        int dy = this.col - col;

        if (dx > 1 || dx < -1) {
            return false;
        }

        if (dy > 1 || dy < -1) {
            return false;
        }

        if (dx != 0 && dy != 0) {
            return false;
        }

        if (dx == 0 && dy == 0) {
            return false;
        }

        return !Move.hasBlock(objectMap[row][col]);
    }

    public void addNeighbor(int row, int col) {
        if(!isNeighbor(row, col)) {
            return;
        }

        int index = row * WIDTH + col;
        if (!neighbors.contains(index)) {
            neighbors.add(index);
        }
    }

    public void addAllNeighbor() {
        int[] dx = new int[] {-1,  0, 0, 1};
        int[] dy = new int[] { 0, -1, 1, 0};

        for (int i = 0; i < dx.length; i++) {
            addNeighbor(row + dx[i], col + dy[i]);
        }
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f);
    }
}
