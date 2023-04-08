package com.jackern;

public class Bishop extends ChessPiece {

    public Bishop(Chessboard board, String color) {
        super(board, "bishop", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validMove'");
    }

}