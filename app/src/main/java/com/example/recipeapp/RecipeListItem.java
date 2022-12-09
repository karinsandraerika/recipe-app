package com.example.recipeapp;

public class RecipeListItem {
    private int id;
    private String name;

    public RecipeListItem() {
        this.id = 0;
        this.name = "";
    }
    public RecipeListItem(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public RecipeListItem setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RecipeListItem setName(String name) {
        this.name = name;
        return this;
    }
}
