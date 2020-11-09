package edu.temple.browseractivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class URLListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> page;
    TextView textView;

    public URLListAdapter(Context context, ArrayList<String> page) {
        this.context = context;
        this.page = page;
    }

    @Override
    public int getCount() {
        return page.size();
    }

    @Override
    public Object getItem(int position) {
        return page.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            textView = new TextView(context);
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(getItem(position).toString());

        return textView;
    }
}
