package com.sealiu.memo.note.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.sealiu.memo._DB.NoteDbSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyang
 * on 2016/6/29.
 */
public class Note {

    private String uuid;
    private String book_uuid;
    private String front;
    private String back;
    private int status = 0;
    private Double E_F = 2.5;
    private Double interval = 0d;
    private String created_time;
    private String modified_time;
    private String access_time;

    public Note(Map<String, String> params) {
        this.uuid = params.get("uuid");
        this.book_uuid = params.get("book_uuid");
        this.front = params.get("front");
        this.back = params.get("back");
        this.status = Integer.valueOf(params.get("status"));

        this.E_F = params.get("E_F") == null ? 2.5 : Double.valueOf(params.get("E_F"));
        this.interval = params.get("interval") == null ? 0d : Double.valueOf(params.get("interval"));

        this.created_time = params.get("created_time");
        this.modified_time = params.get("modified_time");
        this.access_time = params.get("access_time");
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(NoteDbSchema.NoteTable.Cols.UUID, this.uuid);
        values.put(NoteDbSchema.NoteTable.Cols.BOOK_UUID, this.book_uuid);
        values.put(NoteDbSchema.NoteTable.Cols.FRONT, this.front);
        values.put(NoteDbSchema.NoteTable.Cols.BACK, this.back);
        values.put(NoteDbSchema.NoteTable.Cols.STATUS, String.valueOf(this.status));
        values.put(NoteDbSchema.NoteTable.Cols.E_F, String.valueOf(this.E_F));
        values.put(NoteDbSchema.NoteTable.Cols.INTERVAL, String.valueOf(this.interval));
        values.put(NoteDbSchema.NoteTable.Cols.CREATED_TIME, this.created_time);
        values.put(NoteDbSchema.NoteTable.Cols.MODIFIED_TIME, this.modified_time);
        values.put(NoteDbSchema.NoteTable.Cols.ACCESS_TIME, this.access_time);
        return values;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBook_uuid() {
        return book_uuid;
    }

    public void setBook_uuid(String book_uuid) {
        this.book_uuid = book_uuid;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getE_F() {
        return E_F;
    }

    public void setE_F(Double e_F) {
        E_F = e_F;
    }

    public Double getInterval() {
        return interval;
    }

    public void setInterval(Double interval) {
        this.interval = interval;
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

    public static Note cursorToNote(Cursor cursor) {
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
        return new Note(map);
    }

    public static List<Note> cursorToNoteList(Cursor cursor) {
        List<Note> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        int cols_len = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            for (int i = 0; i < cols_len; i++) {
                String cols_name = cursor.getColumnName(i);
                String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
                if (cols_value == null) cols_value = "";
                map.put(cols_name, cols_value);
            }
            if (map.size() != 0) list.add(new Note(map));
        }
        return list;
    }
}
