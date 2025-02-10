package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        int[] playerPos = {10, 10};
        int fov = 10;

        Map map = new Map(200, 200);
        frame.add(map.getPanel(playerPos, fov));

        frame.setVisible(true);
    }
}