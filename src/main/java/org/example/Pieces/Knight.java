package org.example.Pieces;

import org.example.Piece;
import org.example.Position;
import org.example.ChessBoard;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        // Knights move in L-shape (2 squares in one direction, then 1 square perpendicular)
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }

        // Check destination
        Piece target = board.getPiece(to);
        return target == null || target.isWhite() != this.isWhite();
    }

    @Override
    public String toString() {
        return isWhite() ? "♘" : "♞";
    }
}