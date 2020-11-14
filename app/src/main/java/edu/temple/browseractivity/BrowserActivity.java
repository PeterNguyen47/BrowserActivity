package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements BrowserControlFragment.BrowserInterface,
        PageControlFragment.ControlInterface,
        PageListFragment.listInterface,
        PagerFragment.pagerInterface {

    private static final String KEY = "key";

    BrowserControlFragment browserControlFragment;
    PageControlFragment pageControlFragment;
    PageListFragment pageListFragment;
    PagerFragment pagerFragment;

    ArrayList<PageViewerFragment> fragments;
    FragmentManager fm;
    FragmentTransaction ft;

    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        orientation = getResources().getConfiguration().orientation;

        if (fm.findFragmentById(R.id.page_control) == null && fm.findFragmentById(R.id.page_display) == null) {
            browserControlFragment = new BrowserControlFragment();
            pageControlFragment = new PageControlFragment();
            pagerFragment = new PagerFragment();

            fragments = new ArrayList<>();
            fragments.add(new PageViewerFragment());

            ft.add(R.id.browser_control, browserControlFragment)
                    .add(R.id.page_control, pageControlFragment)
                    .add(R.id.page_display, pagerFragment);

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pageListFragment = new PageListFragment();
                ft.add(R.id.page_list, pageListFragment);
            }
            ft.commit();

        }
        else {
            fragments = savedInstanceState.getParcelableArrayList(KEY);

            pageControlFragment = (PageControlFragment) fm.findFragmentById(R.id.page_control);
            browserControlFragment = (BrowserControlFragment) fm.findFragmentById(R.id.browser_control);
            pagerFragment = (PagerFragment) fm.findFragmentById(R.id.page_display);

            if (orientation == Configuration.ORIENTATION_LANDSCAPE && pageListFragment == null) {
                pageListFragment = new PageListFragment();
                ft.add(R.id.page_list, pageListFragment);
                ft.commit();
            }
            else {
                pageListFragment = (PageListFragment) fm.findFragmentById(R.id.page_list);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, fragments);
    }

    @Override
    public void goClicked(String urlInput){
        if (!(urlInput.startsWith("https://") || urlInput.startsWith("http://"))) {
            urlInput = ("https://" + urlInput);
        }

        fragments.get(pagerFragment.getPage()).setLink(urlInput);
        pagerFragment.setNotification();

        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }

        pageControlFragment.setText(fragments.get(pagerFragment.getPage()).getURL());
    }

    @Override
    public void backClicked() {
        fragments.get(pagerFragment.getPage()).canGoBackClicked();
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }
    }

    @Override
    public void nextClicked() {
        fragments.get(pagerFragment.getPage()).canGoForwardClicked();
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }
    }

    @Override
    public void newPageClicked() {
        fragments.add(new PageViewerFragment());
        pagerFragment.setNotification();
        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }
        pagerFragment.setPage(fragments.size());
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
    }

    @Override
    public void setURL() {
        pageControlFragment.setText(fragments.get(pagerFragment.getPage()).getURL());
        if(pageListFragment !=null) pageListFragment.setNotificationChange();
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
    }

    public void getPage(int reference){
        pagerFragment.setPage(reference);
        if(pageListFragment !=null) pageListFragment.setNotificationChange();
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
    }

    @Override
    public ArrayList<PageViewerFragment> getPages() {
        return fragments;
    }
}