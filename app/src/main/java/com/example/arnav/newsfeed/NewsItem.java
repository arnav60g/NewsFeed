package com.example.arnav.newsfeed;

/**
 * Created by Arnav on 28-01-2017.
 */

public class NewsItem {

    private String mSection;

    private String mTitle;

    private String mTimeInMilliseconds;

    private String mDetail;

    public NewsItem (String section, String title, String timeInMilliseconds, String detail){
        mSection=section;
        mTitle=title;
        mTimeInMilliseconds=timeInMilliseconds;
        mDetail=detail;
    }

    public String getSection(){
        return mSection;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getDate(){
        return mTimeInMilliseconds;
    }

    public String getDetail(){
        return mDetail;
    }
}
