package org.example;

import javax.swing.*;

public class Game {
    private final ChessBoard board;
    private boolean isWhiteTurn = true;

    public Game() {
        this.board = new ChessBoard(this);
    }

    public void start() {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(board);
        frame.setVisible(true);
    }

    public boolean isCorrectTurn(Piece piece) {
        if (isWhiteTurn && piece.isWhite()) return true;
        if (!isWhiteTurn && !piece.isWhite()) return true;
        return false;
    }

    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
        System.out.println(isWhiteTurn ? "White's Turn" : "Black's Turn");
    }
}