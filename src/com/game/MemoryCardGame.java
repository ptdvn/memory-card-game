package com.game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MemoryCardGame extends Application {

    public void start(Stage primaryStage) throws Exception {
        startGame(primaryStage);
    }

    private void startGame(final Stage primaryStage) {

        GameBoard gb = new GameBoard();
        gb.grid.setPadding(new Insets(25, 10, 10, 10));
        BorderPane bdp = new BorderPane();
        bdp.setCenter(gb.grid);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(gb.newGame, gb.exitGame, gb.gLabel);
        bdp.setTop(hbox);
        hbox.setPadding(new Insets(20, 10, 10, 10));
        hbox.setSpacing(20);
        Scene scene = new Scene(bdp);
        primaryStage.setTitle("Memory Card Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        GameBoard.clearedCards = 0;
        GameBoard.openedCards = 0;

        class NewGameClickListener implements EventHandler<MouseEvent> {
            public void handle(MouseEvent e) {
                MouseButton mb = e.getButton();
                if (mb == MouseButton.PRIMARY) {
                    leftMouseClick();
                }
            }
            private void leftMouseClick() {
                restart(primaryStage);
            }
        }

        class ExitGameClickListener implements EventHandler<MouseEvent> {
            public void handle(MouseEvent e) {
                MouseButton mb = e.getButton();
                if (mb == MouseButton.PRIMARY) {
                    leftMouseClick();
                }
            }
            private void leftMouseClick() {
                exit(primaryStage);
            }
        }

        gb.newGame.setOnMouseClicked(new NewGameClickListener());
        gb.exitGame.setOnMouseClicked(new ExitGameClickListener());
    }

    public void restart(Stage stage) {
        stage.close();
        startGame(stage);
    }

    public void exit(Stage stage) {
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
