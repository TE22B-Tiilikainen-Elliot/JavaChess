package org.example;

import java.util.List;

public abstract class Piece {
    protected Position position;
    protected final boolean isWhite;

    public Piece(Position position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract List<Position> getValidMoves();

    public void move(Position newPosition) {
        this.position = newPosition;
    }
}