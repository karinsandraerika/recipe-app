package com.example.recipeapp;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class StubRepo implements Repository{

    // DB
    ArrayList<Recipe> recipes;

    public StubRepo() {
        this.recipes = new ArrayList<>();
    }

    public StubRepo(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }


    @Override
    public ArrayList<RecipeListItem> filterByCategory(Category category) {
        ArrayList<RecipeListItem> listItems = new ArrayList<>();

        for (Recipe r : recipes){
            if (r.getCategory() == category){
                RecipeListItem listItem = new RecipeListItem(r.getId(), r.getName());
                listItems.add(listItem);
            }
        }
        return listItems;
    }

    @Override
    public Recipe findRecipeById(int id) {
        for (Recipe r : recipes){
            if (r.getId() == id){
                return r;
            }
        }
        throw new RuntimeException("Id not found.");
    }

    @Override
    public int saveRecipe(Recipe recipe) {
        recipes.add(recipe);
        return recipe.getId();
    }
}
