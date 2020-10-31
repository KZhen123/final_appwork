package com.example.final_appwork;

public class UserItem {
    private int id;
    private String userId;
    private String name;
    private String pwd;

    public UserItem() {
        super();
        userId = "";
        name="";
        pwd="";
    }

    public UserItem(String userId, String name, String pwd) {
        super();
        this.userId = userId;
        this.name = name;
        this.pwd=pwd;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd=pwd;
    }
}
