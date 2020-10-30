package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class PageControlFragment extends Fragment {

    private final String WEB_KEY = "webKey";

    View l;
    Context context;

    EditText urlEditText;
    ImageButton goBtn;
    ImageButton backBtn;
    ImageButton nextBtn;

    String url;

    webPageInterface parentActivity;

    public PageControlFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_control));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Should load the saved Instance state of last web page after rotation
        /*if (savedInstanceState != null) {
            savedInstanceState.getString(urlEditText.getText().toString());
            Log.d("Load Saved","Saved Instance: " + savedInstanceState);
        }*/
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
                Log.d("Clicks", "Go is clicked: " + (parentActivity != null));

                urlEditText.setText(urlEditText.getText().toString());
                Log.d("Clicks", "URL updated to: " + urlEditText.getText().toString());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.backClicked();
                Log.d("Clicks", "Back is clicked: " + (parentActivity != null));

                urlEditText.setText(urlEditText.getText().toString());
                Log.d("Clicks", "URL updated to: " + urlEditText.getText().toString());
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.nextClicked();
                Log.d("Clicks", "Next is clicked: " + (parentActivity != null));
            }
        });

        return l;
    }

    // Instances are saving, but after 3 rotations, app throws NullPointerException
    // URL in nav bar seems to be layered for some reason
    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(WEB_KEY, url);
        super.onSaveInstanceState(outState);
        Log.d("Save", "Instance saved: " + true);
        Log.d("Save","OutState saved: " + outState.getString(WEB_KEY,url));
    }*/

    // Method to update URL in urlEditText (search bar)
    public void updateURL(String url) {
        urlEditText.setText(url);
    }

    interface webPageInterface {
        void goClicked();
        void nextClicked();
        void backClicked();
    }
}
