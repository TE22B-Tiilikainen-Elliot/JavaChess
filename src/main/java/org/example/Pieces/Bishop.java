// Bishop.java
package org.example.Pieces;

import org.example.Direction;
import java.util.List;

public class Bishop extends SlidingPiece {
    public Bishop(boolean white) {
        super(white);
    }

    // Löparen rör sig diagonalt i alla fyra riktningar
    @Override
    protected List<Direction> getSlideDirections() {
        return List.of(
                new Direction(1, 1),    // Nedåt höger
                new Direction(1, -1),   // Nedåt vänster
                new Direction(-1, 1),    // Uppåt höger
                new Direction(-1, -1)   // Uppåt vänster
        );
    }

    @Override
    public String toString() {
        return isWhite() ? "♗" : "♝";  // Vit/svart löpare
    }
}