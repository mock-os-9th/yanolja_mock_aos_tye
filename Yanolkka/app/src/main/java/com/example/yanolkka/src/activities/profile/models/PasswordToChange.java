package com.example.yanolkka.src.activities.profile.models;

public class PasswordToChange {
    private String UserOldPwd;
    private String UserNewPwd1;
    private String UserNewPwd2;

    public PasswordToChange(String userOldPwd, String userNewPwd1, String userNewPwd2) {
        UserOldPwd = userOldPwd;
        UserNewPwd1 = userNewPwd1;
        UserNewPwd2 = userNewPwd2;
    }
}
