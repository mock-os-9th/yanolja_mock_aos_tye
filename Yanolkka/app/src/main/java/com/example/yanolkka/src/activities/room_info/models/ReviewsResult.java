package com.example.yanolkka.src.activities.room_info.models;

public class ReviewsResult {
    private ReviewsInfo Result;
    private boolean IsSuccess;
    private int Code;
    private String Message;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public ReviewsInfo getResult() {
        return Result;
    }
}
