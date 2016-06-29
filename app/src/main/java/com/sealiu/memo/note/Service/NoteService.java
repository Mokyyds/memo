package com.sealiu.memo.note.Service;

import android.content.ContentValues;

import com.sealiu.memo.note.model.Note;

import java.util.List;

/**
 * Created by root
 * on 6/8/16.
 */
public interface NoteService {
    long addNote(ContentValues values);

    int delNote(String whereClause, String[] whereArgs);

    int updateNote(ContentValues values, String whereClause, String[] whereArgs);

    Note queryNote(boolean distinct, String[] columns, String whereClause, String[] whereArgs, String orderBy, String limit);

    List<Note> queryNoteList(boolean distinct, String[] columns, String whereClause, String[] whereArgs, String orderBy, String limit);

    int columnsNum(boolean distinct, String whereClause, String[] whereArgs);
}
