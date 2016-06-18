package com.sealiu.memo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //, AddBookDialogFragment.AddBookDialogListener
    public static final String TAG = "TEST";
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);

        if (fragment == null) {
            fragment = new MainFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.content_frame, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new MainFragment())
                    .commit();
        } else if (id == R.id.nav_words_book) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new BooksFragment())
                    .commit();
        } else if (id == R.id.nav_trend) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new StatisticFragment())
                    .commit();
        } else if (id == R.id.nav_grade) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    private void initMemoBook() {
//        Log.i(TAG, "iniMemoBook()");
//        TextView dateTodayTV = (TextView) findViewById(R.id.date_today);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        dateTodayTV.setText(sdf.format(new Date()));
//
//        TextView taskContentTV = (TextView) findViewById(R.id.task_content);
//        Button learnBTN = (Button) findViewById(R.id.learn_btn);
//        Button settingBTN = (Button) findViewById(R.id.setting_btn);
//        TextView memoBookTitleTV = (TextView) findViewById(R.id.memoBook_title);
//        TextView memoBookSubTitleTV = (TextView) findViewById(R.id.memoBook_subtitle);
//        Button allMemoBooksBTN = (Button) findViewById(R.id.allMemoBooks_btn);
//
//        int disabledColor = ContextCompat.getColor(this, R.color.disabledText);
//        int primaryColor = ContextCompat.getColor(this, R.color.colorAccent);
//
//        BookService bookService = new BookDao(MainActivity.this);
//        List<Book> list = bookService.listBooks();
//        Book activeBook = bookService.getActiveBook();
////        Log.i(TAG, "listBooks: " + list.toString());
////        Log.i(TAG, "bookCount: " + list.size());
////        Log.i(TAG, "activeBook: " + activeBook.toString());
//
//
//        if (!list.isEmpty() && activeBook != null) {
//            // task
//            taskContentTV.setText("task\'s content");
//
//            // active
//            memoBookTitleTV.setText(activeBook.getName());
//            String activeBookDesc = activeBook.getDesc();
//            boolean flag = activeBookDesc == null || activeBookDesc.equals("");
//            if (flag) memoBookSubTitleTV.setText(R.string.no_desc);
//            else memoBookSubTitleTV.setText(activeBookDesc);
//        } else if (!list.isEmpty() && activeBook == null) {
//            // task
//            taskContentTV.setText(R.string.no_task);
//
//            // active
//            memoBookTitleTV.setText(R.string.no_activeBook_info);
//            memoBookSubTitleTV.setText(R.string.no_activeBook_suggest);
//            allMemoBooksBTN.setTextColor(primaryColor);
//        } else {
//            // task
//            taskContentTV.setText(R.string.no_task);
//            learnBTN.setClickable(false);
//            learnBTN.setTextColor(disabledColor);
//            settingBTN.setClickable(false);
//            settingBTN.setTextColor(disabledColor);
//
//            // active;
//            memoBookTitleTV.setText(R.string.no_memoBook_info);
//            memoBookSubTitleTV.setText(R.string.no_memoBook_suggest);
//            allMemoBooksBTN.setTextColor(disabledColor);
//        }
//    }

//    /**
//     * Implement AddBookDialogListener
//     */
//    @Override
//    public void onDialogPositiveClick(DialogFragment dialog, String n, String d, String s) {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String now = sdf.format(new Date());
//
//        BookService bookService = new BookDao(MainActivity.this);
//        Object[] params = {n, d, s, now, now, "", "50", "50"};
//        boolean flag = bookService.addBook(params);
//        if (flag)
//            Toast.makeText(
//                    MainActivity.this,
//                    R.string.add_book_success_toast,
//                    Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(
//                    MainActivity.this,
//                    R.string.add_book_fail_toast,
//                    Toast.LENGTH_SHORT).show();
//        initMemoBook();
//    }

//    @Override
//    public void onDialogNegativeClick(DialogFragment dialog) {
//        Toast.makeText(MainActivity.this, R.string.add_book_fail_toast, Toast.LENGTH_SHORT).show();
//    }
}
