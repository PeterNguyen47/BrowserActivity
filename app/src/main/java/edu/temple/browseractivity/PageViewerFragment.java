package edu.temple.browseractivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.Toast;

import java.net.URL;
import java.net.MalformedURLException;

public class PageViewerFragment extends Fragment {

    View l;
    Context context;
    WebView webView;
    URL url;
    webPageInterface parentActivity;

    public PageViewerFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        }
        else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_view));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_viewer, container, false);

        context = l.getContext();

        webView = l.findViewById(R.id.webView);

        // Getting links to open in webView not android browser
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                parentActivity.updatePage(url);

            }
        });

        // Enable Javascript
        webView.getSettings().setJavaScriptEnabled(true);

        if (savedInstanceState == null) {
            webView.loadUrl(getString(R.string.home));
        } else {
            webView.restoreState(savedInstanceState);
        }


        return l;
    }

    // When a new URL is searched, URL is automatically corrected if needed
    public void newPage(String urlInput) {
        if (!(urlInput.startsWith("https://") || urlInput.startsWith("http://"))) {
            urlInput = ("https://" + urlInput);
        }
        try {
            url = new URL(urlInput);
            webView.loadUrl(url.toString());
        }
        catch (MalformedURLException e) {
            Toast.makeText(context, "URL is Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    // Conditions when Back button is clicked
    public void canGoBackClicked() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            Toast.makeText(context, "No Previous History", Toast.LENGTH_SHORT).show();
        }
    }

    // Conditions when Next button is clicked
    public void canGoForwardClicked() {
        if (webView.canGoForward()) {
            webView.goForward();
        }
        else {
            Toast.makeText(context, "Future is not yet written", Toast.LENGTH_SHORT).show();
        }
    }

    // Conditions when new page button is clicked
    public void anotherPage() {
        webView.loadUrl(getString(R.string.home));
    }

    // conditions when a URL in the listView is clicked
    public void listPage() {
    }

    // interface to talk to fragment
    public interface webPageInterface {
        void updatePage(String url);
        void changeAppTitle(String app);
    }
}

//TODO when URLs in fragment_page_list layout are clicked, corresponding website is displayed in fragment_page_viewer layout