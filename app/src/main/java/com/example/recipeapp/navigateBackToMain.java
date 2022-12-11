package com.example.recipeapp;

import android.content.Intent;

public class navigateBackToMain {
    Intent intent = new Intent(this, RecipesListActivity.class);
    startActivity(intent);
}
