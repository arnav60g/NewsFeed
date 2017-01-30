package com.example.arnav.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Arnav on 28-01-2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {
    String mUrl;
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl=url;
    }

    @Override
    public List<NewsItem> loadInBackground() {
        List<NewsItem> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
