package com.jackern;

public class Knight extends ChessPiece {

    public Knight(Chessboard board, String color) {
        super(board, "knight", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validMove'");
    }

    @Override
    public boolean collision(int xpos, int ypos) {
        return false;
    }

}