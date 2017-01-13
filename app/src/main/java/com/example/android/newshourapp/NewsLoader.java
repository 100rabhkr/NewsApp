package com.example.android.newshourapp;

import java.util.List;
import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by saurabh on 1/14/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<CustomObject>> {

    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<CustomObject> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<CustomObject> customObjects = QueryUtils.fetchNewsData(mUrl);
        return customObjects;
    }
}
