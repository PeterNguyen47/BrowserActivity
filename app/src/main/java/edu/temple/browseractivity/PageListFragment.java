package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class PageListFragment extends Fragment {

    private static final String WEB_KEY = "webKey";

    View l;

    ArrayList<PageViewerFragment> viewerFragments;
    ArrayList<String> webPageTitles;
    ListView listView;
    EditText urlEditText;
    TextView textView;
    PageListAdapter pageListAdapter;

    webPageInterface parentActivity;

    public PageListFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_page_list));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putStringArrayList(WEB_KEY, webPageTitles);
        super.onSaveInstanceState(outState);
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

        listView = l.findViewById(R.id.listView);
        urlEditText = l.findViewById(R.id.urlEditText);

        pageListAdapter = new PageListAdapter(getActivity(), webPageTitles);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.itemSelected(position, viewerFragments);
            }
        });
        return l;
    }

    public void sendList(ArrayList<String> webPageList) {
        webPageTitles = webPageList;
        parentActivity.sendList(listView);
    }

    // interface to talk to activity
    public interface webPageInterface {
        void sendList(ListView listView);
        void itemSelected(int item, ArrayList<PageViewerFragment> pageViewerFragmentArrayList);
    }
}

//TODO in landscape, have searched URLs populate listView as a textView
//TODO in landscape, when URLs from listView clicked, corresponding web page is displayed (should notify activity when item is clicked)