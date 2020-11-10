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
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.webPageInterface,
        PageViewerFragment.webPageInterface,
        BrowserControlFragment.webPageInterface,
        PageListFragment.webPageInterface,
        PagerFragment.webPageInterface {

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

    Boolean landscape;

    ArrayList<String> listOfURLs;
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
            pageControlFragment = new PageControlFragment();
            control = pageControlFragment;
            ft.add(R.id.page_control, new PageControlFragment()).addToBackStack(null)
                    .commit();
        }

        // Page viewer fragment manager
        viewer = fm.findFragmentById(R.id.page_display);
        if (viewer == null) {
            pageViewerFragment = new PageViewerFragment();
            viewer = pageViewerFragment;
            fm.beginTransaction()
                    .add(R.id.page_display, pageViewerFragment).addToBackStack(null)
                    .commit();
        }

        // Browser control fragment manager
        browser = fm.findFragmentById(R.id.browser_control);
        if (browser == null) {
            browserControlFragment = new BrowserControlFragment();
            browser = browserControlFragment;
            fm.beginTransaction()
                    .add(R.id.browser_control, browserControlFragment)
                    .commit();
        }

        // Page list fragment manager
        list = fm.findFragmentById(R.id.page_list);
        if (landscape) {
            pageListFragment = new PageListFragment();
            fm.beginTransaction()
                    .add(R.id.page_list, pageListFragment)
                    .commit();
        }

        /*listOfURLs = new ArrayList<>();
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return listOfURLs.get(position);
            }

            @Override
            public int getCount() {
                return listOfURLs.size();
            }
        });

        findViewById(R.id.newPageBtn).setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listOfURLs.toArray(list);
                viewPager.getAdapter().notifyDataSetChanged();
            }
        });*/

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
    public void changeAppTitle(String app) {
    }

    @Override
    public void newPageClicked() {
        pageViewerFragment.anotherPage();
    }

    // method to talk to pageViewerFragment????
    public void showItem(ArrayList<String> listArray) {
        if (landscape) {
            pageViewerFragment.newPage(pageControlFragment.urlEditText.getText().toString());
        }
    }

    // method to talk to ????
    public void itemClicked(int item, String list){
        if (landscape) {
            pageViewerFragment.listPage();
        }
    }
}

//TODO activity should display current URL as its title and update with each URL
//TODO fix pagerViewer