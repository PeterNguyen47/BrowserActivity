package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.webPageInterface, PageViewerFragment.webPageInterface {

    PageControlFragment pageControlFragment;
    PageViewerFragment pageViewerFragment;
    BrowserControlFragment browserControlFragment;
    PageListFragment pageListFragment;
    PagerFragment pagerFragment;

    FragmentManager fm;
    FragmentTransaction ft;

    Fragment control;
    Fragment viewer;
    Fragment browser;
    Fragment list;
    Fragment pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set browser app label
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        // Page control fragment
        control = fm.findFragmentById(R.id.page_control);
        if (control == null) {
            pageControlFragment = new PageControlFragment();
            control = pageControlFragment;
        ft.add(R.id.page_control, new PageControlFragment()).addToBackStack(null)
                .commit();
        }

        // Page viewer fragment
        viewer = fm.findFragmentById(R.id.page_display);
        if (viewer == null) {
            pageViewerFragment = new PageViewerFragment();
            viewer = pageViewerFragment;
        fm.beginTransaction()
                .add(R.id.page_display, pageViewerFragment).addToBackStack(null)
                .commit();
        }

        // Browser control fragment
        browser = fm.findFragmentById(R.id.browser_control);
        if (browser == null) {
            browserControlFragment = new BrowserControlFragment();
            browser = browserControlFragment;
        fm.beginTransaction()
                .add(R.id.browser_control, browserControlFragment)
                .commit();
        }

        //TODO add pager frag?

    }

    // Prevent fragments overlapping
    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PageControlFragment) {
            pageControlFragment = (PageControlFragment) fragment;
        }
        else if (fragment instanceof PageViewerFragment) {
            pageViewerFragment = (PageViewerFragment) fragment;
        }
        else if (fragment instanceof BrowserControlFragment) {
            browserControlFragment = (BrowserControlFragment) fragment;
        }
    }

    @Override
    public void goClicked() {
        // When user clicks go button, goClicked is called from interface and communicated with viewer to load page
        pageViewerFragment.newPage(pageControlFragment.urlEditText.getText().toString());
    }

    @Override
    public void backClicked() {
        // When user clicks back button, canGoBackClicked is called
        pageViewerFragment.canGoBackClicked();
    }

    @Override
    public void nextClicked() {
        // When user clicks next button, canGoForwardClicked is called
        pageViewerFragment.canGoForwardClicked();
    }

    @Override
    public void updatePage(String url) {
        // When user selects link on web page, Page Viewer Fragment will display new web page
        Log.d("Current URL", "Current URL: " + url);

        // Telling page Control Frag to update URL
        pageControlFragment.updateURL(url);
    }


    //TODO get new web page from new page button
    //TODO activity should display as its title, the page title of the current webpage

}
