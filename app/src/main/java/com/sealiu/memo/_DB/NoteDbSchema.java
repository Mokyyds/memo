package com.sealiu.memo._DB;

/**
 * Created by root
 * on 6/14/16.
 */
public class NoteDbSchema {
    public static final class NoteTable {
        public static final String NAME = "memoNote";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String BOOK_UUID = "book_uuid";
            public static final String FRONT = "front";
            public static final String BACK = "back";
            public static final String STATUS = "status";
            public static final String E_F = "e_f";
            public static final String INTERVAL = "interval";
            public static final String CREATED_TIME = "created_time";
            public static final String MODIFIED_TIME = "modified_time";
            public static final String ACCESS_TIME = "access_time";
        }
    }
}
