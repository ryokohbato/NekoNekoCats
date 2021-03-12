package com.example.nekonekocats;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageAsync extends AsyncTask<String, Void, Drawable> {
    private Activity _activity;

    public LoadImageAsync(Activity activity) {
        _activity = activity;
    }

    @Override
    protected Drawable doInBackground(String... urls) {
        Drawable image = null;
        try {
            URL url = new URL(urls[0]);
            InputStream stream = (InputStream) url.getContent();
            image = Drawable.createFromStream(stream, "");
            stream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    protected void onPostExecute(Drawable image) {
        if (image == null) return;
        ((ImageView)_activity.findViewById(R.id.profile_icon)).setImageDrawable(image);
    }
}
