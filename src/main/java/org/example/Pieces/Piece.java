package org.example.Pieces;

import org.example.ChessBoard;
import org.example.Position;

// Grundklass för alla schackpjäser
public abstract class Piece {
    protected boolean white;  // true = vit, false = svart

    public Piece(boolean white) {
        this.white = white;
    }

    // Returnerar true om pjäsen är vit
    public boolean isWhite() {
        return white;
    }

    // Kollar om draget från 'from' till 'to' är giltigt för denna pjäs
    public abstract boolean isValidMove(Position from, Position to, ChessBoard board);

    // Returnerar symbol för pjäsen (t.ex. "♘" för springare)
    @Override
    public abstract String toString();
}