package com.example.recipeapp;

public enum Category {
    CHRISTMAS(4), SAFFRON(5), SAFFRONFREE(6), RECIPEFORLOVE(2), RECIPEFORDISASTER(3), BREAD(1);

    Category(int index) {
        this.index = index;
    }

    private final int index;

    public int getIndex() {
        return index;
    }
}
