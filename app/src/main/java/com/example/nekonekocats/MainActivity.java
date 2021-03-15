package com.example.nekonekocats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static List<TweetData> tweetDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupRecyclerView();

        GetHomeTimeline getHomeTimeline = new GetHomeTimeline();
        getHomeTimeline.execute();
    }

    public void toggleColorTheme(View view) {
        AppColorTheme.toggle(this);
    }

    // Add tweet data in the top
    public static void insertItem(int insertIndex, TweetData tweetData) {
        tweetDataList.add(insertIndex, tweetData);
        adapter.notifyItemInserted(insertIndex);
    }

    protected void setupRecyclerView() {
        recyclerView = findViewById(R.id.timeline);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // No fixed number of data
        recyclerView.setHasFixedSize(false);

        tweetDataList = new ArrayList<>();
        adapter = new TweetDataAdapter(tweetDataList);
        recyclerView.setAdapter(adapter);

        // Scroll to the top when a data is inserted.
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                recyclerView.scrollToPosition(0);
            }
        });
    }

    public static TweetData createTweetData(String profileName, String tweetText) {
        TweetData tweetDataItem = new TweetData();

        tweetDataItem.setProfileName(profileName);
        tweetDataItem.setTweetText(tweetText);

        return tweetDataItem;
    }

    public void NyannNyann(View view) {
        PostTweetAsync postTweetAsync = new PostTweetAsync();
        postTweetAsync.execute("にゃーん");
    }
}