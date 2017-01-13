package com.example.android.newshourapp;

/**
 * Created by saurabh on 1/13/2017.
 */

public class CustomObject {
    private String mtitle;
    private String mdate;
    private String msection;
    private String mUrl;
    public CustomObject(String title, String section, String date , String Url){
        mdate = date;
        mtitle = title;
        msection = section;
        mUrl = Url;
    }

    public String gettitle() {
        return mtitle;
    }

    public String getdate() {
        return mdate;
    }

    public String getsection() {
        return msection;
    }

    public String getUrl() {
        return mUrl;
    }
}
