package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

public class RecipesListActivity extends AppCompatActivity {
    Repository repo;
    RecyclerView recyclerView;
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        repo = SqliteRepository.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.rv_recipes);

        Intent intent = getIntent();
        String categoryRaw = intent.getStringExtra("category");
        Category category = Category.valueOf(categoryRaw.toUpperCase());

        //TODO use this category to filter recipes

        Category cat = Category.valueOf(category.toUpperCase());

        adapter = new RecipeAdapter(this, repo.filterByCategory(cat));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(R.layout.activity_recipes_list);
    }

    public void BtnClick (View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        intent.putExtra("category", view.getTag().toString());
        startActivity(intent);


    }
}