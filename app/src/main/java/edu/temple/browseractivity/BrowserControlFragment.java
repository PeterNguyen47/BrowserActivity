package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

public class BrowserControlFragment extends Fragment {

    private static final String KEY = "key";

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
        return l;
    }

    interface BrowserInterface {
        void newPageClicked();
    }
}