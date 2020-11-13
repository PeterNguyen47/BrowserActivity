package edu.temple.browseractivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<PageViewerFragment> pageList;
    TextView textView;

    public ListAdapter(Context context, ArrayList<PageViewerFragment> pageLists) {
        this.context = context;
        pageList = pageLists;
    }

    @Override
    public int getCount() {
        return pageList.size();
    }

    @Override
    public Object getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            textView = new TextView(context);
        }
        else {
            textView = (TextView) convertView;
        }
        textView.setText(pageList.get(position).getPageText());
        return textView;
    }
}
