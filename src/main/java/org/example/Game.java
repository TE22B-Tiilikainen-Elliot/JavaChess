package org.example;

import javax.swing.*;

public class Game {
    private final ChessBoard board; // Schackbrädet
    private boolean isWhiteTurn = true; // Vems tur det är

    public Game() {
        this.board = new ChessBoard(this); // Skapa nytt bräde
    }

    // Starta spelet och visa fönstret
    public void start() {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(board);
        frame.setVisible(true);
    }

    // Kolla om det är rätt spelares tur
    public boolean isCorrectTurn(boolean isPieceWhite) {
        return isWhiteTurn == isPieceWhite;
    }

    // Byt tur mellan spelare
    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
        System.out.println(isWhiteTurn ? "White's Turn" : "Black's Turn");
    }
}