package edu.temple.browseractivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BookMarkFragment extends Fragment {

    private static String BOOKMARKS_PREFS = "Bookmarks_file";
    private static String BOOKMARKS_KEY = "Bookmarks_key";
    private static String BOOKMARKS_DELIMITER = Character.toString((char)30);

    View bm;
    ListView bookMarkListView;
    BookMarkListAdapter bookMarkListAdapter;
    ArrayList<String> bookMarkedPages;
    SharedPreferences preferences;

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
        bm.setBackgroundColor(Color.LTGRAY);

        preferences = Objects.requireNonNull(getContext()).getSharedPreferences(BookMarkFragment.BOOKMARKS_PREFS, Context.MODE_PRIVATE);
        String serializedBookmarks = preferences.getString(BookMarkFragment.BOOKMARKS_KEY, null);
        if (serializedBookmarks == null) {
            bookMarkedPages = new ArrayList<String>();
        } else {
            String[] bookmarks = serializedBookmarks.split(BookMarkFragment.BOOKMARKS_DELIMITER);
            this.bookMarkedPages = new ArrayList<String>(Arrays.asList(bookmarks));
        }

        bookMarkListView = bm.findViewById(R.id.bookMarkListView);
        bookMarkListAdapter = new BookMarkListAdapter(getContext(), bookMarkedPages);
        bookMarkListView.setAdapter(bookMarkListAdapter);
        bookMarkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((BookMarkListInterface) Objects.requireNonNull(getActivity())).getPage(position-1);
            }
        });

        return bm;
    }

    public void setBookMark(String pages){
        if (this.preferences == null) {
            preferences = Objects.requireNonNull(getContext()).getSharedPreferences(BookMarkFragment.BOOKMARKS_PREFS, Context.MODE_PRIVATE);
        }

        if (this.bookMarkedPages == null) {
            this.bookMarkedPages = new ArrayList<String>();
        }

        bookMarkedPages.add(pages);

        String serializedBookmarks = String.join(BookMarkFragment.BOOKMARKS_DELIMITER, this.bookMarkedPages);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BookMarkFragment.BOOKMARKS_KEY, serializedBookmarks);
        editor.apply();

        bookMarkListAdapter.notifyDataSetChanged();
    }

    interface BookMarkListInterface{
        void getPage(int reference);
    }
}
