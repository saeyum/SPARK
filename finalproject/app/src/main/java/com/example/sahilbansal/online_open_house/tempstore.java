package com.example.sahilbansal.online_open_house;
public class tempstore {
    String uid;
    String title;
    String Description;

    public tempstore(String uid, String title, String description) {

        this.uid = uid;
        this.title = title;
        Description = description;
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
