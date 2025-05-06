package org.example.Pieces;

import org.example.Piece;
import org.example.Position;
import org.example.ChessBoard;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        // Rooks move in straight lines (same row or same column)
        if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
            return false;
        }

        // Check if path is clear
        if (from.getRow() == to.getRow()) {
            // Horizontal move
            int colStep = from.getCol() < to.getCol() ? 1 : -1;
            for (int col = from.getCol() + colStep; col != to.getCol(); col += colStep) {
                if (board.getPiece(new Position(from.getRow(), col)) != null) {
                    return false;
                }
            }
        } else {
            // Vertical move
            int rowStep = from.getRow() < to.getRow() ? 1 : -1;
            for (int row = from.getRow() + rowStep; row != to.getRow(); row += rowStep) {
                if (board.getPiece(new Position(row, from.getCol())) != null) {
                    return false;
                }
            }
        }

        // Check destination
        Piece target = board.getPiece(to);
        return target == null || target.isWhite() != this.isWhite();
    }

    @Override
    public String toString() {
        return isWhite() ? "♖" : "♜";
    }
}