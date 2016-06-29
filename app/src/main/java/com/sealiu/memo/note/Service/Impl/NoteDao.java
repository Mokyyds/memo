package com.sealiu.memo.note.Service.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sealiu.memo._DB.NoteDbSchema.NoteTable;
import com.sealiu.memo._DB.memoDbHelper;
import com.sealiu.memo.note.Service.NoteService;
import com.sealiu.memo.note.model.Note;

import java.util.List;

/**
 * Created by root
 * on 6/8/16.
 */
public class NoteDao implements NoteService {

    private memoDbHelper helper = null;

    public NoteDao(Context context) {
        helper = new memoDbHelper(context);
    }


    @Override
    public long addNote(ContentValues values) {
        long id = -1;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            id = db.insert(NoteTable.NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return id;
    }

    @Override
    public int delNote(String whereClause, String[] whereArgs) {
        int columns = 0;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            columns = db.delete(NoteTable.NAME, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return columns;
    }

    @Override
    public int updateNote(ContentValues values, String whereClause, String[] whereArgs) {
        int columns = 0;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            columns = db.update(NoteTable.NAME, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return columns;
    }

    @Override
    public Note queryNote(boolean distinct, String[] columns, String whereClause, String[] whereArgs, String orderBy, String limit) {
        Cursor cursor;
        SQLiteDatabase db = null;
        Note note = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.query(distinct, NoteTable.NAME, columns, whereClause, whereArgs, null, null, orderBy, limit);
            if (cursor != null)
                note = Note.cursorToNote(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }
        return note;
    }

    @Override
    public List<Note> queryNoteList(boolean distinct, String[] columns, String whereClause, String[] whereArgs, String orderBy, String limit) {
        Cursor cursor;
        SQLiteDatabase db = null;
        List<Note> list = null;

        try {
            db = helper.getReadableDatabase();
            cursor = db.query(distinct, NoteTable.NAME, columns, whereClause, whereArgs, null, null, orderBy, limit);
            if (cursor != null)
                list = Note.cursorToNoteList(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }

        return list;
    }

    @Override
    public int columnsNum(boolean distinct, String whereClause, String[] whereArgs) {
        int columnsNum = 0;
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.query(distinct, NoteTable.NAME, null, whereClause, whereArgs, null, null, null, null);
            if (cursor != null) {
                columnsNum = cursor.getCount();
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) db.close();
        }

        return columnsNum;
    }
}
