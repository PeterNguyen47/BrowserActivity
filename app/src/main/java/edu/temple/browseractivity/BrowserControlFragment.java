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

public class BrowserControlFragment extends Fragment {

    View l;
    Context context;

    ImageButton newPageBtn;
    webPageInterface parentActivity;

    public BrowserControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_browser_control));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_browser_control, container, false);

        newPageBtn = l.findViewById(R.id.newPageBtn);

        context = l.getContext();

        // Listener for new page button
        newPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            parentActivity.newPageClicked();
            Log.d("New Page","newPageBtn clicked: " + (parentActivity != null));
            }
        });

        return l;
    }

    // interface for fragment to talk to activity
    interface webPageInterface {
        void newPageClicked();
    }
}

//TODO in Landscape, visited URLs should append to ListView found in the PageListFragment
//TODO in landscape, clicked URLs from listView should display website in fragment_page_viewer layout
//TODO if newPageBtn clicked, new instance of PageViewerFragment should be created, attached and displayed in ViewPager in PagerFragment