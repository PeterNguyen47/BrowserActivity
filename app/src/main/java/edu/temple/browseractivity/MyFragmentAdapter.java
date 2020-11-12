package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<PageViewerFragment> fragments;
    FragmentManager fm;

    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, ArrayList<PageViewerFragment> fragments1) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fm = fragmentManager;
        fragments = fragments1;
    }

    @NonNull
    @Override
    public PageViewerFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
