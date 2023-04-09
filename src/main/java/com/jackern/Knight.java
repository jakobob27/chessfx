package com.jackern;

public class Knight extends ChessPiece {

    public Knight(Chessboard board, String color) {
        super(board, "knight", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        if (xpos == getXPos() + 2 && (ypos == getYPos() + 1 || ypos == getYPos() - 1)) {
            return true;
        }

        else if (xpos == getXPos() - 2 && (ypos == getYPos() + 1 || ypos == getYPos() - 1)) {
            return true;
        }

        else if (ypos == getYPos() + 2 && (xpos == getXPos() + 1 || xpos == getXPos() - 1)) {
            return true;
        }

        else if (ypos == getYPos() - 2 && (xpos == getXPos() + 1 || xpos == getXPos() - 1)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean collision(int xpos, int ypos) {
        return false;
    }

}