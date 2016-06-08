package com.sealiu.memo;

import android.test.AndroidTestCase;

import com.sealiu.memo._DB.memoDbHelper;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends AndroidTestCase {

    public void testMemoDbTest() throws Exception {
        memoDbHelper memoDbHelper = new memoDbHelper(getContext());
        memoDbHelper.getReadableDatabase();
    }
}