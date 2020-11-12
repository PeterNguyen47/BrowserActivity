package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.webPageInterface,
        PageViewerFragment.webPageInterface,
        BrowserControlFragment.webPageInterface,
        PageListFragment.webPageInterface,
        PagerFragment.webPageInterface {

    public static final String URL_KEY = "urlKey";

    PageControlFragment pageControlFragment;
    PageViewerFragment pageViewerFragment;
    BrowserControlFragment browserControlFragment;
    PageListFragment pageListFragment;
    PagerFragment pagerFragment;
    BaseAdapter PageListAdapter;
    MyFragmentAdapter myFragmentAdapter;

    FragmentManager fm;
    FragmentTransaction ft;

    Fragment control;
    Fragment viewer;
    Fragment browser;
    Fragment list;
    Fragment pager;

    ArrayList<PageViewerFragment> fragments;
    ArrayList<String> webPageTitles;

    Boolean landscape;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        landscape = findViewById(R.id.page_list) != null;

        // Set browser app label
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        // Page control fragment manager
        control = fm.findFragmentById(R.id.page_control);
        if (control == null) {
            control = pageControlFragment;
            ft.add(R.id.page_control, new PageControlFragment()).addToBackStack(null)
                    .commit();
        }

        // Page viewer fragment manager
        viewer = fm.findFragmentById(R.id.page_display);
        if (viewer == null) {
            viewer = pageViewerFragment;
            fm.beginTransaction()
                    .add(R.id.page_display, new PageViewerFragment()).addToBackStack(null)
                    .commit();
        }

        // Browser control fragment manager
        browser = fm.findFragmentById(R.id.browser_control);
        if (browser == null) {
            browser = browserControlFragment;
            fm.beginTransaction()
                    .add(R.id.browser_control, new BrowserControlFragment())
                    .commit();
        }

        // Page list fragment manager
        list = fm.findFragmentById(R.id.page_list);
        if (landscape && list == null) {
            fm.beginTransaction().add(R.id.page_list, new PageListFragment())
                    .commit();
        }

        // Pager fragment fragment manager
        pager = fm.findFragmentById(R.id.viewPager);
        if (pager == null) {
            fm.beginTransaction().add(R.id.page_display, new PagerFragment())
                    .commit();
        }

        // Page view fragment manager
        viewer = fm.findFragmentById(R.id.webView);
        if (viewer == null) {
            fm.beginTransaction().add(R.id.page_display, new PageViewerFragment())
                    .commit();
        }

        if (myFragmentAdapter == null) {
            myFragmentAdapter = new MyFragmentAdapter(fm, fragments);
        }

        if (fragments == null) {
            fragments = new ArrayList<>();
            viewPager = findViewById(R.id.viewPager);
            viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @NonNull
                @Override
                // Returns each web page fragment by position
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                // Number of web page fragments
                @Override
                public int getCount() {
                    return fragments.size();
                }
            });
        }

        findViewById(R.id.newPageBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add to array list a new web page fragment
                fragments.add(new PageViewerFragment());
                // tell viewPager Adapter that data has changed
                viewPager.getAdapter().notifyDataSetChanged();
            }
        });
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

    //TODO activity should display as its title, the page title of the current webpage
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
    }

    @Override
    public void sendList(ListView listView) {
        listView.setAdapter(PageListAdapter);
        listView.getAdapter();
    }

    @Override
    public void itemSelected(int item, ArrayList<PageViewerFragment> pageViewerFragmentArrayList) {
        if (landscape) {
            pageViewerFragment.listPage();
        }
    }

    @Override
    public void getItem(int position) {
        if (landscape) {
            pageViewerFragment.listPage();
        }
    }
}

//TODO activity should display current URL as its title and update with each URL
//TODO fix pagerViewer