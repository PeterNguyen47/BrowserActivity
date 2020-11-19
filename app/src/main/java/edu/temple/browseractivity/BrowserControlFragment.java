package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

public class BrowserControlFragment extends Fragment {

    private static final String KEY = "key";

    Context context;

    View l;

    BrowserInterface parentActivity;

    public BrowserControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BrowserInterface) {
            parentActivity = (BrowserInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_browser_control));
        }
    }

    public static BrowserControlFragment newInstance(String fragmentId) {
        BrowserControlFragment fragment = new BrowserControlFragment();
        Bundle args = new Bundle();
        args.putString(KEY, fragmentId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(outState);
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
        l = inflater.inflate(R.layout.fragment_browser_control, container, false);

        l.findViewById(R.id.newPageBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BrowserInterface) Objects.requireNonNull(getActivity())).newPageClicked();
            }
        });




        l.findViewById(R.id.saveBookMarkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Current URL should be saved into a list
                //TODO Have URLs saved even when exiting application and reopening
                ((BrowserInterface) Objects.requireNonNull(getActivity())).saveBookMarkClicked();
            }
        });

        l.findViewById(R.id.bookmarkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Launch BookMarkActivity to show a list of saved URL names
                //TODO Clicking an item on list of URL names takes user to that URL in PageViewerFragment
                //TODO Each listed URL name has a trashcan to delete URL
            }
        });





        return l;
    }

    interface BrowserInterface {
        void newPageClicked();

        void saveBookMarkClicked();
        void bookMarkClicked();
    }
}