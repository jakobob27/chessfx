package com.jackern;

public class White extends Player {
    public White() {
        super();
    }

    @Override
    public void update(ChessPiece piece) {
        if (piece.getColor().equals("white")) {
            pieces.add(piece);
        }
    }
}
