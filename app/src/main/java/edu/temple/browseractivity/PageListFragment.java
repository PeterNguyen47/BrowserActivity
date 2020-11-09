package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    View l;

    ListView pageList;
    ArrayList<String> urlList;

    webPageInterface parentActivity;

    //TODO Fragment should contain a listview and is able to notify the activity when an item in the list has been clicked
    //TODO Items in listview should be textviews that will display title of current webpage by associated PageViewFragment
    //TODO When item on ListView is clicked, PageViewerFragment associated with that webpage should display in the PagerFragment attached to page_display container

    public PageListFragment() {
        // Required empty public constructor
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

    //TODO have searched URLs populate listView only when in landscape
    public void showItemInList(final ArrayList<String> listArray) {
        pageList.findViewById(R.id.pageList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listArray);
        pageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                urlList = (ArrayList<String>) pageList.getItemAtPosition(itemPosition);

                parentActivity.showItem(urlList);
            }
        });
    }

    //TODO when URLs in listView clicked, corresponding web page is displayed (should notify activity when item is clicked)
    public void listItemClick(ListView listView, View v, int position, long id) {
        urlList = new ArrayList<>();

        if (urlList.size() != 0) {
            parentActivity.itemClicked(position, urlList.get(position));
        }
    }

    // interface to talk to activity
    public interface webPageInterface {
        void showItem(ArrayList<String> listArray);
        void itemClicked(int item, String list);

    }
}