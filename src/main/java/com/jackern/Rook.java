package com.jackern;

public class Rook extends ChessPiece {

    public Rook(Chessboard board, String color) {
        super(board, "rook", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        if ((xpos > getXPos() || xpos < getXPos()) && ypos == getYPos()) {
            return true;
        }

        else if (xpos == getXPos() && (ypos > getYPos() || ypos < getYPos())) {
            return true;
        }

        return false;
    }

}
