package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class RecipesListActivity extends AppCompatActivity {
    Repository repo;
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        repo = SqliteRepository.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.rv_recipes);

        //TODO hämta category från intent
        Intent intent = getIntent();

        adapter = new RecipeAdapter(this, repo.filterByCategory(category));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //TODO navigering till recept
}