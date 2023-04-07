package com.jackern;

import java.util.ArrayList;

public abstract class Player implements BoardListener {
    protected ArrayList<ChessPiece> pieces = new ArrayList<>();

    public Player() {
    }

    public void addPiece(ChessPiece piece) {
        pieces.add(piece);
    }

    @Override
    public abstract void update(ChessPiece piece);
}
