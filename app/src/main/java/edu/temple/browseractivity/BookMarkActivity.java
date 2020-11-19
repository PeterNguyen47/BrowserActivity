package edu.temple.browseractivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BookMarkActivity extends AppCompatActivity implements PageViewerFragment.PageViewInterface, PageListFragment.listInterface {

    private static final String SAVE_KEY = "saveKey";

    ImageButton bookMarkBtn, saveBookMarkBtn;
    ListView bookMarkList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bookMarkList);
    }

    @Override
    public void bookMarkClicked() {
        //TODO this activity should open as new screen to show list of saved bookmarks
    }

    @Override
    public void getPage(int reference) {

    }
}
