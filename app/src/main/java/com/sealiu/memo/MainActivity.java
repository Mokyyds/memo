package com.sealiu.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.sealiu.memo.book.bookDao;
import com.sealiu.memo.book.bookService;
import com.sealiu.memo.note.AddNote;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookService bookService = new bookDao(MainActivity.this);
//        noteService noteService = new noteDao(MainActivity.this);

//        Object[] params = {"GET", "4"};
//        boolean flag = bookService.addBook(params);
//        Log.i(TAG, String.valueOf(flag));
//
//        String[] selectionArgs = {"1"};
//        Map<String, String> map = bookService.viewBook(selectionArgs);
//        Log.i(TAG, map.toString());
//
//        Object[] params1 = {"GET", "6"};
//        boolean flag1 = bookService.addBook(params1);
//        Log.i(TAG, String.valueOf(flag1));
//
//        List<Map<String, String>> list = bookService.listBooks(null);
//        Log.i(TAG, list.toString());

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

        Log.i(TAG, bookService.count() + "");
        if (bookService.count() == 0) {
            taskContentTV.setText(R.string.empty_memoBook_info);
            learnBTN.setClickable(false);
            learnBTN.setTextColor(getResources().getColor(R.color.disabledText));
            settingBTN.setClickable(false);
            settingBTN.setTextColor(getResources().getColor(R.color.disabledText));
        }

        FloatingActionButton libFab = (FloatingActionButton) findViewById(R.id.lib_add_fab);
        libFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "libFab Clicked", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton noteFab = (FloatingActionButton) findViewById(R.id.note_add_fab);
        noteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "noteFab Clicked", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button moreBtn = (Button) findViewById(R.id.more_btn);
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
