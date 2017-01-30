package com.example.arnav.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arnav on 28-01-2017.
 */

public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

    public NewsItemAdapter(Context context, List<NewsItem> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        final NewsItem currentNews= getItem(position);

        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        sectionView.setText(currentNews.getSection());

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentNews.getTitle());

        String date[]=currentNews.getDate().split("T");
        String dateToDisplay=date[0];
        String timeToDisplay=date[1];

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(timeToDisplay);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(dateToDisplay);

        final View textContainer = listItemView.findViewById(R.id.text_container);

        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentNews.getDetail();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }
}
