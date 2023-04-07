package com.jackern.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.jackern.Bishop;
import com.jackern.Black;
import com.jackern.BoardListener;
import com.jackern.ChessPiece;
import com.jackern.Chessboard;
import com.jackern.King;
import com.jackern.Knight;
import com.jackern.Pawn;
import com.jackern.Queen;
import com.jackern.Rook;
import com.jackern.White;

public class BoardTest implements BoardListener {
    private Chessboard board;
    private boolean checker = false;

    @BeforeEach
    public void init() {
        checker = false;
        White white = new White();
        Black black = new Black();
        board = new Chessboard(white, black);
    }

    @Test
    public void BoardConstructorTest() {
        assertEquals(8, board.getBoardCopy().size());
        assertEquals(8, board.getBoardCopy().get(0).size());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertNull(board.getBoardCopy().get(i).get(j));
            }
        }
    }

    @Test
    public void BoardInitTest() {

        board.init();

        assertTrue(board.getBoardCopy().get(0).get(0) instanceof Rook);
        assertTrue(board.getBoardCopy().get(1).get(0) instanceof Knight);
        assertTrue(board.getBoardCopy().get(2).get(0) instanceof Bishop);
        assertTrue(board.getBoardCopy().get(3).get(0) instanceof Queen);
        assertTrue(board.getBoardCopy().get(4).get(0) instanceof King);
        assertTrue(board.getBoardCopy().get(5).get(0) instanceof Bishop);
        assertTrue(board.getBoardCopy().get(6).get(0) instanceof Knight);
        assertTrue(board.getBoardCopy().get(7).get(0) instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board.getBoardCopy().get(i).get(1) instanceof Pawn);
        }

        assertTrue(board.getBoardCopy().get(0).get(7) instanceof Rook);
        assertTrue(board.getBoardCopy().get(1).get(7) instanceof Knight);
        assertTrue(board.getBoardCopy().get(2).get(7) instanceof Bishop);
        assertTrue(board.getBoardCopy().get(3).get(7) instanceof Queen);
        assertTrue(board.getBoardCopy().get(4).get(7) instanceof King);
        assertTrue(board.getBoardCopy().get(5).get(7) instanceof Bishop);
        assertTrue(board.getBoardCopy().get(6).get(7) instanceof Knight);
        assertTrue(board.getBoardCopy().get(7).get(7) instanceof Rook);

        for (int i = 0; i < 8; i++) {
            assertTrue(board.getBoardCopy().get(i).get(6) instanceof Pawn);
        }
    }

    @Test
    public void ListenerTest() {
        board.addListener(this);
        board.updateListeners();
        assertTrue(checker);
    }

    @Override
    public void update(ChessPiece piece) {
        checker = true;
    }
}
