package com.example.recipeapp;

import java.util.ArrayList;

public interface Repository {

    // Get all recipes that belong to a certain category.
    ArrayList<Recipe> filterByCategory(Category category);
    // Get a recipe by the id.
    Recipe findRecipeById(int id);
    // Save a recipe to the database. Returns true if successful else returns false.
    boolean saveRecipe(Recipe recipe);

}
