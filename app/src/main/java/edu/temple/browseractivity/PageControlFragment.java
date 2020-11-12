package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class PageControlFragment extends Fragment {

    View l;
    Context context;

    EditText urlEditText;
    ImageButton goBtn, backBtn, nextBtn;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
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
                Log.d("Click Go", "URL updated to: " + urlEditText.getText());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.backClicked();
                Log.d("Clicks", "Back is clicked: " + (parentActivity != null));
                Log.d("Click Back", "URL updated to: " + urlEditText.getText());
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.nextClicked();
                Log.d("Clicks", "Next is clicked: " + (parentActivity != null));
                Log.d("Click Next", "URL updated to: " + urlEditText.getText());
            }
        });

        return l;
    }

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