package edu.temple.browseractivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PageListFragment extends Fragment {

    private static final String URL_KEY = "URLkey";

    View l;

    ListView listView;
    EditText urlEditText;

    String urls;

    webPageInterface parentActivity;

    public PageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof webPageInterface) {
            parentActivity = (webPageInterface) context;
        } else {
            throw new RuntimeException(String.valueOf(R.string.runTimeException_page_list));
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
        l = inflater.inflate(R.layout.fragment_page_list, container, false);

        listView = l.findViewById(R.id.listView);
        urlEditText = l.findViewById(R.id.urlEditText);

        /*if (savedInstanceState != null) {
            urls = savedInstanceState.getString(URL_KEY);
        } else {
            Editable links = urlEditText.getText();
            urls = links.toString();
        }*/

        //listView.setAdapter();

        return l;
    }

    // interface to talk to activity
    public interface webPageInterface {
    }
}

//TODO in landscape, have searched URLs populate listView as a textView
//TODO in landscape, when URLs from listView clicked, corresponding web page is displayed (should notify activity when item is clicked)