package com.jackern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    private static final String fileName = "board.ser";

    private static Chessboard board;
    private static boolean updateComplete;
    private static boolean promotion;
    private static ChessPiece promoting;

    @FXML
    private GridPane grid;

    @FXML
    private GridPane promoteGrid;

    @FXML
    private Pane promoteBackround;

    @FXML
    private static ImageView queenPromote;

    @FXML
    private static ImageView rookPromote;

    @FXML
    private static ImageView bishopPromote;

    @FXML
    private static ImageView knightPromote;

    @FXML
    public void initialize() {
        initBoardClick();
        SaveBoard boardState = loadBoardState();
        if (boardState != null) {
            board = boardState.getBoard();
            board.addListener(this);
            board.updateListeners();
            return;
        }
        board = new Chessboard();
        board.addListener(this);
        board.init();
    }

    private void initBoardClick() {
        for (Node child : grid.getChildren()) {
            if (child instanceof Pane) {
                child.setOnMouseClicked(event -> {
                    if (board.getSelectedPiece() != null) {
                        Integer ypos = GridPane.getRowIndex(child);
                        Integer xpos = GridPane.getColumnIndex(child);
                        if (xpos == null) {
                            xpos = 0;
                        }
                        if (ypos == null) {
                            ypos = 0;
                        }
                        board.movePiece(board.getSelectedPiece(), xpos, ypos);
                        board.selectPiece(null);
                        System.out.println(board.getSelectedPiece());
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
            if (promotion) {
                promoteScreen(promoting);
                promotion = false;
            }
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

    @FXML
    public void promoteScreen(ChessPiece piece) {

        if (piece.getColor().equals("black")) {
            queenPromote = new ImageView(new Image(App.class.getResource("black-queen.png").toString()));
            rookPromote = new ImageView(new Image(App.class.getResource("black-rook.png").toString()));
            bishopPromote = new ImageView(new Image(App.class.getResource("black-bishop.png").toString()));
            knightPromote = new ImageView(new Image(App.class.getResource("black-knight.png").toString()));
        }

        else {
            queenPromote = new ImageView(new Image(App.class.getResource("white-queen.png").toString()));
            rookPromote = new ImageView(new Image(App.class.getResource("white-rook.png").toString()));
            bishopPromote = new ImageView(new Image(App.class.getResource("white-bishop.png").toString()));
            knightPromote = new ImageView(new Image(App.class.getResource("white-knight.png").toString()));
        }

        promoteGrid.add(queenPromote, 0, 0);
        promoteGrid.add(rookPromote, 1, 0);
        promoteGrid.add(bishopPromote, 0, 1);
        promoteGrid.add(knightPromote, 1, 1);

        queenPromote.setFitWidth(100);
        queenPromote.setFitHeight(100);

        rookPromote.setFitWidth(100);
        rookPromote.setFitHeight(100);

        bishopPromote.setFitWidth(100);
        bishopPromote.setFitHeight(100);

        knightPromote.setFitWidth(100);
        knightPromote.setFitHeight(100);

        promoteBackround.setLayoutX(300);
        promoteBackround.setLayoutY(300);

        queenPromote.setOnMouseClicked((MouseEvent e) -> {
            board.promoteToQueen(piece);
            hidePromote();
        });

        rookPromote.setOnMouseClicked((MouseEvent e) -> {
            board.promoteToRook(piece);
            hidePromote();
        });

        bishopPromote.setOnMouseClicked((MouseEvent e) -> {
            board.promoteToBishop(piece);
            hidePromote();
        });

        knightPromote.setOnMouseClicked((MouseEvent e) -> {
            board.promoteToKnight(piece);
            hidePromote();
        });
    }

    private void hidePromote() {
        promoteBackround.setLayoutX(-227);
        Iterator<Node> iterator = promoteGrid.getChildren().iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof ImageView) {
                iterator.remove();
            }
        }
    }

    public static void startPromotion(ChessPiece piece) {
        promotion = true;
        promoting = piece;
    }

    private static SaveBoard loadBoardState() {
        try (FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (SaveBoard) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static void saveBoardState() {
        if (board.gameOver()) {
            return;
        }
        SaveBoard boardState = new SaveBoard(board);
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(boardState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
