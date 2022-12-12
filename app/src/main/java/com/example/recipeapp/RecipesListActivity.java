package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipesListActivity extends AppCompatActivity {
    Repository repo;
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    String categoryRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        repo = SqliteRepository.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.rv_recipes);

        Intent intent = getIntent();
        categoryRaw = intent.getStringExtra("category");
        Category category = Category.valueOf(categoryRaw.toUpperCase()); //NullPointerException ibland

        //we should set the itemList as attribute

        ArrayList<RecipeListItem> itemList = repo.filterByCategory(category);
        if (itemList.size() != 0){
            adapter = new RecipeAdapter(this, itemList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }


    protected void onResume() {
        super.onResume();

        //we should use the information from intent to update the item list
        adapter.notifyDataSetChanged();


    }
    public void BtnClick (View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        intent.putExtra("category", categoryRaw);
        startActivity(intent);
    }
}


