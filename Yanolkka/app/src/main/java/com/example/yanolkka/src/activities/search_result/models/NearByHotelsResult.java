package com.example.yanolkka.src.activities.search_result.models;

import java.util.ArrayList;

public class NearByHotelsResult {
    private ArrayList<NearByHotel> Result;
    private boolean IsSuccess;
    private int Code;
    private String Message;

    public ArrayList<NearByHotel> getResult() {
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
