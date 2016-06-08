package com.sealiu.memo.note;

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
 * on 6/8/16.
 */
public class noteDao implements noteService {

    private memoDbHelper helper = null;

    public noteDao(Context context) {
        helper = new memoDbHelper(context);
    }

    @Override
    public boolean addNote(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql =
                    "INSERT INTO memoNote(" +
                            "book_id, " +
                            "front," +
                            "back," +
                            "status," +
                            "e_f," +
                            "interval," +
                            "created_time," +
                            "modified_time," +
                            "access_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    public boolean delNote(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "DELETE FROM memoNote WHERE id = ?";
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
    public boolean updateNote(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "UPDATE memoNote SET " +
                    "book_id = ?," +
                    "front = ?," +
                    "back = ?," +
                    "status = ?," +
                    "e_f = ?," +
                    "interval = ?," +
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
    public Map<String, String> viewNote(String[] selectionArgs) {
        Map<String, String> map = new HashMap<>();
        SQLiteDatabase db = null;
        try {
            String sql = "SELECT * FROM memoNote WHERE id = ?";
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
        return map;
    }

    @Override
    public List<Map<String, String>> listNotes() {
        List<Map<String, String>> list = new ArrayList<>();

        SQLiteDatabase db = null;
        try {
            String sql = "SELECT * FROM memoNote";
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
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return list;
    }
}
