package com.sealiu.memo.DB;

import java.util.List;
import java.util.Map;

/**
 * Created by root
 * on 6/7/16.
 */
public interface bookService {
    boolean addBook(Object[] params);

    boolean delBook(Object[] params);

    boolean updateBook(Object[] params);

    Map<String, String> viewBook(String[] selectionArgs);

    List<Map<String, String>> listBooks(String[] selectionArgs);
}

