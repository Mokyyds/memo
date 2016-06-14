package com.sealiu.memo._DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sealiu.memo._DB.BookDbSchema.BookTable;
import com.sealiu.memo._DB.NoteDbSchema.NoteTable;

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
                "create table " + BookTable.NAME + " (" +
                        "_id integer primary key autoincrement, " +
                        BookTable.Cols.UUID + ", " +
                        BookTable.Cols.NAME + ", " +
                        BookTable.Cols.DESC + ", " +
                        BookTable.Cols.STATUS + " DEFAULT '0', " +
                        BookTable.Cols.CREATED_TIME + ", " +
                        BookTable.Cols.MODIFIED_TIME + ", " +
                        BookTable.Cols.ACCESS_TIME + ", " +
                        BookTable.Cols.NEW_COUNT + " DEFAULT '50', " +
                        BookTable.Cols.REVIEW_COUNT + " DEFAULT '50')";
        db.execSQL(createTableMemoBook);

        String createTableMemoNote =
                "create table " + NoteTable.NAME + " (" +
                        "_id integer primary key autoincrement, " +
                        NoteTable.Cols.UUID + ", " +
                        NoteTable.Cols.BOOK_UUID + ", " +
                        NoteTable.Cols.FRONT + ", " +
                        NoteTable.Cols.BACK + ", " +
                        NoteTable.Cols.STATUS + " DEFAULT '0', " +
                        NoteTable.Cols.E_F + " DEFAULT '2.5', " +
                        NoteTable.Cols.INTERVAL + " DEFAULT '0', " +
                        NoteTable.Cols.CREATED_TIME + ", " +
                        NoteTable.Cols.MODIFIED_TIME + ", " +
                        NoteTable.Cols.ACCESS_TIME + ", " +
                        "FOREIGN KEY(" + NoteTable.Cols.BOOK_UUID + ") REFERENCES " + BookTable.NAME + "(" + BookTable.Cols.UUID + "))";
        db.execSQL(createTableMemoNote);

        String createIndexBookName = "CREATE INDEX name_index ON " + BookTable.NAME + "(" + BookTable.Cols.NAME + ")";
        String createIndexNoteFront = "CREATE INDEX front_index ON " + NoteTable.NAME + "(" + NoteTable.Cols.FRONT + ")";
        String createIndexNoteBack = "CREATE INDEX back_index ON " + NoteTable.NAME + "(" + NoteTable.Cols.BACK + ")";

        db.execSQL(createIndexBookName);
        db.execSQL(createIndexNoteFront);
        db.execSQL(createIndexNoteBack);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
