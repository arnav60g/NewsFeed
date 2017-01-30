package com.example.arnav.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {
    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String URL_SAMPLE="http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConnectivityManager cm =
                (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected){
            getLoaderManager().initLoader(0,null,this).forceLoad();
        }else {
            TextView noConnection = (TextView) findViewById(R.id.no_connection);
            noConnection.setVisibility(View.VISIBLE);
            ProgressBar bar=(ProgressBar) findViewById(R.id.loader);
            bar.setVisibility(View.GONE);
        }
    }

    private void updateUi (List<NewsItem> item){
        List<NewsItem> news=item;

        ListView newsListView = (ListView) findViewById(R.id.list);

        NewsItemAdapter adapter = new NewsItemAdapter(this, news);

        newsListView.setAdapter(adapter);

    }

    @Override
    public android.content.Loader<List<NewsItem>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this,URL_SAMPLE);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<NewsItem>> loader, List<NewsItem> news) {
        if (news == null) {
            return;
        }
        updateUi(news);
        ProgressBar bar=(ProgressBar) findViewById(R.id.loader);
        bar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<NewsItem>> loader) {
        updateUi(new ArrayList<NewsItem>());
    }
}
