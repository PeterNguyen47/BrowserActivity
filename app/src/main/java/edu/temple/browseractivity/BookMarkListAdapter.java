package edu.temple.browseractivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.temple.browseractivity.PageControlFragment;
import edu.temple.browseractivity.R;

public class BookMarkListAdapter extends BaseAdapter implements android.widget.ListAdapter {
    Context context;
    TextView bookMarkTextView;
    ArrayList<String> bookMarkedPage;
    LayoutInflater inflater;

    public BookMarkListAdapter(Context context_, ArrayList<String> bookMarkedPage){
        this.context = context_;
        this.bookMarkedPage = bookMarkedPage;
    }

    @Override
    public int getCount() {
        return bookMarkedPage.size();
    }

    @Override
    public String getItem(int position) {
        return bookMarkedPage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            bookMarkTextView = new TextView(context);
            bookMarkTextView.setText((String)getItem(position));
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_book_mark_list, null);
        }

        bookMarkTextView = convertView.findViewById(R.id.bookMarkTextView);
        bookMarkTextView.setText(bookMarkedPage.get(position));
        bookMarkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PageControlFragment.ControlInterface) context).goClicked((String) bookMarkTextView.getText());
            }
        });

        convertView.findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bookMarkedPage.remove(position);
                notifyDataSetChanged();
            }
        });

        convertView.findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle(R.string.deleteTitle)
                        .setMessage(R.string.deleteMsg)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, R.string.bookmarkDeleted, Toast.LENGTH_SHORT).show();
                                bookMarkedPage.remove(position);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        return convertView;
    }
}
