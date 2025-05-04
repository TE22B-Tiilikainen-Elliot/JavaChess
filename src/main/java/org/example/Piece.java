package org.example;

public class Piece {
    private final int type;

    public Piece(int type) {
        this.type = type;
    }

    public boolean isWhite() {
        if (type >= 1 && type <= 6){
            return true;
        }
        else{
            return false;
        }
    }

    public int getType() {
        return type;
    }
}