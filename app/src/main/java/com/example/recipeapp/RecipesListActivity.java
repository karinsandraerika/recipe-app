package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecipesListActivity extends AppCompatActivity {
    Repository repo;
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    Category category;


    ListView l;
    String tutorials[]
            = { "....", ".....",
            "....", "...",
            "....", ".....",
            "....", ".....",
            "....." };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_recipes_list);

        repo = SqliteRepository.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.rv_recipes);

        //TODO hämta category från intent
        Intent intent = getIntent();

        adapter = new RecipeAdapter(this, repo.filterByCategory(category));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
=======
        setContentView(R.layout.activity_recipes_list_1);

        l = findViewById(R.id.list);
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
              androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tutorials);
        l.setAdapter(arr);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReadRecipeActivity.class);
                startActivity(intent);
              }
        });
>>>>>>> 9cab45a28404b69e733f2e2ad35766068120e82f
    }

    //TODO navigering till recept
}