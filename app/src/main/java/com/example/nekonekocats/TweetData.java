package com.example.nekonekocats;

import android.graphics.drawable.Drawable;

public class TweetData {
    private Drawable profileIcon;
    private String profileName;
    private String tweetText;

    public  void setProfileIcon(Drawable profileIcon) { this.profileIcon = profileIcon; }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public Drawable getProfileIcon() { return  profileIcon; }

    public String getProfileName() {
        return profileName;
    }

    public String getTweetText() {
        return tweetText;
    }
}
