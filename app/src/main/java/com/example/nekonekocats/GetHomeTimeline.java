package com.example.nekonekocats;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetHomeTimeline extends AsyncTask<Void, List<twitter4j.Status>, Void> {
    private static long latestTweetId = 0;
    Twitter twitter;

    @Override
    protected Void doInBackground(Void... voids) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TwitterKeyManager.getApi_key())
                .setOAuthConsumerSecret(TwitterKeyManager.getApi_key_secret())
                .setOAuthAccessToken(TwitterKeyManager.getAccess_token())
                .setOAuthAccessTokenSecret(TwitterKeyManager.getAccess_token_secret());

        TwitterFactory factory = new TwitterFactory(cb.build());
        twitter = factory.getInstance();

        TimerTask getTimelineTask = new TimerTask() {
            public void run() {
                getCurrentTimeline();
            }
        };

        // Get timeline per minute
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(getTimelineTask, 0, 60000);

        return null;
    }

    protected void getCurrentTimeline() {
        try {
            int pageCount = 1;
            List<twitter4j.Status> timelineTweets = twitter.getHomeTimeline();

            if (latestTweetId == 0) {
                publishProgress(timelineTweets);
            }
            else {
                List<twitter4j.Status> matchTweets = new ArrayList<>();

                while (timelineTweets.get(timelineTweets.size() - 1).getId() > latestTweetId) {
                    matchTweets.addAll(timelineTweets);
                    pageCount += 1;
                    Paging paging = new Paging(pageCount, 20);
                    timelineTweets = twitter.getHomeTimeline(paging);
                }

                // Get new tweets since latestTweetId
                for(int i = 0; i < timelineTweets.size(); i++) {
                    if (timelineTweets.get(i).getId() <= latestTweetId) {
                        break;
                    }

                    matchTweets.add(timelineTweets.get(i));
                }

                publishProgress(matchTweets);
            }

            latestTweetId = timelineTweets.get(0).getId();
        } catch (TwitterException e) {
            e.printStackTrace();
            publishProgress(new ArrayList<>());
        }
    }

    @Override
    protected void onProgressUpdate(List<twitter4j.Status>... timelineTweets) {
        super.onProgressUpdate(timelineTweets);
        int nyannNyannCount = 0;
        for(int i = 0; i < timelineTweets[0].size(); i++) {
            if (timelineTweets[0].get(i).getText().matches("^.*にゃーん.*$")) {
                MainActivity.ImageLoaderCallback imageLoaderCallback
                    = new MainActivity.ImageLoaderCallback(
                        nyannNyannCount,
                        timelineTweets[0].get(i).getUser().getName(),
                        timelineTweets[0].get(i).getText()
                    );
                LoadImageAsync loadImageAsync = new LoadImageAsync(imageLoaderCallback);
                loadImageAsync.execute(timelineTweets[0].get(i).getUser().get400x400ProfileImageURLHttps());
                nyannNyannCount += 1;
            }
        }
    }
}
