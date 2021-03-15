package com.example.nekonekocats;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TweetDataViewHolder extends RecyclerView.ViewHolder {
    public TextView profileNameView;
    public TextView tweetTextView;

    public TweetDataViewHolder(@NonNull View itemView) {
        super(itemView);
        profileNameView = (TextView)itemView.findViewById(R.id.profile_name);
        tweetTextView = (TextView)itemView.findViewById(R.id.tweet_text);
    }
}
