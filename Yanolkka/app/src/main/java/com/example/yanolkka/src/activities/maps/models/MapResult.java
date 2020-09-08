package com.example.yanolkka.src.activities.maps.models;

import java.util.ArrayList;

public class MapResult {
    private ArrayList<AccomResult> Result;
    private boolean IsSuccess;
    private int Code;
    private String Message;

    public ArrayList<AccomResult> getResult() {
        return Result;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }
}
