package edu.temple.browseractivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.BreakIterator;

public class BrowserControlFragment extends Fragment {

    View l;
    Context context;

    ImageButton newPageBtn;
    webPageInterface parentActivity;

    public BrowserControlFragment() {
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
        l = inflater.inflate(R.layout.fragment_browser_control, container, false);

        l.findViewById(R.id.newPageBtn);

        context = l.getContext();

        newPageBtn = new ImageButton(context);
        // Listener for new page button
        newPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            parentActivity.newPageClicked();

            //TODO button should create a new browser page (new instance of PageViewFragment) and display ViewPager in PagerFragment
            //TODO if in Landscape, page should be 'listed' in Listview on PageListFragment
            //TODO when clicked
            }
        });

        return l;

    }

    interface webPageInterface {
        void newPageClicked();

    }
}