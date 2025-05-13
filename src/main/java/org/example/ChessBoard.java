package org.example;

import javax.swing.*;
import java.awt.*;
import org.example.Pieces.*;

public class ChessBoard extends JPanel {
    private final JButton[][] buttons = new JButton[8][8]; // Knapparna på schackbrädet
    private final Piece[][] board = new Piece[8][8]; // Var alla pjäser ligger
    private Position selectedPosition = null; // Vilken ruta som är vald just nu
    private final Game game; // Länk till huvudspelet

    public ChessBoard(Game game) {
        this.game = game;
        setLayout(new GridLayout(8, 8)); // 8x8 rutnät
        initializeBoard();
        initializePieces();
    }

    // Skapar alla knappar för schackbrädet
    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                // Större font så pjäserna syns tydligt
                Font buttonFont = new Font(button.getFont().getName(), Font.PLAIN, 37);
                button.setFont(buttonFont);
                button.setFocusPainted(false);
                button.setOpaque(true);
                // Varannan ruta är Vit/Blå
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

    // Lägger ut alla pjäser i startposition
    private void initializePieces() {
        // Bönder
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(false); // Svartas bönder på rad 2
            board[6][col] = new Pawn(true);  // Vitas bönder på rad 7
            updateButton(1, col);
            updateButton(6, col);
        }

        // Torn
        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);

        // Springare
        board[0][1] = new Knight(false);
        board[0][6] = new Knight(false);
        board[7][1] = new Knight(true);
        board[7][6] = new Knight(true);

        // Löpare
        board[0][2] = new Bishop(false);
        board[0][5] = new Bishop(false);
        board[7][2] = new Bishop(true);
        board[7][5] = new Bishop(true);

        // Damer
        board[0][3] = new Queen(false);
        board[7][3] = new Queen(true);

        // Kungar
        board[0][4] = new King(false);
        board[7][4] = new King(true);

        // Uppdaterar visningen för första och sista raden
        for (int col = 0; col < 8; col++) {
            updateButton(0, col);
            updateButton(7, col);
        }
    }

    // Hanterar när man klickar på en ruta
    private void handleBoardClick(Position clickedPosition) {
        Piece piece = getPiece(clickedPosition);

        if (selectedPosition == null) {
            // Första klicket - välj en pjäs om det är din tur
            if (piece != null && game.isCorrectTurn(piece.isWhite())) {
                selectedPosition = clickedPosition;
                highlightSelectedPosition(selectedPosition);
            }
        } else {
            // Andra klicket - försök flytta pjäsen
            if (!selectedPosition.equals(clickedPosition)) {
                tryMove(selectedPosition, clickedPosition);
            }
            selectedPosition = null;
            clearHighlights();
        }
    }

    // Försöker flytta en pjäs från en ruta till en annan
    private void tryMove(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece == null) return;

        if (piece.isValidMove(from, to, this)) {
            // Flytta pjäsen
            board[to.row()][to.col()] = piece;
            board[from.row()][from.col()] = null;

            // Uppdatera vad som visas i rutorna
            updateButton(from.row(), from.col());
            updateButton(to.row(), to.col());

            // Byt till nästa spelare
            game.switchTurn();
        }
    }

    // Markerar vald pjäsen
    private void highlightSelectedPosition(Position position) {
        buttons[position.row()][position.col()].setBackground(Color.YELLOW);
    }

    // Tar bort alla markeringar
    private void clearHighlights() {
        // Återställer alla rutor till originalfärg
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                buttons[row][col].setBackground((row + col) % 2 == 0 ?
                        new Color(234, 233, 210) : new Color(75, 115, 153));
            }
        }
    }

    // Hämtar pjäsen på en specifik position
    public Piece getPiece(Position pos) {
        return board[pos.row()][pos.col()];
    }

    // Uppdaterar vad som visas i en ruta
    public void updateButton(int row, int col) {
        Piece piece = board[row][col];
        buttons[row][col].setText(piece != null ? piece.toString() : "");
    }

    // Kollar om en position är inom brädet
    public boolean isValidPosition(Position pos) {
        return pos.row() >= 0 && pos.row() < 8 &&
                pos.col() >= 0 && pos.col() < 8;
    }
}