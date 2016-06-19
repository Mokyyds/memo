package com.sealiu.memo.book;

import android.content.ContentValues;

/**
 * Created by root
 * on 6/7/16.
 */
public interface BookService {

    /**
     * add a memoBook, which used to record the info of a set of notes;
     */
    long addBook(ContentValues values);

    /**
     * delete a memoBook by providing a curtain id;
     */
    int delBook(String whereClause, String[] whereArgs);

    /**
     * update a memoBook's info
     */
    int updateBook(ContentValues values, String whereClause, String[] whereArgs);

    /**
     * select a memoBook by providing a curtain id;
     */
    Book queryBook(boolean distinct, String[] columns, String whereClause, String[] whereArgs, String orderBy, String limit);

    /**
     *
     */
    int columnsNum(boolean distinct, String whereClause, String[] whereArgs);
}

