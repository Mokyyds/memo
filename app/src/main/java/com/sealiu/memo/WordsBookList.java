package com.sealiu.memo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsBookList extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_words_book_list);


        SimpleAdapter adapter = new SimpleAdapter(
                this,
                getData(),
                R.layout.simple_list,
                new String[] {"name", "img"},
                new int[] {R.id.name, R.id.img});
        setListAdapter(adapter);

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "摩托罗拉");
        map.put("img", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<>();
        map.put("name", "诺基亚");
        map.put("img", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<>();
        map.put("name", "三星");
        map.put("img", R.mipmap.ic_launcher);
        list.add(map);
        return list;
    }
}
