package com.sealiu.memo.book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sealiu.memo._DB.BookDbSchema.BookTable;
import com.sealiu.memo._DB.memoDbHelper;

/**
 * Created by root
 * on 6/7/16.
 */
public class BookDao implements BookService {

    private memoDbHelper helper = null;

    public BookDao(Context context) {
        helper = new memoDbHelper(context);
    }

    @Override
    public long addBook(ContentValues values) {
        long id = -1;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            id = db.insert(BookTable.NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return id;
    }

    @Override
    public int delBook(String whereClause, String[] whereArgs) {
        int columns = 0;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            columns = db.delete(BookTable.NAME, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return columns;
    }

    @Override
    public int updateBook(ContentValues values, String whereClause, String[] whereArgs) {
        int columns = 0;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            columns = db.update(BookTable.NAME, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return columns;
    }

    @Override
    public Book queryBook(boolean distinct, String[] columns, String whereClause, String[] whereArgs, String orderBy, String limit) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        Book book = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(distinct, BookTable.NAME, columns, whereClause, whereArgs, null, null, orderBy, limit);
            if (cursor != null)
                book = Book.cursorToBook(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }

        return book;
    }

    @Override
    public int columnsNum(boolean distinct, String whereClause, String[] whereArgs) {
        int columnsNum = 0;
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.query(distinct, BookTable.NAME, null, whereClause, whereArgs, null, null, null, null);
            if (cursor != null)
                columnsNum = cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }

        return columnsNum;
    }
}
