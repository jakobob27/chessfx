package com.jackern;

public class Black extends Player {

    public Black() {
        super();
    }

    @Override
    public void update(ChessPiece piece) {
        if (piece.getColor().equals("black")) {
            pieces.add(piece);
        }
    }

}
