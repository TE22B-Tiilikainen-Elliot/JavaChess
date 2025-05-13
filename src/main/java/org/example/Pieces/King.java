package org.example.Pieces;

import org.example.Position;
import org.example.ChessBoard;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    // Kungen kan flytta ett steg i vilken riktning som helst
    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        int rowDiff = Math.abs(to.row() - from.row());
        int colDiff = Math.abs(to.col() - from.col());

        if (rowDiff <= 1 && colDiff <= 1) {
            Piece target = board.getPiece(to);
            return target == null || target.isWhite() != this.isWhite();
        }
        return false;
    }

    @Override
    public String toString() {
        return isWhite() ? "♔" : "♚";  // Vit/svart kung
    }
}