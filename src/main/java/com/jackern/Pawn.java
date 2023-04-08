package com.jackern;

public class Pawn extends ChessPiece {

    public Pawn(Chessboard board, String color) {
        super(board, "pawn", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validMove'");
    }

}