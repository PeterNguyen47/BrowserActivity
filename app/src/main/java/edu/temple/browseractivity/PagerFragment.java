package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerFragment extends Fragment {

    View l;

    ViewPager pagerViewer;

    webPageInterface parentActivity;


    //TODO will use a ViewPager and FragmentStatePagerAdapter to allow user to use swiping to switch instances of PageViewerFragments
    //TODO when user creates a new page from the BrowserControlFragment, it should add to end of the ViewPager and automatically displayed
    //TODO swiping to another page should updated in search bar


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

    //public void sendList(int item){
        //viewPager.setCurrentItem(item);
    //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_pager, container, false);

        return l;
    }

    interface webPageInterface {
    }
}