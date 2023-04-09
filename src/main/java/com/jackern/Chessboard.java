package com.jackern;

import java.util.ArrayList;

public class Chessboard {
    private ArrayList<BoardListener> listeners = new ArrayList<>();
    private ArrayList<ArrayList<ChessPiece>> board = new ArrayList<>();
    private ChessPiece whiteKing;
    private ChessPiece blackKing;
    private boolean turn = true; // true when it's white's turn, false when it's black's
    private ChessPiece selectedPiece;
    private boolean enpessant;

    public Chessboard() {
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
        addPiece(4, 0, blackKing = new King(this, "black"));
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
        addPiece(4, 7, whiteKing = new King(this, "white"));
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
    }

    public void removePiece(ChessPiece piece) {
        board.get(piece.getXPos()).set(piece.getYPos(), null);
    }

    public void movePiece(ChessPiece piece, int xpos, int ypos) {
        if (piece.getXPos() == xpos && piece.getYPos() == ypos) {
            return;
        } else if (piece.friendlyfire(xpos, ypos) || piece.collision(xpos, ypos)
                || selfCheck(piece, xpos, ypos) || !piece.validMove(xpos, ypos)) {
            System.out.println("Invalid move!");
            return;
        }
        if (enpessant) {
            enpessant(piece, xpos, ypos);
            enpessant = false;
        }
        board.get(piece.getXPos()).set(piece.getYPos(), null);
        board.get(xpos).set(ypos, piece);
        piece.setXPos(xpos);
        piece.setYPos(ypos);
        for (ArrayList<ChessPiece> list : board) {
            for (ChessPiece i : list) {
                if (i != null && !i.equals(piece)) {
                    i.didnotMove();
                }
            }
        }
        piece.moved();
        promoteChecker(piece);
        turn = !turn;
        if (turn && whiteCheckChecker()) {
            System.out.println("White is in check!");
        } else if (!turn && blackCheckChecker()) {
            System.out.println("Black is in check!");
        }
        updateListeners();
    }

    public ChessPiece getPiece(int xpos, int ypos) {
        return board.get(xpos).get(ypos);
    }

    public void selectPiece(ChessPiece piece) {
        if (piece != null && (!turn && piece.getColor().equals("white") || turn && piece.getColor().equals("black"))) {
            System.out.println("Not your turn!");
            return;
        }
        this.selectedPiece = piece;
    }

    public boolean selfCheck(ChessPiece piece, int xpos, int ypos) {
        ChessPiece newPositionPiece = board.get(xpos).get(ypos);
        int xprev = piece.getXPos();
        int yprev = piece.getYPos();

        board.get(piece.getXPos()).set(piece.getYPos(), null);
        board.get(xpos).set(ypos, piece);
        piece.setXPos(xpos);
        piece.setYPos(ypos);

        if (turn && whiteCheckChecker()) {
            board.get(xpos).set(ypos, newPositionPiece);
            board.get(xprev).set(yprev, piece);
            piece.setXPos(xprev);
            piece.setYPos(yprev);
            return true;
        }

        else if (!turn && blackCheckChecker()) {
            board.get(xpos).set(ypos, newPositionPiece);
            board.get(xprev).set(yprev, piece);
            piece.setXPos(xprev);
            piece.setYPos(yprev);
            return true;
        }

        board.get(xpos).set(ypos, newPositionPiece);
        board.get(xprev).set(yprev, piece);
        piece.setXPos(xprev);
        piece.setYPos(yprev);

        return false;

    }

    public boolean whiteCheckChecker() {
        for (ArrayList<ChessPiece> list : board) {
            for (ChessPiece piece : list) {
                if (piece instanceof ChessPiece) {
                    if ((piece instanceof King) && piece.getColor().equals("black")) {
                        if (piece.getXPos() == whiteKing.getXPos() + 1
                                && (piece.getYPos() == whiteKing.getYPos() || piece.getYPos() == whiteKing.getYPos() + 1
                                        || piece.getYPos() == whiteKing.getYPos() - 1)) {
                            return true;
                        }

                        else if (piece.getXPos() == whiteKing.getXPos() && (piece.getYPos() == whiteKing.getYPos() + 1
                                || piece.getYPos() == whiteKing.getYPos() - 1)) {
                            return true;
                        }

                        else if (piece.getXPos() == whiteKing.getXPos() - 1
                                && (piece.getYPos() == whiteKing.getYPos() || piece.getYPos() == whiteKing.getYPos() + 1
                                        || piece.getYPos() == whiteKing.getYPos() - 1)) {
                            return true;
                        }
                    } else if (piece.getColor().equals("black")
                            && piece.validMove(whiteKing.getXPos(), whiteKing.getYPos())
                            && !piece.collision(whiteKing.getXPos(), whiteKing.getYPos())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean blackCheckChecker() {
        for (ArrayList<ChessPiece> list : board) {
            for (ChessPiece piece : list) {
                if (piece instanceof ChessPiece) {
                    if ((piece instanceof King) && piece.getColor().equals("white")) {
                        if (piece.getXPos() == blackKing.getXPos() + 1
                                && (piece.getYPos() == blackKing.getYPos() || piece.getYPos() == blackKing.getYPos() + 1
                                        || piece.getYPos() == blackKing.getYPos() - 1)) {
                            return true;
                        }

                        else if (piece.getXPos() == blackKing.getXPos() && (piece.getYPos() == blackKing.getYPos() + 1
                                || piece.getYPos() == blackKing.getYPos() - 1)) {
                            return true;
                        }

                        else if (piece.getXPos() == blackKing.getXPos() - 1
                                && (piece.getYPos() == blackKing.getYPos() || piece.getYPos() == blackKing.getYPos() + 1
                                        || piece.getYPos() == blackKing.getYPos() - 1)) {
                            return true;
                        }
                    } else if (piece.getColor().equals("white")
                            && piece.validMove(blackKing.getXPos(), blackKing.getYPos())
                            && !piece.collision(blackKing.getXPos(), blackKing.getYPos())) {
                        return true;
                    }
                }
            }
        }

        return false;
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

    public ChessPiece getWhiteKing() {
        return whiteKing;
    }

    public ChessPiece getBlackKing() {
        return blackKing;
    }

    public void castle(ChessPiece rook, int xpos, int ypos) {
        board.get(rook.getXPos()).set(rook.getYPos(), null);
        board.get(xpos).set(ypos, rook);
        rook.setXPos(xpos);
        rook.setYPos(ypos);
        rook.moved();
    }

    private void promoteChecker(ChessPiece piece) {
        if (((piece instanceof Pawn) && piece.getColor().equals("black") && piece.getYPos() == 7)
                || ((piece instanceof Pawn) && piece.getColor().equals("white") && piece.getYPos() == 0)) {
            MainSceneController.startPromotion(piece);
        }
    }

    public void promoteToQueen(ChessPiece piece) {
        removePiece(piece);
        addPiece(piece.getXPos(), piece.getYPos(), new Queen(this, piece.getColor()));
        updateListeners();
    }

    public void promoteToRook(ChessPiece piece) {
        removePiece(piece);
        addPiece(piece.getXPos(), piece.getYPos(), new Rook(this, piece.getColor()));
        updateListeners();
    }

    public void promoteToBishop(ChessPiece piece) {
        removePiece(piece);
        addPiece(piece.getXPos(), piece.getYPos(), new Bishop(this, piece.getColor()));
        updateListeners();
    }

    public void promoteToKnight(ChessPiece piece) {
        removePiece(piece);
        addPiece(piece.getXPos(), piece.getYPos(), new Knight(this, piece.getColor()));
        updateListeners();
    }

    public void startEnpessant() {
        enpessant = true;
    }

    public void enpessant(ChessPiece piece, int xpos, int ypos) {
        System.out.println("EN PESSANT!");
        if (piece.getColor().equals("white")) {
            removePiece(getPiece(xpos, ypos + 1));
        }

        else {
            removePiece(getPiece(xpos, ypos - 1));
        }
    }
}
