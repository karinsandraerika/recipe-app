package com.example.recipeapp;

import android.util.Log;
import android.util.Xml;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class XMLparser {
    private static final String ns = null;

    public ArrayList<Entry> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readResources(parser);
        } finally {
            in.close();
        }
    }



    private ArrayList<Entry> readResources(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Entry> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "resources");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();

            // Starts by looking for the recipe tag
            if (tagName.equals("recipe")) {
                entries.add(readRecipe(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    public static class Entry {
        public final String category;
        public final String name;
        public final String ingredients;
        public final String instructions;

        private Entry(String category, String name, String ingredients, String instructions) {
            this.category = category;
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
        }

        @NonNull
        @Override
        public String toString(){
            String[] fields = new String[] {category, name, ingredients, instructions};
            return String.join(", ", fields);
        }

    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private Entry readRecipe(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d("XMLparser", "reading recipe");

        parser.require(XmlPullParser.START_TAG, ns, "recipe");
        String category = null;
        String name = null;
        String ingredients = null;
        String instructions = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }


            String tagName = parser.getName();
            Log.d("XMLparser", "get tag: " + tagName);
            if (tagName.equals("category")) {
                category = readText(parser, tagName);
            } else if (tagName.equals("name")) {
                name = readText(parser, tagName);
            } else if (tagName.equals("ingredients")) {
                ingredients = readText(parser, tagName);
            } else if (tagName.equals("instructions")) {
                instructions = readText(parser, tagName);
            } else {
                skip(parser);
            }

        }
        return new Entry(category, name, ingredients, instructions);
    }


    // For the tags name, ingredients and instructions, extracts their text values.
    private String readText(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}

