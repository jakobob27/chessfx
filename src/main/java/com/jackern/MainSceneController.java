package com.jackern;

import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainSceneController implements BoardListener {

    private White white;
    private Black black;
    private Chessboard board;
    private static boolean updateComplete;

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        white = new White();
        black = new Black();
        board = new Chessboard(white, black);
        board.addListener(this);
        initBoardClick();
        board.init();
    }

    private void initBoardClick() {
        for (Node child : grid.getChildren()) {
            if (child instanceof Pane) {
                child.setOnMouseClicked(event -> {
                    if (board.getSelectedPiece() != null) {
                        int ypos = GridPane.getRowIndex(child);
                        int xpos = GridPane.getColumnIndex(child);
                        board.movePiece(board.getSelectedPiece(), xpos, ypos);
                        board.selectPiece(null);
                    }
                });
            }
        }
    }

    private void addImage(ChessPiece piece) {
        Image png = new Image(
                MainSceneController.class.getResource(piece.getColor() + "-" + piece.getID() + ".png").toString());
        ImageView view = new ImageView(png);
        view.setOnMouseClicked((MouseEvent e) -> {
            if (board.getSelectedPiece() == null) {
                board.selectPiece(piece);
            } else {
                board.movePiece(board.getSelectedPiece(), piece.getXPos(), piece.getYPos());
                board.selectPiece(null);
            }
            System.out.println(board.getSelectedPiece());
        });
        ;
        grid.add(view, piece.getXPos(), piece.getYPos());
        GridPane.setHalignment(view, HPos.CENTER);
        GridPane.setValignment(view, VPos.CENTER);
        view.setFitWidth(100);
        view.setFitHeight(100);
    }

    @FXML
    public void resetImages() {
        Iterator<Node> iterator = grid.getChildren().iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof ImageView) {
                iterator.remove();
            }
        }
    }

    @FXML
    @Override
    public void update(ChessPiece piece) {
        if (updateComplete == true) {
            resetImages();
            updateStarted();
        }
        addImage(piece);
    }

    public static void updateCompleted() {
        updateComplete = true;
    }

    public static void updateStarted() {
        updateComplete = false;
    }

}
