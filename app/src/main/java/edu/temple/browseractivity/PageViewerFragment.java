package edu.temple.browseractivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.util.Objects;

public class PageViewerFragment extends Fragment implements Parcelable {

    View l;
    Context context;
    WebView webView;

    public PageViewerFragment() {
    }

    public PageViewerFragment(Parcel in) {
        webView.restoreState(Objects.requireNonNull(in.readBundle(getClass().getClassLoader())));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle args = new Bundle();
        webView.saveState(args);
        dest.writeBundle(args);
    }

    public static final Parcelable.Creator CREATOR = new Creator<PageViewerFragment>() {
        @Override
        public PageViewerFragment createFromParcel(Parcel in) {
            return new PageViewerFragment(in);
        }

        @Override
        public PageViewerFragment[] newArray(int size) {
            return new PageViewerFragment[size];
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            webView.loadUrl(getString(R.string.home));
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.fragment_page_viewer, container, false);

        webView = l.findViewById(R.id.webView);

        // Enable Javascript
        webView.getSettings().setJavaScriptEnabled(true);
        if (savedInstanceState == null) {
            webView.loadUrl(getString(R.string.home));
        } else {
            webView.restoreState(savedInstanceState);
        }

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                ((PageControlFragment.ControlInterface) context).setURL();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                ((PageControlFragment.ControlInterface) context).setURL();
            }
        });
        return l;
    }

    public void canGoBackClicked(){
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            Toast.makeText(context, "No Previous History", Toast.LENGTH_SHORT).show();
        }
    }

    public void canGoForwardClicked(){
        if (webView.canGoForward()) {
            webView.goForward();
        } else {
            Toast.makeText(context, "Future is not yet written", Toast.LENGTH_SHORT).show();
        }
    }

    // setters and getters for web page URL and name
    public void setLink(String url) {
        webView.loadUrl(url);
    }

    public String getURL(){
        return webView.getUrl();
    }

    public String getPageName(){
        return webView.getTitle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

}