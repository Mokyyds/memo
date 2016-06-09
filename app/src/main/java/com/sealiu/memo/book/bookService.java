package com.sealiu.memo.book;

import java.util.List;
import java.util.Map;

/**
 * Created by root
 * on 6/7/16.
 */
public interface bookService {

    /**
     * fetch an active memoBook;
     *
     * @return a map which contains the memoBook's column name and value;
     */
    Map<String, String> getActiveBook();

    /**
     * fetch the number of memoBooks;
     *
     * @return number of memoBooks;
     */
    int count();

    /**
     * add a memoBook, which used to record the info of a set of notes;
     *
     * @param params {name, desc, status, created_time, modified_time, access_time}
     * @return a boolean, show whether is the adding operation successful;
     */
    boolean addBook(Object[] params);

    /**
     * delete a memoBook by providing a curtain id;
     *
     * @param params {id}
     * @return a boolean, show whether is the deleting operation successful;
     */
    boolean delBook(Object[] params);

    /**
     * update a memoBook's info
     *
     * @param params {name, desc, status, modified_time}
     * @return a boolean, show whether is the updating operation successful;
     */
    boolean updateBook(Object[] params);

    /**
     * select a memoBook by providing a curtain id;
     *
     * @param selectionArgs {id}
     * @return a map which contains the memoBook's column name and value;
     */
    Map<String, String> viewBook(String[] selectionArgs);

    /**
     * select all memoBook
     *
     * @return list\<map\>
     */
    List<Map<String, String>> listBooks();
}

