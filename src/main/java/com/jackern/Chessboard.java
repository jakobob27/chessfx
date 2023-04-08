package com.jackern;

import java.util.ArrayList;

public class Chessboard {
    private ArrayList<BoardListener> listeners = new ArrayList<>();
    private ArrayList<ArrayList<ChessPiece>> board = new ArrayList<>();
    private Player white;
    private Player black;
    private boolean turn = true; // true when it's white's turn, false when it's black's
    private ChessPiece selectedPiece;

    public Chessboard(Player white, Player black) {
        if (!(white instanceof White || black instanceof Black)) {
            throw new IllegalArgumentException("Not valid players!");
        }
        this.white = white;
        this.black = black;
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<ChessPiece>());
            for (int j = 0; j < 8; j++) {
                board.get(i).add(null);
            }
        }
    }

    public void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.get(i).set(j, null);
            }
        }

        addPiece(0, 0, new Rook(this, "black"));
        addPiece(1, 0, new Knight(this, "black"));
        addPiece(2, 0, new Bishop(this, "black"));
        addPiece(3, 0, new Queen(this, "black"));
        addPiece(4, 0, new King(this, "black"));
        addPiece(5, 0, new Bishop(this, "black"));
        addPiece(6, 0, new Knight(this, "black"));
        addPiece(7, 0, new Rook(this, "black"));

        for (int i = 0; i < 8; i++) {
            addPiece(i, 1, new Pawn(this, "black"));
        }

        addPiece(0, 7, new Rook(this, "white"));
        addPiece(1, 7, new Knight(this, "white"));
        addPiece(2, 7, new Bishop(this, "white"));
        addPiece(3, 7, new Queen(this, "white"));
        addPiece(4, 7, new King(this, "white"));
        addPiece(5, 7, new Bishop(this, "white"));
        addPiece(6, 7, new Knight(this, "white"));
        addPiece(7, 7, new Rook(this, "white"));

        for (int i = 0; i < 8; i++) {
            addPiece(i, 6, new Pawn(this, "white"));
        }
        updateListeners();
    }

    public ArrayList<ArrayList<ChessPiece>> getBoardCopy() {
        ArrayList<ArrayList<ChessPiece>> boardcopy = board;
        return boardcopy;
    }

    public void updateListeners() {
        for (ArrayList<ChessPiece> list : board) {
            for (ChessPiece piece : list) {
                if (piece != null) {
                    for (BoardListener listener : listeners) {
                        listener.update(piece);
                    }
                }
            }
        }
        MainSceneController.updateCompleted();
    }

    public void addPiece(int xpos, int ypos, ChessPiece piece) {
        if (!(board.get(xpos).get(ypos) == null)) {
            throw new IllegalArgumentException("Space is occupied");
        }
        board.get(xpos).set(ypos, piece);
        piece.setXPos(xpos);
        piece.setYPos(ypos);
        if (piece.getColor().equals("white")) {
            white.addPiece(piece);
        } else if (piece.getColor().equals("black")) {
            black.addPiece(piece);
        }
    }

    public void movePiece(ChessPiece piece, int xpos, int ypos) {
        board.get(piece.getXPos()).set(piece.getYPos(), null);
        board.get(xpos).set(ypos, piece);
        piece.setXPos(xpos);
        piece.setYPos(ypos);
        updateListeners();
    }

    public ChessPiece getPiece(int xpos, int ypos) {
        return board.get(xpos).get(ypos);
    }

    public void selectPiece(ChessPiece piece) {
        this.selectedPiece = piece;
    }

    public void addListener(BoardListener listener) {
        listeners.add(listener);
    }

    public void removeListener(BoardListener listener) {
        listeners.remove(listener);
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public ChessPiece getSelectedPiece() {
        return selectedPiece;
    }

}
