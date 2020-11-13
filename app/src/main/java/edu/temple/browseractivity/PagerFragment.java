package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Objects;

public class PagerFragment extends Fragment {

    View l;

    ViewPager viewPager;

    PagerAdapter pagerAdapter;

    pagerInterface parentActivity;

    public PagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof pagerInterface) {
            parentActivity = (pagerInterface) context;
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

        assert getFragmentManager() != null;
        pagerAdapter = new PagerAdapter(getFragmentManager(), ((PagerFragment.pagerInterface) Objects.requireNonNull(getActivity())).getItem());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ((PageControlFragment.webPageInterface) Objects.requireNonNull(getActivity())).setWebURL();
            }

            @Override
            public void onPageSelected(int position) {
                ((PageControlFragment.webPageInterface) Objects.requireNonNull(getActivity())).setWebURL();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ((PageControlFragment.webPageInterface) Objects.requireNonNull(getActivity())).setWebURL();
            }
        });
        return l;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    public void onChange(){
        pagerAdapter.notifyDataSetChanged();
    }

    public int getCurrentPage(){
        return viewPager.getCurrentItem();
    }


    public void getNewPage(int num){
        viewPager.setCurrentItem(num);
    }

    public void setChange() {
    }

    interface pagerInterface {
        ArrayList<PageViewerFragment> getItem();
    }
}