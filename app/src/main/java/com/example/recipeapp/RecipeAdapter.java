package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    Context context;
    ArrayList<Recipe> recipes;
    RecipeAction action;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        this.action = r -> {};
    }
    public RecipeAdapter(Context context, ArrayList<Recipe> recipes, RecipeAction action) {
        this(context, recipes);
        this.action = action;
    }

    //

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recipe_list_row, parent, false);
        //View itemView = inflater.inflate(R.layout.recipe_list_row, parent);

        ViewHolder holder = new ViewHolder(itemView);

        itemView.setOnClickListener(view -> action.run(holder.getRecipe()));

        //TODO event listener, navigation till ReadRecipeActivity
        //TODO måste skicka med id i intentet, i bästa fall funkar intent.putExtra("id", Integer.parseInt(holder.itemView.getTag().toString()));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);
        //omvandlar till en string för säkerhets skull för nu
        holder.itemView.setTag(String.valueOf(recipe.getId())); //för att kunna skicka med i intentet
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Recipe recipe;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view = itemView;
        }

        public Recipe getRecipe() {
            return recipe;
        }
        public void bind(Recipe recipe){
            this.recipe = recipe;

            bindString(R.id.textView, recipe.getName());
        }

        private void bindString(int resId, String value){
            TextView txt = itemView.findViewById(R.id.textView);

        }
    }

    public interface RecipeAction {
        void run(Recipe recipe);
    }
}
