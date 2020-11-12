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

        if (fragments == null) {
            fragments = new ArrayList<>();
            viewPager = l.findViewById(R.id.viewPager);
            viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
                @NonNull
                @Override
                // Returns each web page fragment by position
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getItemPosition(@NonNull Object object) {
                    if (fragments.contains(object)) {
                        return fragments.indexOf(object);
                    } else {
                        return POSITION_NONE;
                    }
                }

                // Number of web page fragments
                @Override
                public int getCount() {
                    return fragments.size();
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
