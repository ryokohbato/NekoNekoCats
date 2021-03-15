package com.example.nekonekocats;

import android.os.AsyncTask;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterMethod;
import twitter4j.conf.ConfigurationBuilder;

// The string args[0] is used as the content of the tweet
public class PostTweetAsync extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... args) {
        if (args.length < 1) {
            return null;
        }

        // Set authentication key
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(TwitterKeyManager.getApi_key())
            .setOAuthConsumerSecret(TwitterKeyManager.getApi_key_secret())
            .setOAuthAccessToken(TwitterKeyManager.getAccess_token())
            .setOAuthAccessTokenSecret(TwitterKeyManager.getAccess_token_secret());

        AsyncTwitterFactory factory = new AsyncTwitterFactory(cb.build());
        AsyncTwitter twitter = factory.getInstance();
        twitter.addListener(new TwitterAdapter() {
            @Override
            public void onException(TwitterException e, TwitterMethod method) {
                e.printStackTrace();
            }
        });

        // Post tweet
        twitter.updateStatus(args[0]);

        return null;
    }
}
