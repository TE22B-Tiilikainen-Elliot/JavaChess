package org.example.Pieces;

import org.example.Position;
import org.example.ChessBoard;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    // Springaren hoppar i L-form: 2 steg + 1 steg åt sidan
    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        int rowDiff = Math.abs(to.row() - from.row());
        int colDiff = Math.abs(to.col() - from.col());

        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }

        Piece target = board.getPiece(to);
        return target == null || target.isWhite() != this.isWhite();
    }

    @Override
    public String toString() {
        return isWhite() ? "♘" : "♞";  // Vit/svart springare
    }
}