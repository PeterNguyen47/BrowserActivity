package edu.temple.browseractivity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class BookMarkFragment extends Fragment {

    View bm;
    ListView bookMarkListView;
    BookMarkListAdapter bookMarkListAdapter;
    ArrayList<String> bookMarkedPages;

    public BookMarkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bm = inflater.inflate(R.layout.fragment_book_mark, container, false);

        bookMarkedPages = new ArrayList<String>();

        bookMarkListView = bm.findViewById(R.id.bookMarkListView);
        bookMarkListAdapter = new BookMarkListAdapter(getContext(), bookMarkedPages);
        bookMarkListView.setAdapter(bookMarkListAdapter);

        bm.setBackgroundColor(Color.LTGRAY);

        return bm;
    }

    public void setBookMark(String pages){
        bookMarkedPages.add(pages);
        bookMarkListAdapter.notifyDataSetChanged();
    }
}
