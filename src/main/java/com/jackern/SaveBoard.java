package com.jackern;

import java.io.Serializable;

public class SaveBoard implements Serializable {
    private Chessboard board;

    public SaveBoard(Chessboard board) {
        this.board = board;
    }

    public Chessboard getBoard() {
        return board;
    }
}
