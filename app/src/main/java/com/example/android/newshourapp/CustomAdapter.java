package com.example.android.newshourapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saurabh on 1/13/2017.
 */

public class CustomAdapter extends ArrayAdapter<CustomObject> {

    public CustomAdapter(Context context, List<CustomObject> customObjects){
        super(context,0,customObjects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View Customview = convertView;
        if (Customview == null) {
            Customview = LayoutInflater.from(getContext()).inflate(
                    R.layout.list, parent, false);
        }
        CustomObject currentItem = getItem(position);
        TextView TitleTextView = (TextView) Customview.findViewById(R.id.TitletextView);
        TitleTextView.setText(currentItem.gettitle());
        TextView SectionTextView = (TextView) Customview.findViewById(R.id.textView);
        SectionTextView.setText(currentItem.getsection());
        String defaultDate = currentItem.getdate();
        String modDate;
        modDate = modifyDate(defaultDate);
        TextView datetextView = (TextView) Customview.findViewById(R.id.datetextView);
        datetextView.setText(modDate);
        TextView timeTextView = (TextView) Customview.findViewById(R.id.timetextView);
        timeTextView.setText(modifyTime(defaultDate));
        if(currentItem.getUrl()==null){
            TextView errortv = (TextView) Customview.findViewById(R.id.textView6);
            errortv.setText("Please Go Back and Enter A valid Term");
        }
        return Customview;
    }

    public String modifyDate(String dateString){
        String datearr[]= dateString.split("T");
        String RevDate = datearr[0];
       /* StringBuffer buffer = new StringBuffer(RevDate);
        buffer.reverse();*/
        return RevDate;
    }

    public String modifyTime(String timeString){
        String datearr1[]= timeString.split("T");
        String timewithz= datearr1[1];
        String timeaa[]=timewithz.split("Z");
        return timeaa[0];
    }
}
