package edu.temple.browseractivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    PageAdapter pageAdapter;

    public PagerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.fragment_pager, container, false);

        assert getFragmentManager() != null;
        pageAdapter = new PageAdapter(getFragmentManager(), ((pagerInterface) Objects.requireNonNull(getActivity())).getPages());

        viewPager = l.findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ((PageControlFragment.ControlInterface) Objects.requireNonNull(getContext())).setURL();
            }

            @Override
            public void onPageSelected(int position) {
                ((PageControlFragment.ControlInterface) Objects.requireNonNull(getContext())).setURL();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ((PageControlFragment.ControlInterface) Objects.requireNonNull(getContext())).setURL();
            }
        });
        return l;
    }

    @Override
    public void onResume() {
        super.onResume();
        setNotification();
    }

    public void setPage(int index){
        viewPager.setCurrentItem(index);
    }

    public void setNotification(){
        pageAdapter.notifyDataSetChanged();
    }


    public int getPage(){
        return viewPager.getCurrentItem();
    }

    interface pagerInterface {
        ArrayList<PageViewerFragment> getPages();
    }
}
