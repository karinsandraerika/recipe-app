package com.example.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

// TODO Object creation is very fragile. If something changes in the Recipe class or the database, this breaks. Create a RecipeBuilder class to prevent this.

public class SqliteRepository implements Repository {
    // TODO Make sure this is the right table name
    private final String TABLE_NAME = "recipes";
    private final SQLiteOpenHelper sqlite;

    private static SqliteRepository instance = null;
    public static SqliteRepository getInstance(Context context){
        if(instance == null)
            instance = new SqliteRepository(context);

        return instance;
    }
    private SqliteRepository(Context context){
        // TODO make sure this class exists
        sqlite = SqliteHelper.getInstance(context);
    }

    @Override
    public ArrayList<RecipeListItem> filterByCategory(Category category) {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        ArrayList<RecipeListItem> listItems = new ArrayList<>();
        // TODO All column names are db dependent, make sure they match
        String query = "SELECT id, name FROM " + TABLE_NAME + " WHERE category = " + category;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            RecipeListItem listItem = new RecipeListItem(
                    cursor.getInt(0), // id
                    cursor.getString(1) // name
            );

            listItems.add(listItem);
        }

        cursor.close();
        return listItems;
    }

    @Override
    public Recipe findRecipeById(int id) {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        // TODO Indices are db dependent AND class dependent, make sure they match
        Recipe recipe = cursor.moveToFirst() ? new Recipe(
                cursor.getInt(0), // id
                Category.valueOf(cursor.getString(1)), // category
                cursor.getString(2), // name
                cursor.getString(3), // ingredients
                cursor.getString(4) // instructions
                ) : null;
                // possible null reference exception.
                // Only happens if the RecipeListActivity does not reload the recipe list after deleting a recipe and the user tries to click the deleted recipe.

        cursor.close();
        return recipe;
    }

    @Override
    public boolean saveRecipe(Recipe recipe) {
        if (recipe.getId() == 0) {
             return insert(recipe);
        }
        else {
            return update(recipe);
        }
    }

    private boolean insert(Recipe recipe) {
        SQLiteDatabase db = sqlite.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, getContentValues(recipe));
        return id > 0;
    }

    private boolean update(Recipe recipe) {
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues c = getContentValues(recipe);
        String[] whereArgs = getWhereArgs(recipe.getId());

        int rowsAffected = db.update(TABLE_NAME, c, "id = ?", whereArgs);
        return rowsAffected != 0;

    }

    private ContentValues getContentValues(Recipe recipe) {
        ContentValues c = new ContentValues();

        c.put("category", recipe.getCategory().name());
        c.put("name", recipe.getName());
        c.put("ingredients", recipe.getIngredients());
        c.put("instructions", recipe.getInstructions());
        return c;
    }

    private String[] getWhereArgs(int id) {
        return new String[] {String.valueOf(id)};
    }
}
