package com.sealiu.memo;

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

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sealiu.memo.book.AddBookDialogFragment;
import com.sealiu.memo.book.Book;
import com.sealiu.memo.book.BookDao;
import com.sealiu.memo.book.BookService;
import com.sealiu.memo.note.AddNoteDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BookService bookService = new BookDao(MainActivity.this);
//        NoteService noteService = new NoteDao(MainActivity.this);

        List<Book> list = bookService.listBooks();
        Log.i(TAG, "listBooks: " + list.toString());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show current date below the TextView of "Today's Task"
        TextView dateTodayTV = (TextView) findViewById(R.id.date_today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        dateTodayTV.setText(sdf.format(new Date()));

        // Task's Content
        TextView taskContentTV = (TextView) findViewById(R.id.task_content);
        Button learnBTN = (Button) findViewById(R.id.learn_btn);
        Button settingBTN = (Button) findViewById(R.id.setting_btn);

        int disabledColor = ContextCompat.getColor(this, R.color.disabledText);
        int primaryColor = ContextCompat.getColor(this, R.color.colorAccent);

        Log.i(TAG, "bookCount: " + bookService.count());
        if (bookService.count() == 0 || list.isEmpty()) {
            taskContentTV.setText(R.string.no_memoBook_info);
            learnBTN.setClickable(false);
            learnBTN.setTextColor(disabledColor);
            settingBTN.setClickable(false);
            settingBTN.setTextColor(disabledColor);
        }

        // Active memoBook
        TextView memoBookTitleTV = (TextView) findViewById(R.id.memoBook_title);
        TextView memoBookSubTitleTV = (TextView) findViewById(R.id.memoBook_subtitle);
        Button allMemoBooksBTN = (Button) findViewById(R.id.allMemoBooks_btn);
        if (bookService.count() == 0 || list.isEmpty()) {
            memoBookTitleTV.setText(R.string.no_memoBook_info);
            memoBookSubTitleTV.setText(R.string.no_memoBook_suggest);
            allMemoBooksBTN.setTextColor(disabledColor);
        } else { // already have some memoBooks, but no one is active.
            Book activeBook = bookService.getActiveBook();
            if (activeBook != null) {

                Log.i(TAG, "activeBook: " + activeBook.toString());

                memoBookTitleTV.setText(activeBook.getName());

                String activeBookDesc = activeBook.getDesc();
                boolean flag = activeBookDesc == null || activeBookDesc.equals("");

                memoBookSubTitleTV.setText(
                        (flag) ? "No Description" : activeBookDesc
                );

            } else {
                memoBookTitleTV.setText(R.string.no_activeBook_info);
                memoBookSubTitleTV.setText(R.string.no_activeBook_suggest);
                allMemoBooksBTN.setTextColor(primaryColor);
            }
        }

        FloatingActionButton libFab = (FloatingActionButton) findViewById(R.id.lib_add_fab);
        libFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddBookDialogFragment()
                        .show(getFragmentManager(), "addBook_dialog_fragment");
            }
        });

        FloatingActionButton noteFab = (FloatingActionButton) findViewById(R.id.note_add_fab);
        noteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddNoteDialogFragment()
                        .show(getFragmentManager(), "addNote_dialog_fragment");
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
}
