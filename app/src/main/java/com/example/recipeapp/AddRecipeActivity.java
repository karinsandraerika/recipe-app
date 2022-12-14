package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecipeActivity extends AppCompatActivity {

    Repository recipeRepo;
    String strCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipeRepo = SqliteRepository.getInstance(getApplicationContext());
        strCategory = getCategoryFromIntent();

        setTitle(getResources().getString(R.string.AddRecipe));
    }

    public void onBtnSaveClick(View view) {
        if (!validateInput()) {
            return;
        }
        Recipe recipe = new Recipe()
                .setId(0)
                .setCategory(Category.valueOf(strCategory))
                .setName(getTextFromView(R.id.txtAddRecipeTitle))
                .setIngredients(getTextFromView(R.id.txtAddRecipeIngredients))
                .setInstructions(getTextFromView(R.id.txtAddRecipeInstructions));

        if (recipeRepo.saveRecipe(recipe)) {
            Intent intent = new Intent(this, RecipesListActivity.class);
            intent.putExtra("category", strCategory);
            startActivity(intent);
        }
        else
            Toast.makeText(this, AddRecipeActivity.this.getResources().getString(R.string.ToastSaveFeedback), Toast.LENGTH_SHORT).show();
    }

    private String getCategoryFromIntent(){
        Intent intent = getIntent();
        strCategory = intent.getStringExtra("category");
        String s = getResources().getString(R.string.Category3);
        return strCategory;
    }

    private boolean validateInput() {
        if (getTextFromView(R.id.txtAddRecipeTitle).isEmpty() || getTextFromView(R.id.txtAddRecipeIngredients).isEmpty()) {
            Toast.makeText(this, AddRecipeActivity.this.getResources().getString(R.string.ToastAddFeedback), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private String getTextFromView(int resId) {
        TextView view = findViewById(resId);
        return view.getText().toString().trim();
    }
}