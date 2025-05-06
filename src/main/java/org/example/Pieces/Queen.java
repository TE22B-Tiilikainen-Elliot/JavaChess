package org.example.Pieces;

import org.example.Piece;
import org.example.Position;
import org.example.ChessBoard;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        // Queen combines rook and bishop moves
        // Check if it's a straight or diagonal move
        boolean isStraight = from.getRow() == to.getRow() || from.getCol() == to.getCol();
        boolean isDiagonal = Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getCol() - to.getCol());

        if (!isStraight && !isDiagonal) {
            return false;
        }

        // Check if path is clear
        int rowStep = Integer.compare(to.getRow(), from.getRow());
        int colStep = Integer.compare(to.getCol(), from.getCol());

        int row = from.getRow() + rowStep;
        int col = from.getCol() + colStep;

        while (row != to.getRow() || col != to.getCol()) {
            if (board.getPiece(new Position(row, col)) != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }

        // Check destination
        Piece target = board.getPiece(to);
        return target == null || target.isWhite() != this.isWhite();
    }


    @Override
    public String toString() {
        return isWhite() ? "♕" : "♛";
    }
}