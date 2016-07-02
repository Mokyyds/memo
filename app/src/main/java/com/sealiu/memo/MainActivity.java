package com.sealiu.memo;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sealiu.memo.book.AddBookDialogFragment;
import com.sealiu.memo.book.Service.BookService;
import com.sealiu.memo.book.Service.Impl.BookDao;
import com.sealiu.memo.book.model.Book;
import com.sealiu.memo.note.AddNoteDialogFragment;
import com.sealiu.memo.note.Service.Impl.NoteDao;
import com.sealiu.memo.note.Service.NoteService;
import com.sealiu.memo.note.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AddBookDialogFragment.AddBookDialogListener,
        AddNoteDialogFragment.AddNoteDialogListener {

    // 试试我的分支111

    private static final String TAG = "MainActivity";
    private FragmentManager fm = getFragmentManager();
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = fm.findFragmentById(R.id.content_frame);

        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .replace(R.id.content_frame, fragment, "MAIN")
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fm.beginTransaction()
                    .replace(R.id.content_frame, new MainFragment(), "MAIN")
                    .addToBackStack("MAIN")
                    .commit();
        } else if (id == R.id.nav_words_book) {
            fm.beginTransaction()
                    .replace(R.id.content_frame, new BooksFragment(), "BOOKS")
                    .addToBackStack("BOOKS")
                    .commit();
        } else if (id == R.id.nav_trend) {
            fm.beginTransaction()
                    .replace(R.id.content_frame, new StatisticFragment(), "STATISTIC")
                    .addToBackStack("STATISTIC")
                    .commit();
        } else if (id == R.id.nav_grade) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    /**
     * Implement AddBookDialogListener
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String name, String desc, String status) {

        BookService bookService = new BookDao(MainActivity.this);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());

        Map<String, String> map = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        map.put("uuid", uuid.toString());
        map.put("name", name);
        map.put("desc", desc);
        map.put("status", status);
        map.put("created_time", now);
        map.put("modified_time", now);
        map.put("access_time", "");
        map.put("new_count", "50");
        map.put("review_count", "50");

        ContentValues values = new Book(map).getContentValues();
        long id = bookService.addBook(values);

        if (id != -1)
            Toast.makeText(
                    MainActivity.this,
                    R.string.add_success_toast,
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(
                    MainActivity.this,
                    R.string.add_fail_toast,
                    Toast.LENGTH_SHORT).show();
        MainFragment mainFragment = (MainFragment) getFragmentManager().findFragmentByTag("MAIN");
        mainFragment.updateView();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(MainActivity.this, R.string.add_cancel_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddNoteDialogPositiveClick(DialogFragment dialog, String front, String back, String targetBook) {
        if (targetBook.equals("")) {
            Toast.makeText(
                    MainActivity.this,
                    R.string.no_activeBook_suggest,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (front.equals("") && back.equals("")) {
            Toast.makeText(
                    MainActivity.this,
                    R.string.not_blank_validation,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        NoteService noteService = new NoteDao(MainActivity.this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());

        Map<String, String> map = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        map.put("uuid", uuid.toString());
        map.put("front", front);
        map.put("back", back);
        map.put("status", "0");
        map.put("E_F", "2.5");
        map.put("interval", "0");
        map.put("created_time", now);
        map.put("modified_time", now);
        map.put("access_time", "");

        ContentValues values = new Note(map).getContentValues();
        long id = noteService.addNote(values);

        if (id != -1)
            Toast.makeText(
                    MainActivity.this,
                    R.string.add_success_toast,
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(
                    MainActivity.this,
                    R.string.add_fail_toast,
                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAddNoteDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(MainActivity.this, R.string.add_cancel_toast, Toast.LENGTH_SHORT).show();
    }
}
