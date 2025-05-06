package org.example.Pieces;

import org.example.Piece;
import org.example.Position;
import org.example.ChessBoard;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        // Normal king move
        if (rowDiff <= 1 && colDiff <= 1) {
            Piece target = board.getPiece(to);
            return target == null || target.isWhite() != this.isWhite();
        }

        return false;
    }


    @Override
    public String toString() {
        return isWhite() ? "♔" : "♚";
    }
}