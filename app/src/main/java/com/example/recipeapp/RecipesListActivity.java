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
        Category category = Category.valueOf(categoryRaw.toUpperCase());

        ArrayList<RecipeListItem> itemList = repo.filterByCategory(category);
        if (itemList.size() != 0){
            adapter = new RecipeAdapter(this, itemList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        setTitle(getCategoryString(category));
    }

    private String getCategoryString(Category cat) {
        String[] catArray = new String[7];
        catArray[0] = "";
        catArray[1] = getResources().getString(R.string.Category1);
        catArray[2] = getResources().getString(R.string.Category2);
        catArray[3] = getResources().getString(R.string.Category3);
        catArray[4] = getResources().getString(R.string.Category4);
        catArray[5] = getResources().getString(R.string.Category5);
        catArray[6] = getResources().getString(R.string.Category6);

        int catIndex = cat.getIndex();

        return catArray[catIndex];
    }

    public void BtnClick (View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        intent.putExtra("category", categoryRaw);
        startActivity(intent);
    }
}


