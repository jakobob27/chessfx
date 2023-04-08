package com.jackern;

public class King extends ChessPiece {

    public King(Chessboard board, String color) {
        super(board, "king", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validMove'");
    }

}