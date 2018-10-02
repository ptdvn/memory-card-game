package com.game;

import java.util.ArrayList;
import java.util.Random;

import com.game.model.Card;


public class CardMap {
    private Card[][] cards = new Card[6][6];
    private static final int nRow = 6;
    private static final int nCol = 6;
    ArrayList<String> nameList = new ArrayList();
    ArrayList<String> duplicateNameList = new ArrayList();
    ArrayList<Integer> indexList = new ArrayList();

    public CardMap() {
        for (int k = 0; k < 18; ++k) {
            indexList.add(k);
        }
        setCards(cards);
    }

    // Main objective: Assign a random different front image name to each card
    // and make sure no more than 2 cards can have that same name.

    private void setCards(Card[][] cards) {
        this.cards = cards;
        Random r = new Random();
        for (int i = 0; i < nRow; ++i) {
            for (int j = 0; j < nCol; ++j) {
                int totalDiffCards = 18;
                Integer indexOfCard = r.nextInt(totalDiffCards);
                String imageName = "image_" + indexOfCard.toString() + ".jpg";

                if (!nameList.contains(imageName)) {
                    nameList.add(imageName);
                    cards[i][j] = new Card();
                    cards[i][j].cardFrontImageName = imageName;
                } else if (nameList.contains(imageName) && !duplicateNameList.contains(imageName)) {
                    this.duplicateNameList.add(imageName);
                    cards[i][j] = new Card();
                    cards[i][j].cardFrontImageName = imageName;
                } else if (nameList.contains(imageName) && duplicateNameList.contains(imageName)) {

                    do {
                        Integer indexOfCard2 = indexList.get(r.nextInt(indexList.size()));
                        imageName = "image_" + indexOfCard2.toString() + ".jpg";
                    } while (nameList.contains(imageName) && duplicateNameList.contains(imageName));

                    if (!nameList.contains(imageName)) {
                        nameList.add(imageName);
                        cards[i][j] = new Card();
                        cards[i][j].cardFrontImageName = imageName;
                    } else if (nameList.contains(imageName) && !duplicateNameList.contains(imageName)) {
                        duplicateNameList.add(imageName);
                        cards[i][j] = new Card();
                        cards[i][j].cardFrontImageName = imageName;
                    }
                }
            }
        }
    }

    public Card getCard(int i, int j) {
        return cards[i][j];
    }

    private void display() {
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                System.out.print("  |  " + cards[i][j]);
            }
            System.out.print("  |  ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CardMap cardmap = new CardMap();
        cardmap.display();
    }
}