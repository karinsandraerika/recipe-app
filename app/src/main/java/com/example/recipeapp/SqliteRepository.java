package com.example.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


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
    public ArrayList<RecipeListItem> filterByCategory(Category category) throws IllegalArgumentException {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        ArrayList<RecipeListItem> listItems = new ArrayList<>();
        // TODO All column names are db dependent, make sure they match
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id", "name"},
                "category = ?",
                new String[]{category.name()},
                null, null, null, null );


        while (cursor.moveToNext()) {
            RecipeListItem listItem = new RecipeListItem()
            .setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")))
            .setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));

            listItems.add(listItem);
        }

        cursor.close();
        return listItems;
    }

    @Override
    public Recipe findRecipeById(int id) throws IllegalArgumentException {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,
                "id = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        Recipe recipe = cursor.moveToFirst() ? new Recipe()
                .setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")))
                .setCategory(Category.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("category"))))
                .setName(cursor.getString(cursor.getColumnIndexOrThrow("name")))
                .setIngredients(cursor.getString(cursor.getColumnIndexOrThrow("ingredients")))
                .setInstructions(cursor.getString(cursor.getColumnIndexOrThrow("instructions")))
                : null;
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
