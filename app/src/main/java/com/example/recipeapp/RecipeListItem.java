package com.example.recipeapp;

public class RecipeListItem {
    private final int id;
    private String name;

    public RecipeListItem(int Id, String Name) {
        this.id = Id;
        this.name = Name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public RecipeListItem setName(String name) {
        this.name = name;
        return this;
    }
}
