package com.example.recipeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io. * ;
import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Recipes.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "recipes";

    Context context;

    private static SqliteHelper instance = null;
    public static SqliteHelper getInstance(Context context) {
        if(instance == null)
            instance = new SqliteHelper(context.getApplicationContext());

        return instance;
    }

    private SqliteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlInitScript = readInitScript("db_init.sql");
        for (String query: sqlInitScript.split(";")) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private String readInitScript(String pathToSqlFile) {
        try {
            InputStreamReader sr = new InputStreamReader(context.getAssets().open(pathToSqlFile));
            BufferedReader reader = new BufferedReader(sr);

            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader.readLine();

                if(line == null)
                    break;

                sb.append(line);
            }

            String script = sb.toString();
            reader.close();
            return script;

        } catch (IOException e) {
            return "";
            //?
        }
    }
}