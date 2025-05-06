package org.example.Pieces;

import org.example.Piece;
import org.example.Position;
import org.example.ChessBoard;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        // Bishops move diagonally (equal row and column differences)
        if (Math.abs(from.getRow() - to.getRow()) != Math.abs(from.getCol() - to.getCol())) {
            return false;
        }

        // Check if path is clear
        int rowStep = from.getRow() < to.getRow() ? 1 : -1;
        int colStep = from.getCol() < to.getCol() ? 1 : -1;

        int row = from.getRow() + rowStep;
        int col = from.getCol() + colStep;

        while (row != to.getRow() && col != to.getCol()) {
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
        return isWhite() ? "♗" : "♝";
    }
}