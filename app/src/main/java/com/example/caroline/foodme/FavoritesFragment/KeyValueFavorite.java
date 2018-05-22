package com.example.caroline.foodme.FavoritesFragment;


import android.support.annotation.NonNull;

public class KeyValueFavorite implements Comparable {
    private String key;
    private int value;
    private boolean backendless;

    public KeyValueFavorite(String key, int value, boolean backendless) {
        this.key = key;
        this.value = value;
        this.backendless = backendless;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isBackendless() {
        return backendless;
    }

    public void setBackendless(boolean backendless) {
        this.backendless = backendless;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        KeyValueFavorite keyValueFavorite = (KeyValueFavorite) o;
        return keyValueFavorite.getValue() - value;
    }
}
