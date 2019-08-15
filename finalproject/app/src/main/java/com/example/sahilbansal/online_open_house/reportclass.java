package com.example.sahilbansal.online_open_house;

public class reportclass {

    String problem;
    String uid;
    String email;

    public reportclass() {
    }

    public reportclass(String problem, String uid, String email) {

        this.problem = problem;
        this.uid = uid;
        this.email = email;
    }

    public String getProblem() {

        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
