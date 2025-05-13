// Rook.java
package org.example.Pieces;

import org.example.Direction;
import java.util.List;

public class Rook extends SlidingPiece {
    public Rook(boolean white) {
        super(white);
    }

    // Tornet rör sig enbart rakt (horisontellt/vertikalt)
    @Override
    protected List<Direction> getSlideDirections() {
        return List.of(
                new Direction(1, 0),    // Nedåt
                new Direction(-1, 0),   // Uppåt
                new Direction(0, 1),    // Höger
                new Direction(0, -1)    // Vänster
        );
    }

    @Override
    public String toString() {
        return isWhite() ? "♖" : "♜";  // Vit/svart torn
    }
}