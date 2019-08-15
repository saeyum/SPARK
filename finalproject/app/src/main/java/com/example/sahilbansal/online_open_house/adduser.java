package com.example.sahilbansal.online_open_house;

public class adduser {
    public String email;
    public String uid;
    public String pass;
    public String id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public adduser() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public adduser(String email, String uid, String pass, String id) {

        this.email = email;
        this.id = id;

        this.uid = uid;
        this.pass = pass;
    }
}
