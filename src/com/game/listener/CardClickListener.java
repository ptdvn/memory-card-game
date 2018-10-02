package com.game.listener;

import com.game.GameBoard;
import com.game.model.Card;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CardClickListener implements EventHandler<MouseEvent> {
    public static ArrayList<Card> cardList = new ArrayList();
    public static ArrayList<ImageView> imageList = new ArrayList();
    private Card card;
    private ImageView imv;
    private GameBoard gb;
    static int numCards;

    public CardClickListener(Card _card, ImageView _imv, GameBoard _gb) {
        this.card = _card;
        this.imv = _imv;
        this.gb = _gb;
    }

    // Main objective: Check if two opened cards share the same front image name.
    // If they do not, turn them face down.
    public void handle(MouseEvent e) {
        if (!this.card.isDiscovered()) {
            MouseButton mb = e.getButton();
            if (mb == MouseButton.PRIMARY) {
                if (numCards < 2) {
                    leftMouseClick();
                }
                cardList.add(this.card);
                imageList.add(this.imv);
                ++numCards;
                if (numCards == 2) {
                    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                    Runnable task = new Runnable() {
                        public void run() {
                            if (!(CardClickListener.cardList.get(0)).cardFrontImageName.equals((CardClickListener.cardList.get(1)).cardFrontImageName)) {
                                imageList.get(0).setImage(gb.loadImage(cardList.get(0).cardBackImageName));
                                imageList.get(1).setImage(gb.loadImage(cardList.get(1).cardBackImageName));
                                cardList.get(0).discovered = false;
                                cardList.get(1).discovered = false;
                            } else {
                                gb.clearedCards += 2;
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        if (gb.clearedCards == gb.totalCards) {
                                            gb.winGame();
                                        }
                                    }
                                });
                            }
                            cardList.clear();
                            imageList.clear();
                            numCards = 0;
                        }
                    };
                    scheduledExecutorService.schedule(task, 700, TimeUnit.MILLISECONDS);
                    gb.openedCards += 2;
                }
            }
        }
    }

    private void leftMouseClick() {
        card.discover();
        String imgName = card.cardFrontImageName;
        imv.setImage(gb.loadImage(imgName));
    }
}
