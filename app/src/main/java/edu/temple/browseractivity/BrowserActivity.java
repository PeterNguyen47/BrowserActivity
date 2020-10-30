package edu.temple.browseractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.webPageInterface, PageViewerFragment.webPageInterface {

    PageControlFragment pageControlFragment;
    PageViewerFragment pageViewerFragment;

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Set browser app label
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);

        pageControlFragment = new PageControlFragment();
        pageViewerFragment = new PageViewerFragment();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        // Page control fragment
        fm.findFragmentById(R.id.page_control);
        fm.beginTransaction()
                .add(R.id.page_control, pageControlFragment).addToBackStack(null)
                .commit();

        // Page viewer fragment
        fm.findFragmentById(R.id.page_viewer);
        fm.beginTransaction()
                .add(R.id.page_viewer, pageViewerFragment).addToBackStack(null)
                .commit();
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
}
