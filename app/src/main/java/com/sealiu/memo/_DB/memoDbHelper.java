package com.sealiu.memo._DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root
 * on 6/7/16.
 */
public class memoDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "memo.db";

    public memoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMemoBook =
                "CREATE TABLE memoBook(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL UNIQUE, " +
                        "desc TEXT, " +
                        "status INTEGER DEFAULT 0, " +
                        "created_time TEXT, " +
                        "modified_time TEXT" +
                        "access_time TEXT)";
        db.execSQL(createTableMemoBook);

        String createTableMemoNote =
                "CREATE TABLE memoNote(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "book_id INTEGER NOT NULL, " +// foreign key: book_id
                        "front TEXT NOT NULL UNIQUE, " +
                        "back TEXT NOT NULL UNIQUE, " +
                        "status INTEGER DEFAULT 0, " +// status=0: new
                        "e_f REAL DEFAULT 2.5, " +
                        "interval REAL 0.0, " +
                        "created_time TEXT, " +
                        "modified_time TEXT" +
                        "access_time TEXT," +
                        "FOREIGN KEY(book_id) REFERENCES memoBook(id))";
        db.execSQL(createTableMemoNote);

        String createIndexBookName = "CREATE INDEX name_index ON memoBook(name)";
        String createIndexNoteFront = "CREATE INDEX front_index ON memoNote(front)";
        String createIndexNoteBack = "CREATE INDEX back_index ON memoNote(back)";

        db.execSQL(createIndexBookName);
        db.execSQL(createIndexNoteFront);
        db.execSQL(createIndexNoteBack);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
