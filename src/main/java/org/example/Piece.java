package org.example;

public abstract class Piece {
    protected boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public abstract boolean isValidMove(Position from, Position to, ChessBoard board);

    @Override
    public abstract String toString();
}