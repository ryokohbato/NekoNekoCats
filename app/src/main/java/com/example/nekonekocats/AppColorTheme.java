package com.example.nekonekocats;

import androidx.appcompat.app.AppCompatDelegate;

public class AppColorTheme {
    public static void change(boolean isLight) {
        if (isLight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}
