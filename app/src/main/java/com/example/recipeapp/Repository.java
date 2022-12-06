package com.example.recipeapp;

public interface Repository {

    // Get all recipes that belong to a certain category.
    RecipeListItem[] filterByCategory(Category category);
    // Get a recipe by the id.
    Recipe findRecipeById(int id);
    // Save a recipe to the database. Returns -1 if unsuccessful else returns the id.
    int saveRecipe(Recipe recipe);

}
