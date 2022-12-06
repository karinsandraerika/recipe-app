package com.example.recipeapp;

public class Recipe {
    private Category category;
    private String name;
    //private String[] ingredients; //kanske bara en string istället för en array
    private String instructions; //just nu är detta både ingredienslista och instruktioner

    public Recipe(Category category, String name, String instructions) {
        //lägga till validering här eller i annan klass innan objekt skapas
        this.category = category;
        this.name = name;
        this.instructions = instructions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
