package org.example;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {
    private final JButton[][] buttons = new JButton[8][8];
    private final String[] assets = {"", "♙", "♖", "♘", "♗", "♕", "♔", "♟", "♜", "♞", "♝", "♛", "♚"};
    private final int[][] chessBoard = {
            {8, 9, 10, 11, 12, 10, 9, 8},
            {7, 7, 7, 7, 7, 7, 7, 7},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {2, 3, 4, 5, 6, 4, 3, 2}
    };

    private Position selectedPosition = null;
    private final Game game;

    public ChessBoard(Game game) {
        this.game = game;
        setLayout(new GridLayout(8, 8));
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton(assets[chessBoard[row][col]]);
                Font buttonFont = new Font(button.getFont().getName(), button.getFont().getStyle(), 37);
                button.setFont(buttonFont);
                button.setFocusPainted(false);
                button.setOpaque(true);

                if ((row + col) % 2 == 0) {
                    button.setBackground(new Color(234, 233, 210));
                } else {
                    button.setBackground(new Color(75, 115, 153));
                }

                final int currentRow = row;
                final int currentCol = col;

                button.addActionListener(e -> handleBoardClick(new Position(currentRow, currentCol)));

                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private void handleBoardClick(Position clickedPosition) {
        Piece piece = new Piece(chessBoard[clickedPosition.getRow()][clickedPosition.getCol()]);

        if (selectedPosition == null) {
            System.out.println(piece.getType());
            if (piece.getType() != 0 && game.isCorrectTurn(piece)) {
                selectedPosition = clickedPosition;
                System.out.println("Selected piece at: " + selectedPosition);
            } else {
                System.out.println("Invalid selection! Not your piece.");
            }
        } else {
            if (true) {
                movePiece(selectedPosition, clickedPosition);
                selectedPosition = null;
            } else {
                System.out.println("Invalid move for " + piece.getType());
            }
        }
    }

    public void movePiece(Position from, Position to) {
        int piece = chessBoard[from.getRow()][from.getCol()];
        if (piece != 0) {
            chessBoard[to.getRow()][to.getCol()] = piece;
            chessBoard[from.getRow()][from.getCol()] = 0;

            buttons[from.getRow()][from.getCol()].setText("");
            buttons[to.getRow()][to.getCol()].setText(assets[piece]);

            game.switchTurn();
        }
    }
}