package com.jackern.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.jackern.BoardListener;
import com.jackern.ChessPiece;
import com.jackern.Chessboard;
import com.jackern.MainSceneController;
import com.jackern.SaveBoard;

public class SaveBoardTest implements BoardListener {
    private Chessboard board = new Chessboard();

    @Test
    public void saveBoardTest() {
        board.addListener(this);
        board.init();
        board.movePiece(board.getPiece(0, 6), 0, 5);
        MainSceneController.saveBoardState(board);
        MainSceneController.loadBoardState();

        SaveBoard savedBoard = MainSceneController.loadBoardState();

        assertEquals(board.getPiece(0, 5).toString(), savedBoard.getBoard().getPiece(0, 5).toString());

    }

    @Override
    public void update(ChessPiece piece) {

    }
}
