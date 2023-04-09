package com.jackern;

public class Pawn extends ChessPiece {

    public Pawn(Chessboard board, String color) {
        super(board, "pawn", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        if (getColor().equals("white") && (ypos == getYPos() - 1 || ypos == getYPos() - 2 && !hasMoved())
                && (xpos == getXPos() && board.getPiece(xpos, ypos) == null
                        || (xpos == getXPos() + 1 || xpos == getXPos() - 1)
                                && board.getPiece(xpos, ypos) != null
                                && ypos == getYPos() - 1)) {
            return true;
        }

        else if (getColor().equals("black") && (ypos == getYPos() + 1 || ypos == getYPos() + 2 && !hasMoved())
                && (xpos == getXPos() && board.getPiece(xpos, ypos) == null
                        || (xpos == getXPos() + 1 || xpos == getXPos() - 1)
                                && board.getBoardCopy().get(xpos).get(ypos) != null
                                && ypos == getYPos() + 1)) {
            return true;
        }
        return false;
    }

}