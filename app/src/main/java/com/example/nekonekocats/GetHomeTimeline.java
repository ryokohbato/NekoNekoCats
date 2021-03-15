package com.example.nekonekocats;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetHomeTimeline extends AsyncTask<Void, Void, List<Status>> {
    @Override
    protected List<twitter4j.Status> doInBackground(Void... voids) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TwitterKeyManager.getApi_key())
                .setOAuthConsumerSecret(TwitterKeyManager.getApi_key_secret())
                .setOAuthAccessToken(TwitterKeyManager.getAccess_token())
                .setOAuthAccessTokenSecret(TwitterKeyManager.getAccess_token_secret());

        TwitterFactory factory = new TwitterFactory(cb.build());
        Twitter twitter = factory.getInstance();
        try {
            List<twitter4j.Status> timelineTweets = twitter.getHomeTimeline();
            return timelineTweets;
        } catch (TwitterException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<twitter4j.Status> timelineTweets) {
        super.onPostExecute(timelineTweets);

        for(twitter4j.Status tweet: timelineTweets) {
            MainActivity.insertItemToEnd(MainActivity.createTweetData(tweet.getUser().getName(), tweet.getText()));
        }
    }
}
