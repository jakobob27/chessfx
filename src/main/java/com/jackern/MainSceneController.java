package com.jackern;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainSceneController implements BoardListener {

    private White white;
    private Black black;
    private Chessboard board;

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        white = new White();
        black = new Black();
        board = new Chessboard(white, black);
        board.addListener(this);
        board.init();
    }

    private void addImage(String image, int xpos, int ypos) {
        Image png = new Image(MainSceneController.class.getResource(image + ".png").toString());
        ImageView view = new ImageView(png);
        grid.add(view, xpos, ypos);
        GridPane.setHalignment(view, HPos.CENTER);
        GridPane.setValignment(view, VPos.CENTER);
        view.setFitWidth(100);
        view.setFitHeight(100);
    }

    @FXML
    @Override
    public void update(ChessPiece piece) {
        addImage(piece.getColor() + "-" + piece.getID(), piece.getXPos(), piece.getYPos());
    }
}
