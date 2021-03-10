package com.example.nekonekocats;

import android.app.Activity;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

public class AppColorTheme {
    // Toggle the color theme of this app
    public static void toggle(Activity activity) {
        int currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        System.out.println(currentNightMode);
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_YES:
                // Change to light theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                System.out.println(currentNightMode);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
            default:
                // Change to night theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                System.out.println(currentNightMode);
                break;
        }
    }
}
