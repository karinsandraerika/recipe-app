package com.example.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    Context context;
    ArrayList<RecipeListItem> recipes;

    public RecipeAdapter(Context context, ArrayList<RecipeListItem> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recipe_list_row, parent, false);

        ViewHolder holder = new ViewHolder(itemView);

        itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReadRecipeActivity.class);
            intent.putExtra("id", holder.recipe.getId());
            context.startActivity(intent);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        RecipeListItem recipe = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecipeListItem recipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(RecipeListItem recipe){
            this.recipe = recipe;
            bindString(R.id.textView, recipe.getName());
        }

        private void bindString(int resId, String value){
            TextView txt = itemView.findViewById(resId);
            txt.setText(value);
        }
    }


}
