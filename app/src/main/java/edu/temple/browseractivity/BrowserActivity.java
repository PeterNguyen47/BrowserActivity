package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.webPageInterface,
        PageViewerFragment.webPageInterface,
        BrowserControlFragment.webPageInterface,
        PageListFragment.TabSelectionListener,
        PagerFragment.pagerInterface {

    PageControlFragment pageControlFragment;
    PageViewerFragment pageViewerFragment;
    BrowserControlFragment browserControlFragment;
    PageListFragment pageListFragment;
    PagerFragment pagerFragment;

    FragmentManager fm;
    FragmentTransaction ft;

    ArrayList<PageViewerFragment> fragments;
    ArrayList<String> webPageTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();

        if (fm.findFragmentById(R.id.page_control) == null
                && fm.findFragmentById(R.id.page_display) == null) {

            fragments = new ArrayList<PageViewerFragment>();
            fragments.add(new PageViewerFragment());


            pageControlFragment = new PageControlFragment();
            ft.add(R.id.page_control, pageControlFragment);

            pagerFragment = new PagerFragment();
            ft.add(R.id.page_display, pagerFragment);

            browserControlFragment = new BrowserControlFragment();
            ft.add(R.id.browser_control, browserControlFragment);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                pageListFragment = new PageListFragment();
                ft.add(R.id.page_list, pageListFragment);
            }
            ft.commit();
        }
        else {

            pageControlFragment = (PageControlFragment) fm.findFragmentById(R.id.page_control);

            browserControlFragment = (BrowserControlFragment) fm.findFragmentById(R.id.browser_control);

            pagerFragment = (PagerFragment) fm.findFragmentById(R.id.page_display);

            if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation){

                if(pageListFragment == null) {
                    pageListFragment = new PageListFragment();
                    fm = getSupportFragmentManager();

                    ft = fm.beginTransaction();
                    ft.add(R.id.page_list, pageListFragment);
                    ft.commit();
                }
                else {
                    pageListFragment = (PageListFragment) fm.findFragmentById(R.id.page_list);
                }
            }
        }
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
        else if (fragment instanceof PageListFragment) {
            pageListFragment = (PageListFragment) fragment;
        }
        else if (fragment instanceof PagerFragment) {
            pagerFragment = (PagerFragment) fragment;
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
        fragments.get(pagerFragment.getCurrentPage()).canGoBackClicked();
        if (pageListFragment != null ) {
            pageListFragment.setChange();
        } else {
        setTitle(fragments.get(pagerFragment.getCurrentPage()).getPageText());
        }
    }

    @Override
    public void setWebURL() {
        pageControlFragment.getText(fragments.get(pagerFragment.getCurrentPage()).getWebPage());
        if (pageListFragment != null) {
            pageListFragment.setChange();
        } else {
        setTitle(fragments.get(pagerFragment.getCurrentPage()).getPageText());
        }
    }

    @Override
    public void nextClicked() {
        // When user clicks next button, canGoForwardClicked is called
        pageViewerFragment.canGoForwardClicked();
        fragments.get(pagerFragment.getCurrentPage()).canGoForwardClicked();
        if (pageListFragment != null) {
            pageListFragment.setChange();
        } else {
        setTitle(fragments.get(pagerFragment.getCurrentPage()).getPageText());
        }
    }

    @Override
    public void updatePage(String url) {
        // When user selects link on web page, Page Viewer Fragment will display new web page
        Log.d("Current URL", "Current URL: " + url);

        // Telling page Control Frag to update URL
        pageControlFragment.updateURL(url);
    }

    @Override
    public void changeURLTitle(String urlTitle) {
        getSupportActionBar().setTitle((CharSequence) webPageTitles);
        pageListFragment.setRetainInstance(true);
    }

    public void countPage(String pageTitle) {
        webPageTitles.add(pageTitle);
    }

    @Override
    public void newPageClicked() {
        pageViewerFragment.anotherPage();
        fragments.add(new PageViewerFragment());
        pagerFragment.setChange();
        if (pageListFragment != null) {
            pageListFragment.setChange();
        } else {
        pagerFragment.getNewPage(fragments.size() -1);
        setTitle(fragments.get(pagerFragment.getCurrentPage()).getPageText());
        }
    }

    @Override
    public void onTabSelected(int position) {
        pagerFragment.onTabSelected(position);
        pagerFragment.getNewPage(position);
        if (pageListFragment != null) {
            pageListFragment.setChange();
        } else {
        setTitle(fragments.get(pagerFragment.getCurrentPage()).getPageText());
        }
    }

    @Override
    public ArrayList<PageViewerFragment> getItem() {
            return fragments;
    }
}