package com.game.model;

public class Card {
    public final String cardBackImageName = "back_image.jpg";
    public String cardFrontImageName;
    public boolean discovered = false;

    public void discover() {
        discovered = true;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public String toString() {
        return cardFrontImageName;
    }
}
