package com.sealiu.memo.book;

import java.util.List;

/**
 * Created by root
 * on 6/7/16.
 */
public interface BookService {

    /**
     * fetch an active memoBook;
     *
     * @return an instance of Book
     */
    Book getActiveBook();

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
     * @return an instance of Book;
     */
    Book viewBook(String[] selectionArgs);

    /**
     * select all memoBook
     *
     * @return list\<Book\>
     */
    List<Book> listBooks();
}

