package edu.temple.browseractivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookMarkListAdapter extends BaseAdapter {
    Context context;
    TextView textView;
    ArrayList<String> bookMarkedPage;

    public BookMarkListAdapter(Context context, ArrayList<String> bookMarkedPage) {
        this.context = context;
        this.bookMarkedPage = bookMarkedPage;
    }

    @Override
    public int getCount() {
        return bookMarkedPage.size();
    }

    @Override
    public Object getItem(int position) {
        return bookMarkedPage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            textView = new TextView(context);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(bookMarkedPage.get(position));

        return convertView;
    }
}
