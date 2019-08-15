package com.example.sahilbansal.online_open_house;



public class addproblems {
   private String title;
    private String Decsription;
    private String uid;
    private String userid;

    public addproblems() {
    }

    public addproblems(String title, String decsription, String uid , String userid) {

        this.title = title;
        this.Decsription = decsription;
        this.uid = uid;
        this.userid = userid;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecsription() {
        return Decsription;
    }

    public void setDecsription(String decsription) {
        Decsription = decsription;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
