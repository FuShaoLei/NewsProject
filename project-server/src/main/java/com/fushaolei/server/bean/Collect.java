package com.fushaolei.server.bean;

public class Collect {
    private int id;
    private int news_id;
    private int user_id;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", news_id=" + news_id +
                ", user_id=" + user_id +
                ", status=" + status +
                '}';
    }
}
