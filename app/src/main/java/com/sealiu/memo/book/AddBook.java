package com.sealiu.memo.book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sealiu.memo.MainActivity;
import com.sealiu.memo.R;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        findViewById(R.id.submit_addBook_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText bookNameET = (EditText) findViewById(R.id.book_name);
                EditText bookDescET = (EditText) findViewById(R.id.book_desc);
                CheckBox isActiveCB = (CheckBox) findViewById(R.id.is_active);

                String bookName = String.valueOf(bookNameET.getText());
                String bookDesc = String.valueOf(bookDescET.getText());
                boolean isActive = isActiveCB.isChecked();

                Log.i(MainActivity.TAG, bookName + " " + bookDesc + " " + String.valueOf(isActive));
                bookService bookService = new bookDao(AddBook.this);
                boolean flag = bookService.addBook(new Object[]{bookName, bookDesc, isActive, "", "", ""});

                Log.i(MainActivity.TAG, String.valueOf(flag));
            }
        });
    }
}
