package com.jackern;

public class Pawn extends ChessPiece {

    public Pawn(Chessboard board, String color) {
        super(board, "pawn", color);
    }

    @Override
    public boolean validMove(int xpos, int ypos) {
        if (getColor().equals("white") && (ypos == getYPos() - 1 || ypos == getYPos() - 2 && getYPos() == 6)
                && (xpos == getXPos() || (xpos == getXPos() + 1 || xpos == getXPos() - 1)
                        && board.getBoardCopy().get(xpos).get(ypos) != null
                        && ypos == getYPos() - 1)) {
            return true;
        }

        else if (getColor().equals("black") && (ypos == getYPos() + 1 || ypos == getYPos() + 2 && getYPos() == 1)
                && (xpos == getXPos() || (xpos == getXPos() + 1 || xpos == getXPos() - 1)
                        && board.getBoardCopy().get(xpos).get(ypos) != null
                        && ypos == getYPos() - 1)) {
            return true;
        }
        return false;
    }

}