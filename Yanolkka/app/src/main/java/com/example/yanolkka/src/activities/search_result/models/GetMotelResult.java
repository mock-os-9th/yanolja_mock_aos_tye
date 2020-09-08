package com.example.yanolkka.src.activities.search_result.models;

import java.util.ArrayList;

public class GetMotelResult {
    private ArrayList<MotelResult> result;
    private boolean isSuccess;
    private int code;
    private String message;

    public ArrayList<MotelResult> getResult() {
        return result;
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
