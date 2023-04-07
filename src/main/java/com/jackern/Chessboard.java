package com.jackern;

import java.util.ArrayList;

public class Chessboard {
    private ArrayList<BoardListener> listeners = new ArrayList<>();
    private ArrayList<ArrayList<ChessPiece>> board = new ArrayList<>();
    private Player white;
    private Player black;
    private char turn = 'w';

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

        board.get(0).set(0, new Rook("black"));
        board.get(1).set(0, new Knight("black"));
        board.get(2).set(0, new Bishop("black"));
        board.get(3).set(0, new Queen("black"));
        board.get(4).set(0, new King("black"));
        board.get(5).set(0, new Bishop("black"));
        board.get(6).set(0, new Knight("black"));
        board.get(7).set(0, new Rook("black"));

        for (int i = 0; i < 8; i++) {
            board.get(i).set(1, new Pawn("black"));
        }

        board.get(0).set(7, new Rook("white"));
        board.get(1).set(7, new Knight("white"));
        board.get(2).set(7, new Bishop("white"));
        board.get(3).set(7, new Queen("white"));
        board.get(4).set(7, new King("white"));
        board.get(5).set(7, new Bishop("white"));
        board.get(6).set(7, new Knight("white"));
        board.get(7).set(7, new Rook("white"));

        for (int i = 0; i < 8; i++) {
            board.get(i).set(6, new Pawn("white"));
        }
    }

    public ArrayList<ArrayList<ChessPiece>> getBoardCopy() {
        ArrayList<ArrayList<ChessPiece>> boardcopy = board;
        return boardcopy;
    }

    public void updateListeners() {
        for (ArrayList<ChessPiece> list : board) {
            for (ChessPiece piece : list) {
                for (BoardListener listener : listeners) {
                    listener.update(piece);
                }
            }
        }
    }

    public void addListener(BoardListener listener) {
        listeners.add(listener);
    }

    public void removeListener(BoardListener listener) {
        listeners.remove(listener);
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }
}
