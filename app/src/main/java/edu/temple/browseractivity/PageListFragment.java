package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

public class PageListFragment extends Fragment {

    private static final String TITLE_KEY = "titleKey";

    View l;

    ListView listView;

    ListAdapter listAdapter;

    TabSelectionListener parentActivity;

    public PageListFragment() {
        // Required empty public constructor
    }

    public static PageListFragment newInstance(String title) {
        PageListFragment pageListFragment = new PageListFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        pageListFragment.setArguments(args);
        return pageListFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TabSelectionListener) {
            parentActivity = (TabSelectionListener) context;
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
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_list, container, false);

        listView = l.findViewById(R.id.listView);

        listAdapter = new ListAdapter(getContext(),
                ((PagerFragment.pagerInterface) Objects.requireNonNull(getActivity()))
                        .getItem());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((PageListFragment.TabSelectionListener) Objects.requireNonNull(getActivity()))
                        .onTabSelected(position);
            }
        });
        return l;
    }

    public void setChange() {
        if (listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        }
    }

    public interface TabSelectionListener {
        void onTabSelected(int position);
    }
}