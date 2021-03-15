package com.example.nekonekocats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TweetDataAdapter extends RecyclerView.Adapter<TweetDataViewHolder> {
    private List<TweetData> tweetDataList;
    public TweetDataAdapter(List<TweetData> tweetDataList) {
        this.tweetDataList = tweetDataList;
    }

    @NonNull
    @Override
    public TweetDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item, parent, false);
        return new TweetDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetDataViewHolder holder, int position) {
        holder.profileNameView.setText(tweetDataList.get(position).getProfileName());
        holder.tweetTextView.setText(tweetDataList.get(position).getTweetText());
    }

    @Override
    public int getItemCount() {
        return tweetDataList.size();
    }
}
