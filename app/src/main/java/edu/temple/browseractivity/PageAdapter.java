package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

public class PageAdapter extends FragmentStatePagerAdapter {

    ArrayList<PageViewerFragment> pages;

    public PageAdapter(@NonNull FragmentManager fm, ArrayList<PageViewerFragment> pages) {
        super(fm);
        this.pages = pages;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}