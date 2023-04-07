package com.jackern;

public abstract class ChessPiece {
    private String color;
    private String id;
    private int value;

    public abstract boolean validMove(int xpos, int ypos);

    public ChessPiece(String id, String color) {
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

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

}