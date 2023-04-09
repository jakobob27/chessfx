package com.jackern;

public class King extends ChessPiece {

    public King(Chessboard board, String color) {
        super(board, "king", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        if (xpos == getXPos() + 1 && (ypos == getYPos() || ypos == getYPos() + 1 || ypos == getYPos() - 1)) {
            return true;
        }

        else if (xpos == getXPos() && (ypos == getYPos() + 1 || ypos == getYPos() - 1)) {
            return true;
        }

        else if (xpos == getXPos() - 1 && (ypos == getYPos() || ypos == getYPos() + 1 || ypos == getYPos() - 1)) {
            return true;
        }

        return false;
    }

}