package Entity.Figure.Intelligent;

import Control.Move;
import Entity.Figure.Figure;
import Graphics.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static GameRunner.RunBomberman.*;

public class AStar {
    private int[] status;
    private Node[] node;
    private List<Integer>[] edges;
    private int size;

    public AStar() {

    }

    public AStar(int width, int height) {
        int size = width * height;
        this.size = size;
        this.status = new int[size];
        this.node = new Node[size];
        this.edges = new List[size];

        for (int i = 0; i < size; i++) {
            this.edges[i] = new ArrayList<>();
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (Move.hasBlock(objectMap[row][col])) {
                    continue;
                }

                int index = row * height + col;

                if (!Move.hasBlock(objectMap[row][col + 1])) {
                    edges[index].add(index + 1);
                }

                if (!Move.hasBlock(objectMap[row][col - 1])) {
                    edges[index].add(index - 1);
                }

                if (!Move.hasBlock(objectMap[row + 1][col])) {
                    edges[index].add(index + height);
                }

                if (!Move.hasBlock(objectMap[row - 1][col])) {
                    edges[index].add(index - height);
                }

                node[index] = new Node(row, col, width, height);
                int h = node[index].getHeuristic(player);
                node[index].setH(h);
            }
        }
    }

    public int[] getStatus() {
        return status;
    }

    public int getSize() {
        return size;
    }

    public List[] getEdges() {
        return edges;
    }

    public Node[] getNode() {
        return node;
    }


    public void setEdges(List[] edges) {
        this.edges = edges;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    public void setNode(Node[] node) {
        this.node = node;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int count() {
        int cnt = 0;
        for (int i = 0; i < this.size; i++) {
            if (node[i] != null && status[i] == 1) {
                cnt++;
            }
        }
        return cnt;
    }

    private int find() {
        for (int i = 0; i < size; i++) {
            if(status[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    private int findMin() {
        int minIdx = find();
        if (minIdx == -1) {
            return -1;
        }

        for (int i = 0; i < size; i++) {
            if (status[i] != 1 || node[i] == null) {
                continue;
            }

            if (node[minIdx].getF() > node[i].getF()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    private String getDirection(Figure figure, int height) {
        int row = player.getY() / Sprite.SCALED_SIZE;
        int col = player.getX() / Sprite.SCALED_SIZE;
        int index = row * height + col;
        if (node[index] == null) {
            return figure.getDirection();
        }

        int parentIndex = index;
        while (node[parentIndex].getParent() != -1) {
            parentIndex = node[parentIndex].getParent();
        }

        int rowPar = parentIndex / height;
        int colPar = parentIndex % height;
        if (colPar - col == -1) {
            return "left";
        }

        if (colPar - col == 1) {
            return "right";
        }

        if (rowPar - row == -1) {
            return "up";
        }
        if (rowPar - row == 1) {
            return "down";
        }

        return figure.getDirection();
    }

    public static String findDirection(Figure figure, int width, int height) {
        AStar aStar = new AStar(width, height);
        int row = figure.getY() / Sprite.SCALED_SIZE;
        int col = figure.getX() / Sprite.SCALED_SIZE;
        int index = row * height + col;

        int rp = player.getY() / Sprite.SCALED_SIZE;
        int cp = player.getX() / Sprite.SCALED_SIZE;
        int pIndex = rp * height + cp;

        aStar.status[index] = 1;
        int count = 1;
        while (count != 0) {
            int u = aStar.findMin();
            aStar.status[u] = 2;
            count--;
            if (u == pIndex) {
                break;
            }

            for (Integer v : aStar.edges[u]) {

                if (aStar.status[v] == 0) {
                    int g = aStar.node[u].getG() + 1;
                    aStar.node[v].setG(g);
                    aStar.node[v].setF(aStar.node[v].getG() + aStar.node[v].getH());
                    aStar.node[v].setParent(u);
                    aStar.status[v] = 1;
                    count++;
                    continue;
                }

                if (aStar.status[v] == 1 && aStar.node[v].getG() > aStar.node[u].getG() + 1) {
                    int g = aStar.node[u].getG() + 1;
                    aStar.node[v].setG(g);
                    aStar.node[v].setF(aStar.node[v].getG() + aStar.node[v].getH());
                    aStar.node[v].setParent(u);
                    continue;
                }

                if (aStar.status[v] == 2 && aStar.node[v].getG() > aStar.node[u].getG() + 1) {
                    aStar.status[v] = 1;
                    count++;
                }
            }
        }
        return aStar.getDirection(figure, height);
    }
}
