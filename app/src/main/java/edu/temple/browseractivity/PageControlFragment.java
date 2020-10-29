package edu.temple.browseractivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;

public class PageControlFragment extends Fragment {

    public static final String WEB_KEY = "webKey";
    private static String newURL;

    View l;
    Context context;

    WebView webView;
    EditText urlEditText;
    ImageButton goBtn;
    ImageButton backBtn;
    ImageButton nextBtn;

    webPageInterface parentActivity;

    public PageControlFragment() {}

    /*public static PageControlFragment newInstance(String newURL) {
        PageControlFragment pcf = new PageControlFragment();
        Bundle args = new Bundle();
        args.putString(WEB_KEY,newURL);
        pcf.setArguments(args);
        return pcf;
    }*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        }
        else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_control));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_page_control, container, false);

        goBtn = l.findViewById(R.id.goBtn);
        backBtn = l.findViewById(R.id.backBtn);
        nextBtn = l.findViewById(R.id.nextBtn);
        urlEditText = l.findViewById(R.id.urlEditText);

        context = l.getContext();

        // Listeners set for each button's functionality
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goClicked();
                Log.d("Clicks","Go is clicked: " + (parentActivity != null));

                urlEditText.setText((urlLink()));
                Log.d("Clicks", "URL updated to: " + urlLink());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.backClicked();
                Log.d("Clicks","Back is clicked: " + (parentActivity != null));

                //TODO get Edit Text to update URL of previous web page in Edit Text
                urlEditText.setText(urlLink());
                Log.d("Clicks", "URL updated to: " + urlEditText.getText().toString());


            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.nextClicked();
                Log.d("Clicks","Next is clicked: " + (parentActivity != null));
            }
        });

        return l;
    }

    // Method that contains web page URL
    public String urlLink() {
        newURL = urlEditText.getText().toString();
        Log.d("New URL","New URL: " + newURL);
        return newURL;
    }

    // Method to update URL in urlEditText (search bar)
    public void updateURL(String urlText) {
        urlEditText.setText(urlText);
    }

    interface webPageInterface {
        void goClicked();
        void nextClicked();
        void backClicked();
    }
}