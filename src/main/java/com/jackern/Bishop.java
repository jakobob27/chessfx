package com.jackern;

public class Bishop extends ChessPiece {

    public Bishop(Chessboard board, String color) {
        super(board, "bishop", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        int xdiff = Math.abs(xpos - getXPos());
        int ydiff = Math.abs(ypos - getYPos());

        if (xdiff == ydiff) {
            return true;
        }

        return false;
    }

}