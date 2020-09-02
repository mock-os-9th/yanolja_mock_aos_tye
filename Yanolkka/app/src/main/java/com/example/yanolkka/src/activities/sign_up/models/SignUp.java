package com.example.yanolkka.src.activities.sign_up.models;

public class SignUp {
    private String UserId, UserPwd, UserName, UserBirth, UserContact, UserGender;

    public SignUp(String id, String pw, String phoneNum){
        this.UserId = id;
        this.UserPwd = pw;
        this.UserName = "아무개";
        this.UserBirth = "1900-01-01";
        this.UserContact = phoneNum;
        this.UserGender = "F";
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserBirth() {
        return UserBirth;
    }

    public String getUserContact() {
        return UserContact;
    }

    public String getUserGender() {
        return UserGender;
    }
}
