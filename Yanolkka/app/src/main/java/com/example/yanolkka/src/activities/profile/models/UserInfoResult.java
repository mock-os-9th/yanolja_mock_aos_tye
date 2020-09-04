package com.example.yanolkka.src.activities.profile.models;

public class UserInfoResult {
    private Result Result;

    private boolean isSuccess;
    private int code;
    private String message;

    public com.example.yanolkka.src.activities.profile.models.Result getResult() {
        return Result;
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
