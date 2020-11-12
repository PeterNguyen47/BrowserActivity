package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class PagerFragment extends Fragment {

    private static final String WEB_KEY = "webKey";

    View l;
    ArrayList<PageViewerFragment> fragments;

    ViewPager viewPager;
    ArrayList<String> listOfUrls;

    webPageInterface parentActivity;
    MyFragmentAdapter myFragmentAdapter;

    public PagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_pagerView));
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
        l = inflater.inflate(R.layout.fragment_pager, container, false);

        viewPager = l.findViewById(R.id.viewPager);

        myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), fragments);

        if (savedInstanceState != null) {
            //fragments = savedInstanceState.getInt(WEB_KEY);
            viewPager.setAdapter(myFragmentAdapter);
            viewPager.getAdapter().notifyDataSetChanged();
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    parentActivity.getItem(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
        return l;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(WEB_KEY, listOfUrls);
    }

    interface webPageInterface {
        void getItem(int position);
    }
}

//TODO in landscape, have searched URLs populate listView as a textView
//TODO in landscape, when URLs from listView clicked, corresponding web page is displayed (should notify activity when item is clicked)
