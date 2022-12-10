package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

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

        ArrayList<RecipeListItem> itemList = repo.filterByCategory(category);
        if (itemList.size() != 0){
            adapter = new RecipeAdapter(this, itemList, r -> {
                Intent intentRead = new Intent(this, ReadRecipeActivity.class);
                intentRead.putExtra("id", r.getId());
                startActivity(intentRead);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        }

    }

    public void BtnClick (View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);

    }
}