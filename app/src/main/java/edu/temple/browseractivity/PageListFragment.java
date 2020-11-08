package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    View l;

    webPageInterface parentActivity;

    //TODO Fragment should contain a listview and is able to notify the activity when an item in the list has been clicked
    //TODO Items in listview should be textviews that will display title of current webpage by associated PageViewFragment
    //TODO When item on ListView is clicked, PageViewerFragment associated with that webpage should display in the PagerFragment attached to page_display container


    public PageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_list, container, false);

        return l;
    }

    interface webPageInterface {
        void itemSelected(int item, ArrayList<PageViewerFragment> list);
        void sendList(ListView listView);

    }
}