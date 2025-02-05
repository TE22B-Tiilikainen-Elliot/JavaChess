package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(16, 16)); // Set layout before adding components

        // Add some buttons as an example
        for (int i = 0; i < 16 * 16; i++) {
            frame.add(new JButton());
        }

        frame.setVisible(true); // Set visibility at the end
    }
}