package edu.temple.browseractivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookMarkListFragment extends Fragment {

    private static final String KEY = "bookmark_Key";

    View bml;
    TextView bookMarkTextView;
    String page;
    Bundle bundle;

    public BookMarkListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        assert bundle != null;
        page = bundle.getString(KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bml = inflater.inflate(R.layout.fragment_book_mark_list, container, false);
        bml.findViewById(R.id.deleteBtn);
        bookMarkTextView.setText(page);

        return bml;
    }
}