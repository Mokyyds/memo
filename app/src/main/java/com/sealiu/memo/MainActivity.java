package com.sealiu.memo;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sealiu.memo.book.AddBookDialogFragment;
import com.sealiu.memo.book.Book;
import com.sealiu.memo.book.BookDao;
import com.sealiu.memo.book.BookService;
import com.sealiu.memo.note.AddNoteDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AddBookDialogFragment.AddBookDialogListener {

    public static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton libFab = (FloatingActionButton) findViewById(R.id.lib_add_fab);
        libFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddBookDialogFragment()
                        .show(getFragmentManager(), "addBook_dialog_fragment");
            }
        });

        // FloatingActionButton
        FloatingActionButton noteFab = (FloatingActionButton) findViewById(R.id.note_add_fab);
        noteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddNoteDialogFragment().show(getFragmentManager(), "addNote_dialog_fragment");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button moreBtn = (Button) findViewById(R.id.allMemoBooks_btn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WordsBookList.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        initMemoBook();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i(TAG, "onResume");
//
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i(TAG, "onPause");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.i(TAG, "onStop");
//    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_words_book) {
            Intent intent = new Intent(MainActivity.this, WordsBookList.class);
            startActivity(intent);
        } else if (id == R.id.nav_trend) {

        } else if (id == R.id.nav_grade) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initMemoBook() {
        Log.i(TAG, "iniMemoBook()");
        TextView dateTodayTV = (TextView) findViewById(R.id.date_today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateTodayTV.setText(sdf.format(new Date()));

        TextView taskContentTV = (TextView) findViewById(R.id.task_content);
        Button learnBTN = (Button) findViewById(R.id.learn_btn);
        Button settingBTN = (Button) findViewById(R.id.setting_btn);
        TextView memoBookTitleTV = (TextView) findViewById(R.id.memoBook_title);
        TextView memoBookSubTitleTV = (TextView) findViewById(R.id.memoBook_subtitle);
        Button allMemoBooksBTN = (Button) findViewById(R.id.allMemoBooks_btn);

        int disabledColor = ContextCompat.getColor(this, R.color.disabledText);
        int primaryColor = ContextCompat.getColor(this, R.color.colorAccent);

        BookService bookService = new BookDao(MainActivity.this);
        List<Book> list = bookService.listBooks();
        Book activeBook = bookService.getActiveBook();
//        Log.i(TAG, "listBooks: " + list.toString());
//        Log.i(TAG, "bookCount: " + list.size());
//        Log.i(TAG, "activeBook: " + activeBook.toString());


        if (!list.isEmpty() && activeBook != null) {
            // task
            taskContentTV.setText("task\'s content");

            // active
            memoBookTitleTV.setText(activeBook.getName());
            String activeBookDesc = activeBook.getDesc();
            boolean flag = activeBookDesc == null || activeBookDesc.equals("");
            if (flag) memoBookSubTitleTV.setText(R.string.no_desc);
            else memoBookSubTitleTV.setText(activeBookDesc);
        } else if (!list.isEmpty() && activeBook == null) {
            // task
            taskContentTV.setText(R.string.no_task);

            // active
            memoBookTitleTV.setText(R.string.no_activeBook_info);
            memoBookSubTitleTV.setText(R.string.no_activeBook_suggest);
            allMemoBooksBTN.setTextColor(primaryColor);
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
            allMemoBooksBTN.setTextColor(disabledColor);
        }
    }

    /**
     * Implement AddBookDialogListener
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String n, String d, String s) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());

        BookService bookService = new BookDao(MainActivity.this);
        Object[] params = {n, d, s, now, now, "", "50", "50"};
        boolean flag = bookService.addBook(params);
        if (flag)
            Toast.makeText(
                    MainActivity.this,
                    R.string.add_book_success_toast,
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(
                    MainActivity.this,
                    R.string.add_book_fail_toast,
                    Toast.LENGTH_SHORT).show();
        initMemoBook();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(MainActivity.this, R.string.add_book_fail_toast, Toast.LENGTH_SHORT).show();
    }
}
