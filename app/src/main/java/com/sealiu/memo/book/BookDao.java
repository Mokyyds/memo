package com.sealiu.memo.book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sealiu.memo._DB.memoDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Book getActiveBook() {
        Map<String, String> map = new HashMap<>();
        SQLiteDatabase db = null;
        try {
            String sql = "SELECT * FROM memoBook WHERE status = 1";
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);

            int columns = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                for (int i = 0; i < columns; i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                    if (columnValue == null) columnValue = "";
                    map.put(columnName, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        if (map.isEmpty() || map.size() == 0) return null;
        return new Book(map);
    }

    @Override
    public int count() {
        int num = 0;
        SQLiteDatabase db = null;
        try {
            String sql = "SELECT COUNT(id) FROM memoBook";
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            num = cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return num;
    }

    @Override
    public boolean addBook(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql =
                    "INSERT INTO memoBook(" +
                            "name, " +
                            "desc," +
                            "status," +
                            "created_time," +
                            "modified_time," +
                            "access_time) values (?, ?, ?, ?, ?, ?)";
            db = helper.getWritableDatabase();
            db.execSQL(sql, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return flag;
    }

    @Override
    public boolean delBook(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "DELETE FROM memoBook WHERE id = ?";
            db = helper.getWritableDatabase();
            db.execSQL(sql, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return flag;
    }

    @Override
    public boolean updateBook(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "UPDATE memoBook SET " +
                    "name = ?, " +
                    "desc = ?, " +
                    "status = ?," +
                    "modified_time = ?" +
                    "WHERE id = ?";
            db = helper.getWritableDatabase();
            db.execSQL(sql, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return flag;
    }

    @Override
    public Book viewBook(String[] selectionArgs) {
        Map<String, String> map = new HashMap<>();
        SQLiteDatabase db = null;
        try {
            String sql = "SELECT * FROM memoBook WHERE id = ?";
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, selectionArgs);

            int columns = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                for (int i = 0; i < columns; i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                    if (columnValue == null) columnValue = "";
                    map.put(columnName, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        if (map.isEmpty() || map.size() == 0) return null;
        return new Book(map);
    }

    @Override
    public List<Book> listBooks() {
        List<Book> list = new ArrayList<>();

        SQLiteDatabase db = null;
        try {
            String sql = "SELECT * FROM memoBook";
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);

            int columns = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < columns; i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
                    if (columnValue == null) columnValue = "";
                    map.put(columnName, columnValue);
                }
                if (map.isEmpty() || map.size() == 0)
                    list.add(null);
                else
                    list.add(new Book(map));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return list;
    }
}
