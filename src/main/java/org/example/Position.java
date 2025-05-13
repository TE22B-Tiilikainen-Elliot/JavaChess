package org.example;

// Representerar en ruta på schackbrädet (rad, kolumn)
public record Position(int row, int col) {

    // Flyttar positionen i given riktning och returnerar ny position
    public Position add(Direction dir) {
        return new Position(row + dir.rowDelta(), col + dir.colDelta());
    }
}