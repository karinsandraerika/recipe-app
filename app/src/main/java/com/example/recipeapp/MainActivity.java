package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onBtnCategoryClick(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        String category = view.getTag().toString();
        intent.putExtra("category", category);
        startActivity(intent);
    }
}