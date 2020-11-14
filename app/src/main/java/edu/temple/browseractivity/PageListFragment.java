package edu.temple.browseractivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class PageListFragment extends Fragment {

    View l;

    ListView listView;
    ListAdapter listAdapter;

    public PageListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        l =inflater.inflate(R.layout.fragment_page_list, container, false);

        listView = l.findViewById(R.id.listView);
        listAdapter = new ListAdapter(getContext(), ((PagerFragment.pagerInterface) getActivity()).getPages());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ((listInterface) getActivity()).getPage(position);
            }
        });

        return l;
    }

    public void setNotificationChange(){
        if (listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        }
    }

    interface listInterface {
        void getPage(int reference);
    }
}
