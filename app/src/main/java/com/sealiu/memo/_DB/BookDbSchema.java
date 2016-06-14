package com.sealiu.memo._DB;

/**
 * memoBook Table Schema
 * <p/>
 * Created by root
 * on 6/14/16.
 */
public class BookDbSchema {
    public static final class BookTable {
        public static final String NAME = "memoBook";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DESC = "desc";
            public static final String STATUS = "status";
            public static final String CREATED_TIME = "created_time";
            public static final String MODIFIED_TIME = "modified_time";
            public static final String ACCESS_TIME = "access_time";
            public static final String NEW_COUNT = "new_count";
            public static final String REVIEW_COUNT = "review_count";

        }
    }
}
