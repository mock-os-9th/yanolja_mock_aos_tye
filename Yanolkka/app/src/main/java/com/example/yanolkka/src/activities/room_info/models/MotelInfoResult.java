package com.example.yanolkka.src.activities.room_info.models;

import java.util.ArrayList;

public class MotelInfoResult {
    private boolean isSuccess;
    private int code;
    private String message;
    private MotelInfo info;
    private ArrayList<String> facility;
    private ArrayList<PhotoInfo> photo;
    private ArrayList<Review> ReviewPreview;
    private ArrayList<MotelRoomInfo> result;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public MotelInfo getInfo() {
        return info;
    }

    public ArrayList<String> getFacility() {
        return facility;
    }

    public ArrayList<MotelRoomInfo> getResult() {
        return result;
    }

    public ArrayList<PhotoInfo> getPhoto() {
        return photo;
    }

    public ArrayList<Review> getReviewPreview() {
        return ReviewPreview;
    }
}
