package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReadRecipeActivity extends AppCompatActivity {
    Recipe recipe;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_recipe);

        repo = SqliteRepository.getInstance(getApplicationContext());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        recipe = repo.findRecipeById(id);
        initViewFromRecipe(recipe);

        setTitle(recipe.getName());
    }

    private void initViewFromRecipe(Recipe recipe) {
        setText(R.id.txtIngredientsRecipe, recipe.getIngredients());
        setText(R.id.txtInstructionsRecipe, recipe.getInstructions());
    }

    private void setText(int resId, String value) {
        TextView view = findViewById(resId);
        view.setText(value);
    }
}