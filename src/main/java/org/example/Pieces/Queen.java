// Queen.java
package org.example.Pieces;

import org.example.Direction;
import java.util.List;

public class Queen extends SlidingPiece {
    public Queen(boolean white) {
        super(white);
    }

    // Damen kan röra sig i alla riktningar - både rakt och diagonalt
    @Override
    protected List<Direction> getSlideDirections() {
        return List.of(
                new Direction(1, 0),    // Nedåt
                new Direction(-1, 0),   // Uppåt
                new Direction(0, 1),     // Höger
                new Direction(0, -1),    // Vänster
                new Direction(1, 1),    // Nedåt höger
                new Direction(1, -1),   // Nedåt vänster
                new Direction(-1, 1),    // Uppåt höger
                new Direction(-1, -1)   // Uppåt vänster
        );
    }

    @Override
    public String toString() {
        return isWhite() ? "♕" : "♛";  // Vit/svart dam
    }
}