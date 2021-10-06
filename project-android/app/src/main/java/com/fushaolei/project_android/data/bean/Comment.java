package com.fushaolei.project_android.data.bean;

public class Comment {
    private int id;
    private String name;
    private String date;
    private String avator;
    private String content;

    public Comment(String name, String date, String avator, String content) {
        this.name = name;
        this.date = date;
        this.avator = avator;
        this.content = content;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
