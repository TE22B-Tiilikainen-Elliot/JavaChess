package org.example.Pieces;

import org.example.Piece;
import org.example.Position;
import org.example.ChessBoard;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        int direction = isWhite() ? -1 : 1;
        int startRow = isWhite() ? 6 : 1;

        // Forward move
        if (from.getCol() == to.getCol()) {
            // Single move
            if (to.getRow() - from.getRow() == direction &&
                    board.getPiece(to) == null) {
                return true;
            }
            // Double move from start position
            if (from.getRow() == startRow &&
                    to.getRow() - from.getRow() == 2 * direction &&
                    board.getPiece(to) == null) {
                Position middle = new Position(from.getRow() + direction, from.getCol());
                return board.getPiece(middle) == null;
            }
        }

        // Diagonal capture
        if (Math.abs(from.getCol() - to.getCol()) == 1 &&
                to.getRow() - from.getRow() == direction) {
            Piece target = board.getPiece(to);
            return target != null && target.isWhite() != this.isWhite();
        }


        return false;
    }

    @Override
    public String toString() {
        return isWhite() ? "♙" : "♟";
    }
}