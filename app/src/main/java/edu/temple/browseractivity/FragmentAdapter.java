package edu.temple.browseractivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<PageViewerFragment> links;
    FragmentManager fm;
    PageViewerFragment pageViewerFragment;

    public FragmentAdapter(@NonNull FragmentManager fm, ArrayList<PageViewerFragment> list) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        links = list;
    }
    @Override
    public int getCount() {
        return links.size();
    }

    @NonNull
    @Override
    public PageViewerFragment getItem(int position) {
        return links.get(position);
    }

}