package org.example;

import javax.swing.*;
import java.awt.*;

class Map {
    private int rows, cols;
    private int[][] map;
    private JPanel panel;

    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.map = new int[rows][cols];

        int obj = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = obj++;
            }
        }
    }

    public JPanel getPanel(int[] playerPos, int fov) {
        panel = new JPanel(new GridLayout(fov, fov));

        int halfFov = fov / 2;
        int startX = Math.max(0, playerPos[0] - halfFov);
        int startY = Math.max(0, playerPos[1] - halfFov);
        int endX = Math.min(rows, playerPos[0] + halfFov);
        int endY = Math.min(cols, playerPos[1] + halfFov);

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                JPanel tile = new JPanel();
                tile.setBackground(new Color((int) (Math.random() * 0x1000000))); // Random colors
                panel.add(tile);
            }
        }
        return panel;
    }
}