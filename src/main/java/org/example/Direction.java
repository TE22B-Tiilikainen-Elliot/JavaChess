package org.example;

// Beskriver en rörelseriktning på brädet (hur många steg i rad/kolumn)
public record Direction(int rowDelta, int colDelta) {
    // Exempel:
    // new Direction(1,0) = ett steg neråt
    // new Direction(-1,1) = ett steg upp och höger
}