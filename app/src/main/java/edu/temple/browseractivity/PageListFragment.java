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

    private static final String WEB_KEY1 = "webKey1";
    private static final String WEB_KEY2 = "webKey2";

    View l;

    ArrayList<PageViewerFragment> viewerFragments;
    ArrayList<String> webPageTitles;
    ListView listView;
    EditText urlEditText;
    TextView textView;

    int page;
    String title;

    webPageInterface parentActivity;

    public static PageListFragment newInstance(int page, String title) {
        PageListFragment pageListFragment = new PageListFragment();
        Bundle args = new Bundle();
        args.putInt(WEB_KEY1, page);
        args.putString(WEB_KEY2, title);
        pageListFragment.setArguments(args);
        return pageListFragment;
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
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(WEB_KEY1);
        title = getArguments().getString(WEB_KEY2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_list, container, false);

        listView = l.findViewById(R.id.listView);
        urlEditText = l.findViewById(R.id.urlEditText);
        textView = l.findViewById(R.id.textView);
        textView.setText(page + title);

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