package edu.temple.browseractivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PageListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> listOfUrls;
    TextView textView;

    public PageListAdapter(Context context, ArrayList<String> webPageTitles) {
        this.listOfUrls = webPageTitles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listOfUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            textView = new TextView(context);
            textView.setText(getItem(position).toString());
        }
        else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
