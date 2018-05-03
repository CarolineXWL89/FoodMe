package com.example.caroline.foodme.Search;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Nicolo on 4/19/2018.
 */
public class MyRecentSuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.MyRecentSuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public MyRecentSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}