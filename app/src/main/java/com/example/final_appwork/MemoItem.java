package com.example.final_appwork;

public class MemoItem {
    private int id;
    private String title;
    private String time;
    private String body;

    public MemoItem() {
        super();
        title = "";
        time = "";
        body = "";
    }

    public MemoItem(String title, String time, String body) {
        super();
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
