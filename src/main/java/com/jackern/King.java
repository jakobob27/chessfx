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

        else if (!hasMoved() && xpos == 2 && !board.getPiece(0, getYPos()).hasMoved()) {
            for (int i = 0; i < 3; i++) {
                if (board.selfCheck(this, getXPos() - i, ypos)) {
                    return false;
                }
            }
            board.castle(board.getPiece(0, ypos), 3, ypos);
            return true;
        }

        else if (!hasMoved() && xpos == 6 && !board.getPiece(7, getYPos()).hasMoved()) {
            for (int i = 0; i < 3; i++) {
                if (board.selfCheck(this, getXPos() + i, ypos)) {
                    return false;
                }
            }
            board.castle(board.getPiece(7, ypos), 5, ypos);
            return true;

        }

        return false;
    }

}