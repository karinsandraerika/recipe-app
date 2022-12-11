package com.example.recipeapp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class JSONparser {

    public ArrayList<Recipe> parse(InputStream in) {
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        Gson gson = new Gson();

        Type recipeListType = new TypeToken<Collection<Recipe>>() {}.getType();
        return gson.fromJson(inputStreamReader, recipeListType);
    }
}
