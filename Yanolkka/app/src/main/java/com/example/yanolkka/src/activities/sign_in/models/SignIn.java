package com.example.yanolkka.src.activities.sign_in.models;


public class SignIn {
    private String id, pw;

    public SignIn(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
