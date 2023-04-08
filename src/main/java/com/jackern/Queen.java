package com.jackern;

public class Queen extends ChessPiece {

    public Queen(Chessboard board, String color) {
        super(board, "queen", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validMove'");
    }

}