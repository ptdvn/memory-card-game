package com.game;

import com.game.listener.CardClickListener;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoard {

    public final CardMap cardMap = new CardMap();
    GridPane grid = new GridPane();
    private Map<String, Image> loadedRes = new HashMap();

    Label gLabel;
    Button newGame;
    Button exitGame;
    private static final int nRow = 6;
    private static final int nCol = 6;
    public static int clearedCards;
    public static int openedCards;
    public static final int totalCards = 36;

    public GameBoard() {

        initGrid(nRow, nCol);
        gLabel = new Label();

        ImageView imv = new ImageView(new Image("new_game.jpg"));
        newGame = new Button("New Game", imv);
        newGame.setPadding(new Insets(10, 10, 10, 10));

        ImageView imv2 = new ImageView(new Image("exit_game.jpg"));
        exitGame = new Button("Exit Game", imv2);
        exitGame.setPadding(new Insets(10, 10, 10, 10));
    }

    private void initGrid(int nRow, int nCol) {
        for (int i = 0; i < nRow; ++i) {
            for (int j = 0; j < nCol; ++j) {
                ImageView imv = new ImageView(loadImage("back_image.jpg"));
                Button button = new Button("", imv);
                button.setPadding(new Insets(10, 10, 10, 10));
                button.setOnMouseClicked(new CardClickListener(cardMap.getCard(i, j), imv, this));
                GridPane.setRowIndex(button, i);
                GridPane.setColumnIndex(button, j);
                grid.getChildren().add(button);
            }
        }
    }

    public Image loadImage(String resName) {
        Image res = loadedRes.get(resName);
        if (res == null) {
            res = new Image(resName);
            loadedRes.put(resName, res);
        }
        return res;
    }

    public void winGame() {
        this.gLabel.setText("Congratulations! Board cleared. You have opened a total of " + openedCards + " cards.\n" +
                            "Play a new game if you want to improve your record.");
    }
}
