package com.example.yanolkka.src.activities.main.models;

import java.util.ArrayList;

public class GroupResult {
    private ArrayList<Group> result;
    private boolean isSuccess;
    private String message;
    private int code;

    public ArrayList<Group> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
