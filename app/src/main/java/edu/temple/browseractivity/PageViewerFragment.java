package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.MalformedURLException;

public class PageViewerFragment extends Fragment {

    private static final String WEB_KEY = "webKey";

    View l;
    Context context;
    WebView webView;
    webPageInterface parentActivity;

    EditText urlEditText;
    String url;

    public PageViewerFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_view));
        }
    }

    //TODO Have UI state save after orientation change
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_viewer, container, false);

        context = l.getContext();

        urlEditText = l.findViewById(R.id.urlEditText);
        webView = l.findViewById(R.id.webView);

        // Homepage set to Google.com
        webView.loadUrl(getString(R.string.home));

        // Enable Javascript
        webView.getSettings().setJavaScriptEnabled(true);

        // Getting links to open in webView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                parentActivity.updatePage();
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        return l;
    }

    // Method for currentURL
    public String currentURL() {
        url = webView.getUrl();
        return url;
    }

    // When a new URL is searched, URL is automatically corrected if needed
    public void newPage(String urlInput) {
        if (!(urlInput.equals("https://"))) {
            urlInput = ("https://www." + urlInput);
        }
        try {
            URL url = new URL(urlInput);
            webView.loadUrl(url.toString());
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
            parentActivity.updatePage();
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
        parentActivity.updatePage();
    }

    // Conditions when Next button is clicked
    public void canGoForwardClicked() {
        if (webView.canGoForward()) {
            webView.goForward();
        }
        else {
            Toast.makeText(context, "Future is not yet written", Toast.LENGTH_SHORT).show();
        }
        parentActivity.updatePage();
    }

    interface webPageInterface {
        void updatePage();
    }
}

//TODO Get URL to update in EditText
//TODO Save UI state in even of orientation rotation