package com.jackern;

public abstract class ChessPiece {
    private String color;
    private String id;
    private int value;
    private int xpos;
    private int ypos;
    protected Chessboard board;

    public abstract boolean validMove(int xpos, int ypos);

    public ChessPiece(Chessboard board, String id, String color) {
        this.board = board;
        validPiece(id, color);
        this.id = id;
        this.color = color;
        switch (this.id) {
            case "king":
                value = 0;
            case "queen":
                value = 9;
                break;
            case "rook":
                value = 5;
                break;
            case "bishop":
            case "knight":
                value = 3;
                break;
            case "pawn":
                value = 1;
                break;
            default:
                throw new IllegalArgumentException("Not a valid piece!");
        }
    }

    private void validPiece(String id, String color) {
        if (!(id.equals("king") || id.equals("queen") || id.equals("rook") || id.equals("bishop") || id.equals("knight")
                || id.equals("pawn"))) {
            throw new IllegalArgumentException("Not a valid ID!");
        }
        if (!(color.equals("black") || color.equals("white"))) {
            throw new IllegalArgumentException("Not a valid piece color!");
        }
    }

    protected boolean friendlyfire(int xpos, int ypos) {
        if (board.getPiece(xpos, ypos) == null) {
            return false;
        }

        else if (board.getPiece(xpos, ypos).getColor().equals(this.getColor())) {
            return true;
        }

        else {
            return false;
        }
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public String getID() {
        return id;
    }

    public int getXPos() {
        return xpos;
    }

    public int getYPos() {
        return ypos;
    }

    public void setXPos(int xpos) {
        this.xpos = xpos;
    }

    public void setYPos(int ypos) {
        this.ypos = ypos;
    }

    @Override
    public String toString() {
        return color + " " + id + " " + xpos + " " + ypos;
    }
}
