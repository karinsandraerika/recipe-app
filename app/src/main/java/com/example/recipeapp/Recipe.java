package com.example.recipeapp;

public class Recipe {
    private int id;
    private Category category;
    private String name;
    private String ingredients;
    private String instructions;

    public Recipe(){
        id = 0;
        category = Category.RECIPEFORDISASTER; //Vi behöver nog en "övrigt"-kategori, som default
        name = ingredients = instructions = "";
    }

    public Recipe(int id, Category category, String name, String ingredients, String instructions) {
        //lägga till validering här eller i annan klass innan objekt skapas
        this.id = id;
        this.category = category;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    //Ta bort?
    public Recipe setId(int id) {
        this.id = id;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Recipe setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public Recipe setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
}
