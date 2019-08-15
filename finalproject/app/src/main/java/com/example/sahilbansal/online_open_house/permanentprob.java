package com.example.sahilbansal.online_open_house;

public class permanentprob {
   public String title;
   public String desc;
   public String uid;

    public permanentprob() {
    }

    public permanentprob(String title, String desc, String uid) {

        this.title = title;
        this.desc = desc;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
