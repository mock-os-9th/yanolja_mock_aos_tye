package com.example.yanolkka.src.activities.sign_in.models;

public class SignInResult {
    private String jwt;
    private boolean isSuccess;
    private int code;
    private String message;

    public String getJwt() {
        return jwt;
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
