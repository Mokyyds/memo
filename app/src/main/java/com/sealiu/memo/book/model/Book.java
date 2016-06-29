package com.sealiu.memo.book.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.sealiu.memo._DB.BookDbSchema.BookTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root
 * on 6/11/16.
 */
public class Book {
    private String uuid;
    private String name;
    private String desc;
    private int status = 0;
    private String created_time;
    private String modified_time;
    private String access_time;
    private int new_count = 50;
    private int review_count = 50;

    public Book(Map<String, String> params) {
        this.uuid = params.get("uuid");
        this.name = params.get("name") == null ? "" : params.get("name");
        this.desc = params.get("desc") == null ? "" : params.get("desc");
        this.status = Integer.valueOf(params.get("status"));
        this.created_time = params.get("created_time");
        this.modified_time = params.get("modified_time");
        this.access_time = params.get("access_time");
        this.new_count = Integer.valueOf(params.get("new_count"));
        this.review_count = Integer.valueOf(params.get("review_count"));
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BookTable.Cols.UUID, this.uuid);
        values.put(BookTable.Cols.NAME, this.name);
        values.put(BookTable.Cols.DESC, this.desc);
        values.put(BookTable.Cols.STATUS, String.valueOf(this.status));
        values.put(BookTable.Cols.CREATED_TIME, this.created_time);
        values.put(BookTable.Cols.MODIFIED_TIME, this.modified_time);
        values.put(BookTable.Cols.ACCESS_TIME, this.access_time);
        values.put(BookTable.Cols.NEW_COUNT, String.valueOf(this.new_count));
        values.put(BookTable.Cols.REVIEW_COUNT, String.valueOf(this.review_count));
        return values;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public String getAccess_time() {
        return access_time;
    }

    public void setAccess_time(String access_time) {
        this.access_time = access_time;
    }

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public static Book cursorToBook(Cursor cursor) {
        int cols_len = cursor.getColumnCount();
        Map<String, String> map = new HashMap<>();
        while (cursor.moveToNext()) {
            for (int i = 0; i < cols_len; i++) {
                String cols_name = cursor.getColumnName(i);
                String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                if (cols_value == null) cols_value = "";
                map.put(cols_name, cols_value);
            }
        }
        if (map.size() == 0) return null;
        return new Book(map);
    }

    public static List<Book> cursorToBookList(Cursor cursor) {
        List<Book> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        int cols_len = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            for (int i = 0; i < cols_len; i++) {
                String cols_name = cursor.getColumnName(i);
                String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                if (cols_value == null) cols_value = "";
                map.put(cols_name, cols_value);
            }
            if (map.size() != 0) list.add(new Book(map));
        }
        return list;
    }
}
