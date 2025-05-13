package org.example.Pieces;

import org.example.Position;
import org.example.ChessBoard;
import org.example.Direction;
import java.util.ArrayList;
import java.util.List;

// Grundklass för pjäser som glider (torn, löpare, dam)
public abstract class SlidingPiece extends Piece {
    // Varje pjästyp måste ange vilka riktningar den kan röra sig i
    protected abstract List<Direction> getSlideDirections();

    public SlidingPiece(boolean white) {
        super(white);
    }

    // Kollar om ett drag är giltigt för denna pjäs
    @Override
    public boolean isValidMove(Position from, Position to, ChessBoard board) {
        List<Position> validMoves = calculateValidMoves(from, board);
        return validMoves.contains(to);
    }

    // Beräknar alla giltiga drag pjäsen kan göra från aktuell position
    protected List<Position> calculateValidMoves(Position current, ChessBoard board) {
        List<Position> validMoves = new ArrayList<>();

        // Gå igenom alla riktningar pjäsen kan röra sig i
        for (Direction dir : getSlideDirections()) {
            Position next = current.add(dir);

            // Fortsätt i samma riktning tills kanten på brädet nås
            while (board.isValidPosition(next)) {
                Piece target = board.getPiece(next);

                // Om rutan är tom - lägg till som giltigt drag
                if (target == null) {
                    validMoves.add(next);
                } else {
                    // Om det står en motståndarpjäs - lägg till och avbryt
                    if (target.isWhite() != this.isWhite()) {
                        validMoves.add(next);
                    }
                    break; // Avbryt i denna riktning
                }
                next = next.add(dir); // Gå till nästa ruta i samma riktning
            }
        }
        return validMoves;
    }
}