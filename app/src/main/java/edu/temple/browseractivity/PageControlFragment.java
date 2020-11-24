package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;

import edu.temple.browseractivity.R;

public class PageControlFragment extends Fragment {

    private static final String KEY = "key";

    View l;
    EditText urlEditText;

    ControlInterface controlInterface;

    public PageControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ControlInterface) {
            controlInterface = (ControlInterface) context;
        } else {
            throw new RuntimeException(getString(R.string.runTimeException_control));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY, urlEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        l = inflater.inflate(R.layout.fragment_page_control, container, false);

        urlEditText = l.findViewById(R.id.urlEditText);

        l.findViewById(R.id.goBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ((ControlInterface) Objects.requireNonNull(getContext())).goClicked(urlEditText.getText().toString());
            }

        });

        l.findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ControlInterface) Objects.requireNonNull(getContext())).backClicked();
            }
        });

        l.findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ControlInterface) Objects.requireNonNull(getContext())).nextClicked();
            }
        });

        return l;
    }

    public void setText(String link){
        if (urlEditText != null) {
            urlEditText.setText(link);
        }
    }

    interface ControlInterface {
        void goClicked(String URL);
        void backClicked();
        void nextClicked();
        void setURL();
    }
}