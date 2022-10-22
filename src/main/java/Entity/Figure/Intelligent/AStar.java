package Entity.Figure.Intelligent;

import Entity.Figure.Figure;
import Graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static GameRunner.RunBomberman.*;

public class AStar {
    private static Node[] nodes = new Node[WIDTH * HEIGHT];

    private static PriorityQueue<Node> openList = new PriorityQueue<>();
    private static PriorityQueue<Node> closedList = new PriorityQueue<>();
    public AStar() {

    }

    private static void update() {

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {

                int index = row * WIDTH + col;

                nodes[index] = new Node();
                nodes[index].setCol(col);
                nodes[index].setRow(row);

                int h = nodes[index].getHeuristic(player);
                nodes[index].setH(h);

                nodes[index].addAllNeighbor();
            }
        }
    }
    private static int getIndex(Figure figure) {
        int row = figure.getY() / Sprite.SCALED_SIZE;
        int col = figure.getX() / Sprite.SCALED_SIZE;
        return row * WIDTH + col;
    }
    private static void aStar(Figure figure) {

        update();

        openList.clear();
        closedList.clear();

        int figureIndex = getIndex(figure);
        nodes[figureIndex].setG(0);
        nodes[figureIndex].setF(nodes[figureIndex].getH());

        openList.add(nodes[figureIndex]);

        int playerIndex = getIndex(player);

        while (!openList.isEmpty()) {
            Node u = openList.poll();
            closedList.add(u);
            int currentIndex = u.getIndex();

            if (u.getF() != nodes[currentIndex].getF()) {
                continue;
            }

            if (currentIndex == playerIndex) {
                return;
            }

            for (int index : u.getNeighbors()) {
                Node v = nodes[index];
                int g = u.getG() + 1;

                if (!openList.contains(v) && !closedList.contains(v)) {
                    v.setG(g);
                    v.setF(g + v.getH());
                    v.setParent(u);
                    openList.add(v);
                } else {
                    if (v.getG() > g) {
                        v.setG(g);
                        v.setF(g + v.getH());
                        v.setParent(u);

                        if (closedList.contains(v)) {
                           closedList.remove(v);
                           openList.add(v);
                        }
                    }
                }
                nodes[index] = v;
            }
        }
    }

    private static List<Node> findPath(Figure figure) {
        aStar(figure);

        List<Node> path = new ArrayList<>();

        int playerIndex = getIndex(player);

        Node currentNode = nodes[playerIndex];

        while (currentNode.getParent() != null) {
            path.add(0, currentNode);
            currentNode = currentNode.getParent();
        }

        return path;
    }

    public static String getDirection(Figure figure) {
        List<Node> path = findPath(figure);
        String direction = figure.getDirection();
        try {
            Node child = path.get(0);
            Node parent = child.getParent();

            if (parent.getIndex() != getIndex(figure)) {
                throw new Exception();
            }

            int cRow = child.getRow();
            int cCol = child.getCol();

            int pRow = parent.getRow();
            int pCol = parent.getCol();

            int dx = cRow - pRow;
            int dy = cCol - pCol;

            if (dx != 0 && dy != 0) {
                throw new Exception();
            }

            if (dx == 0 && dy == 0) {
                throw new Exception();
            }

            if (dx == -1) {
                direction = "up";
            }

            if (dx == 1) {
                direction = "down";
            }

            if (dy == -1) {
                direction = "left";
            }

            if (dy == 1) {
                direction = "right";
            }



        } catch (Exception e) {
//            e.printStackTrace();
        }

        return direction;
    }
}
