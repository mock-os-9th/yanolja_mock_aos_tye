package com.example.yanolkka.src.activities.main.models;

public class UserInfoResult {
    private String UserName;
    private int UserId;
    private String UserPwd;
    private String UserContact;

    private boolean isSuccess;
    private int code;
    private String message;

    public String getUserPwd() {
        return UserPwd;
    }

    public String getUserContact() {
        return UserContact;
    }

    public String getUserName() {
        return UserName;
    }

    public int getUserId() {
        return UserId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
