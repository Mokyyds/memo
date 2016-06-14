package com.sealiu.memo.book;

import java.util.Map;

/**
 * Created by root
 * on 6/11/16.
 */
public class Book {
    private int id;
    private String name;
    private String desc;
    private int status = 0;
    private String created_time;
    private String modified_time;
    private String access_time;
    private int new_count = 50;
    private int review_count = 50;

    public Book(Map<String, String> params) {
        this.id = Integer.valueOf(params.get("id"));
        this.name = params.get("name");
        this.desc = params.get("desc");
        this.status = Integer.valueOf(params.get("status"));
        this.created_time = params.get("created_time");
        this.modified_time = params.get("modified_time");
        this.access_time = params.get("access_time");
        this.new_count = Integer.valueOf(params.get("new_count"));
        this.review_count = Integer.valueOf(params.get("review_count"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getModified_time() {
        return modified_time;
    }

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public String getAccess_time() {
        return access_time;
    }

    public void setAccess_time(String access_time) {
        this.access_time = access_time;
    }

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }
}
