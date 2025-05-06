package org.example;

import javax.swing.*;
import java.awt.*;
import org.example.Pieces.*;

public class ChessBoard extends JPanel {
    private final JButton[][] buttons = new JButton[8][8];
    private final Piece[][] board = new Piece[8][8];
    private Position selectedPosition = null;
    private final Game game;

    public ChessBoard(Game game) {
        this.game = game;
        setLayout(new GridLayout(8, 8));
        initializeBoard();
        initializePieces();
    }

    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                Font buttonFont = new Font(button.getFont().getName(), Font.PLAIN, 37);
                button.setFont(buttonFont);
                button.setFocusPainted(false);
                button.setOpaque(true);
                button.setBackground((row + col) % 2 == 0 ?
                        new Color(234, 233, 210) : new Color(75, 115, 153));

                final int currentRow = row;
                final int currentCol = col;
                button.addActionListener(e ->
                        handleBoardClick(new Position(currentRow, currentCol)));

                buttons[row][col] = button;
                add(button);
            }
        }
    }

    private void initializePieces() {
        // Initialize pawns
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(false); // Black pawns
            board[6][col] = new Pawn(true);  // White pawns
            updateButton(1, col);
            updateButton(6, col);
        }

        // Initialize rooks
        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);

        // Initialize knights
        board[0][1] = new Knight(false);
        board[0][6] = new Knight(false);
        board[7][1] = new Knight(true);
        board[7][6] = new Knight(true);

        // Initialize bishops
        board[0][2] = new Bishop(false);
        board[0][5] = new Bishop(false);
        board[7][2] = new Bishop(true);
        board[7][5] = new Bishop(true);

        // Initialize queens
        board[0][3] = new Queen(false);
        board[7][3] = new Queen(true);

        // Initialize kings
        board[0][4] = new King(false);
        board[7][4] = new King(true);

        // Update all the buttons for the back row pieces
        for (int col = 0; col < 8; col++) {
            updateButton(0, col);
            updateButton(7, col);
        }
    }

    private void handleBoardClick(Position clickedPosition) {
        Piece piece = getPiece(clickedPosition);

        if (selectedPosition == null) {
            // First click - select a piece
            if (piece != null && game.isCorrectTurn(piece.isWhite())) {
                selectedPosition = clickedPosition;
                highlightValidMoves(selectedPosition);
            }
        } else {
            // Second click - move the piece
            if (!selectedPosition.equals(clickedPosition)) {
                tryMove(selectedPosition, clickedPosition);
            }
            selectedPosition = null;
            clearHighlights();
        }
    }

    private void tryMove(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece == null) return;

        if (piece.isValidMove(from, to, this)) {
            // Perform the move
            board[to.getRow()][to.getCol()] = piece;
            board[from.getRow()][from.getCol()] = null;

            // Update UI
            updateButton(from.getRow(), from.getCol());
            updateButton(to.getRow(), to.getCol());

            // Switch turns
            game.switchTurn();
        }
    }

    private void highlightValidMoves(Position position) {
        // Simple highlighting implementation
        buttons[position.getRow()][position.getCol()].setBackground(Color.YELLOW);
    }

    private void clearHighlights() {
        // Reset all button colors
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                buttons[row][col].setBackground((row + col) % 2 == 0 ?
                        new Color(234, 233, 210) : new Color(75, 115, 153));
            }
        }
    }

    public Piece getPiece(Position pos) {
        return board[pos.getRow()][pos.getCol()];
    }

    public void updateButton(int row, int col) {
        Piece piece = board[row][col];
        buttons[row][col].setText(piece != null ? piece.toString() : "");
    }
}