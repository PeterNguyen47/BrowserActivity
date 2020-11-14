package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;

public class PageControlFragment extends Fragment {

    private static final String KEY = "key";

    View l;

    EditText editTextURL;

    ControlInterface parentActivity;

    public PageControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ControlInterface) {
            parentActivity = (ControlInterface) context;
        } else {
            throw new RuntimeException(getString(R.string.runTimeException_control));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY, editTextURL.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.fragment_page_control, container, false);

        editTextURL = l.findViewById(R.id.urlEditText);

        l.findViewById(R.id.goBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ControlInterface) Objects.requireNonNull(getActivity())).goClicked(editTextURL.getText().toString());
            }
        });

        l.findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ControlInterface) Objects.requireNonNull(getActivity())).backClicked();
            }
        });

        l.findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ControlInterface) Objects.requireNonNull(getActivity())).nextClicked();
            }
        });
        return l;
    }

    //setter
    public void setText(String link){
        if (editTextURL != null) {
            editTextURL.setText(link);
        }
    }

    interface ControlInterface {
        void goClicked(String URL);
        void backClicked();
        void nextClicked();
        void setURL();
    }
}