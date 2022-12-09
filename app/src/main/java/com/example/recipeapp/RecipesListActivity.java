package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        setContentView(R.layout.activity_recipes_list);

        AdapterView<Adapter> l = null;
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReadRecipeActivity.class);
                startActivity(intent);
              }
        });
    }
}