package com.sealiu.memo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sealiu.memo.book.AddBookDialogFragment;
import com.sealiu.memo.book.Service.BookService;
import com.sealiu.memo.book.Service.Impl.BookDao;
import com.sealiu.memo.book.modle.Book;
import com.sealiu.memo.note.AddNoteDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root
 * on 6/18/16.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FloatingActionButton libFab = (FloatingActionButton) view.findViewById(R.id.lib_add_fab);
        libFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddBookDialogFragment()
                        .show(getFragmentManager(), "ADD_BOOK");
            }

        });

        // FloatingActionButton
        FloatingActionButton noteFab = (FloatingActionButton) view.findViewById(R.id.note_add_fab);
        noteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddNoteDialogFragment().show(getFragmentManager(), "ADD_NOTE");
            }
        });

        this.view = view;
        updateView();
        return view;
    }

    public void updateView() {
        Log.i(TAG, "updateView()");

        TextView dateTodayTV = (TextView) view.findViewById(R.id.date_today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateTodayTV.setText(sdf.format(new Date()));

        TextView taskContentTV = (TextView) view.findViewById(R.id.task_content);
        Button learnBTN = (Button) view.findViewById(R.id.learn_btn);
        Button settingBTN = (Button) view.findViewById(R.id.setting_btn);
        TextView memoBookTitleTV = (TextView) view.findViewById(R.id.memoBook_title);
        TextView memoBookSubTitleTV = (TextView) view.findViewById(R.id.memoBook_subtitle);

        int disabledColor = ContextCompat.getColor(getActivity(), R.color.disabledText);
        int primaryColor = ContextCompat.getColor(getActivity(), R.color.colorAccent);

        BookService bookService = new BookDao(getActivity());
        int bookCount = bookService.columnsNum(true, null, null);
        Book activeBook = bookService.queryBook(
                true,
                null,
                "status = ?",
                new String[]{"1"},
                null,
                "1"
        );

        if (bookCount != 0 && activeBook != null) {
            // task
            taskContentTV.setText("task\'s content");

            // active
            memoBookTitleTV.setText(activeBook.getName());
            String activeBookDesc = activeBook.getDesc();
            boolean flag = activeBookDesc == null || activeBookDesc.equals("");
            if (flag) memoBookSubTitleTV.setText(R.string.no_desc);
            else memoBookSubTitleTV.setText(activeBookDesc);
        } else if (bookCount != 0 && activeBook == null) {
            // task
            taskContentTV.setText(R.string.no_task);

            // active
            memoBookTitleTV.setText(R.string.no_activeBook_info);
            memoBookSubTitleTV.setText(R.string.no_activeBook_suggest);
        } else {
            // task
            taskContentTV.setText(R.string.no_task);
            learnBTN.setClickable(false);
            learnBTN.setTextColor(disabledColor);
            settingBTN.setClickable(false);
            settingBTN.setTextColor(disabledColor);

            // active;
            memoBookTitleTV.setText(R.string.no_memoBook_info);
            memoBookSubTitleTV.setText(R.string.no_memoBook_suggest);
        }
    }
}
