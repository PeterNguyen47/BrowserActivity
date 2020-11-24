package edu.temple.browseractivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class BrowserControlFragment extends Fragment {

    View l;

    SharedPreferences preferences;

    BrowserInterface browserInterface;
    BookMarkInterface bookMarkInterface;

    public BrowserControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BrowserInterface && context instanceof BookMarkInterface) {
            browserInterface = (BrowserInterface) context;
            bookMarkInterface = (BookMarkInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_browser_control));
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_browser_control, container, false);

        l.findViewById(R.id.newPageBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BrowserInterface) Objects.requireNonNull(getContext())).newPageClicked();
            }
        });

        l.findViewById(R.id.saveBookMarkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BrowserControlFragment.BookMarkInterface) Objects.requireNonNull(getContext())).setBookedPage();
            }
        });

        l.findViewById(R.id.bookmarkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BookMarkInterface) Objects.requireNonNull(getContext())).showBookMarkFragment();
            }
        });

        return l;
    }

    interface BrowserInterface {
        void newPageClicked();
    }

    interface BookMarkInterface {
        void showBookMarkFragment();
        void setBookedPage();
    }
}