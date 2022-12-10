package com.example.recipeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Recipes.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "recipes";

    private static SqliteHelper instance = null;
    public static SqliteHelper getInstance(Context context) {
        if(instance == null)
            instance = new SqliteHelper(context.getApplicationContext());

        return instance;
    }

    private SqliteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createRecipesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void createRecipesTable(SQLiteDatabase db){
        String query =
                "CREATE TABLE recipes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "category TEXT," +
                        "name TEXT," +
                        "ingredients TEXT," +
                        "instructions TEXT" +
                        ")";

        db.execSQL(query);
    }
}