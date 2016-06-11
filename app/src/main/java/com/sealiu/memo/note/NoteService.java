package com.sealiu.memo.note;

import java.util.List;
import java.util.Map;

/**
 * Created by root
 * on 6/8/16.
 */
public interface NoteService {
    boolean addNote(Object[] params);

    boolean delNote(Object[] params);

    boolean updateNote(Object[] params);

    // select only one note by id
    Map<String, String> viewNote(String[] selectionArgs);

    List<Map<String, String>> listNotes();
}
