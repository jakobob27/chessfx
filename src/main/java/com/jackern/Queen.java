package com.jackern;

public class Queen extends ChessPiece {

    public Queen(Chessboard board, String color) {
        super(board, "queen", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        int xdiff = Math.abs(xpos - getXPos());
        int ydiff = Math.abs(ypos - getYPos());

        if (xdiff == ydiff) {
            return true;
        }

        else if ((xpos > getXPos() || xpos < getXPos()) && ypos == getYPos()) {
            return true;
        }

        else if (xpos == getXPos() && (ypos > getYPos() || ypos < getYPos())) {
            return true;
        }

        return false;
    }

}