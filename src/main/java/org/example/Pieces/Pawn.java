package org.example.Pieces;

import org.example.Position;
import org.example.ChessBoard;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    // Bonden har speciella drag: framåt, dubbeldrag första gången, och slag diagonalt
    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        int direction = isWhite() ? -1 : 1;  // Vit går uppåt, svart nedåt
        int startRow = isWhite() ? 6 : 1;

        // Framåt
        if (from.col() == to.col()) {
            // Enkel förflyttning
            if (to.row() - from.row() == direction && board.getPiece(to) == null) {
                return true;
            }
            // Dubbeldrag från startposition
            if (from.row() == startRow && to.row() - from.row() == 2 * direction) {
                Position middle = new Position(from.row() + direction, from.col());
                return board.getPiece(middle) == null && board.getPiece(to) == null;
            }
        }

        // Slag diagonalt
        if (Math.abs(from.col() - to.col()) == 1 && to.row() - from.row() == direction) {
            Piece target = board.getPiece(to);
            return target != null && target.isWhite() != this.isWhite();
        }

        return false;
    }

    @Override
    public String toString() {
        return isWhite() ? "♙" : "♟";  // Vit/svart bonde
    }
}
