package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.ControlInterface,
        BrowserControlFragment.BrowserInterface,
        PagerFragment.pagerInterface,
        PageListFragment.ListInterface,
        BrowserControlFragment.BookMarkInterface,
        BookMarkFragment.BookMarkListInterface {

    private static final String KEY = "key";

    private static final String SHARE_KEY = "shareKey";

    private ShareActionProvider shareActionProvider;

    BrowserControlFragment browserControlFragment;
    PageControlFragment pageControlFragment;
    PageListFragment pageListFragment;
    PagerFragment pagerFragment;
    BookMarkFragment bookMarkFragment;

    FrameLayout BookMarkView;

    ArrayList<PageViewerFragment> fragments;

    FragmentManager fm;
    FragmentTransaction ft;

    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookMarkView = findViewById(R.id.book_mark_view);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        orientation = getResources().getConfiguration().orientation;

        if (fm.findFragmentById(R.id.page_control) == null && fm.findFragmentById(R.id.page_display) == null){
            browserControlFragment = new BrowserControlFragment();
            pageControlFragment = new PageControlFragment();
            pagerFragment = new PagerFragment();
            bookMarkFragment = new BookMarkFragment();

            fragments = new ArrayList<>();
            fragments.add(new PageViewerFragment());

            ft.add(R.id.page_control, pageControlFragment)
                    .add(R.id.browser_control, browserControlFragment)
                    .add(R.id.page_display, pagerFragment)
                    .add(R.id.book_mark_view, bookMarkFragment);

            if (orientation == Configuration.ORIENTATION_LANDSCAPE){
                pageListFragment = new PageListFragment();
                ft.add(R.id.page_list, pageListFragment);
            }
            ft.commit();

        } else {
            fragments = savedInstanceState.getParcelableArrayList(KEY);

            pageControlFragment = (PageControlFragment) fm.findFragmentById(R.id.page_control);
            browserControlFragment = (BrowserControlFragment) fm.findFragmentById(R.id.browser_control);
            pagerFragment = (PagerFragment) fm.findFragmentById(R.id.page_display);
            bookMarkFragment = (BookMarkFragment) fm.findFragmentById(R.id.book_mark_view);

            if(orientation == Configuration.ORIENTATION_LANDSCAPE && pageListFragment == null) {
                pageListFragment = new PageListFragment();
                ft.add(R.id.page_list, pageListFragment)
                        .commit();
            } else {
                pageListFragment = (PageListFragment) fm.findFragmentById(R.id.page_list);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String url = pageControlFragment.setText(fragments.get(pagerFragment.getPage()).getURL());
                intent.putExtra(Intent.EXTRA_TEXT, url);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(intent, null));
                }
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, fragments);
    }

    public void goClicked(String urlInput){
        if (!urlInput.startsWith("https://") || urlInput.startsWith("http://")) {
            urlInput = ("https://" + urlInput);
        } else if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }

        fragments.add(new PageViewerFragment());
        fragments.get(pagerFragment.getPage()).setLink(urlInput);
        pagerFragment.setNotification();
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
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
        pagerFragment.setNotification();
        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }
        pagerFragment.setPage(fragments.size());
    }

    @Override
    public void setURL() {
        pageControlFragment.setText(fragments.get(pagerFragment.getPage()).getURL());
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }
    }

    public void getPage(int reference){
        pagerFragment.setPage(reference);
        setTitle(fragments.get(pagerFragment.getPage()).getPageName());
        if (pageListFragment != null) {
            pageListFragment.setNotificationChange();
        }
    }

    @Override
    public void showBookMarkFragment() {
        if (BookMarkView.getVisibility() == View.INVISIBLE){
            BookMarkView.setVisibility(View.VISIBLE);
        } else {
            BookMarkView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setBookedPage() {
        Toast.makeText(this, R.string.saveBookMarkClick, Toast.LENGTH_SHORT).show();
        String string = fragments.get(pagerFragment.getPage()).getURL();
        bookMarkFragment.setBookMark(string);
    }

    @Override
    public ArrayList<PageViewerFragment> getPages() {
        return fragments;
    }
}
