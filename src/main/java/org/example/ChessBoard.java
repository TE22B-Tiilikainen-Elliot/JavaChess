package org.example;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> pieces = new HashMap<>();

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    public void placePiece(Piece piece) {
        pieces.put(piece.getPosition(), piece);
    }

    public void movePiece(Position from, Position to) {
        Piece piece = pieces.remove(from);
        if (piece != null) {
            piece.move(to);
            pieces.put(to, piece);
        }
    }

    public boolean isSquareOccupied(Position position) {
        return pieces.containsKey(position);
    }
}