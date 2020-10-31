package com.example.final_appwork;

public class MemoItem {
    private int id;
    private String userId;
    private String title;
    private String time;
    private String body;

    public MemoItem() {
        super();
        userId = "";
        title = "";
        time = "";
        body = "";
    }

    public MemoItem(String userId, String title, String time, String body) {
        super();
        this.userId = userId;
        this.title = title;
        this.time = time;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
