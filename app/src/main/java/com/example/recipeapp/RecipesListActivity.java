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
    Category category;


    protected void onCreate(Bundle savedInstanceState, AdapterView<Adapter> l) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        repo = SqliteRepository.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.rv_recipes);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        //TODO use this category to filter recipes

        adapter = new RecipeAdapter(this, repo.filterByCategory(category));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(R.layout.activity_recipes_list);


        l.setOnItemClickListener (new repo.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReadRecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}