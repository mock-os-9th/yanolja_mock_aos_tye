package com.example.yanolkka.src.activities.search_result.models;

import java.util.ArrayList;

public class MotelResult {
    private int AccomIdx;
    private int RoomIdx;
    private String AccomName;
    private String AccomThumbnailUrl;
    private boolean IsPartTimeAvailable;
    private String ReservedCheckIn;
    private String ReservedCheckOut;
    private boolean IsAllDayAvailable;
    private float AvgRating;
    private int NumOfReview;
    private int NumOfUserPick;
    private ArrayList<AccomTag> AccomTag;

    public int getAccomIdx() {
        return AccomIdx;
    }

    public int getRoomIdx() {
        return RoomIdx;
    }

    public String getAccomName() {
        return AccomName;
    }

    public String getAccomThumbnailUrl() {
        return AccomThumbnailUrl;
    }

    public boolean isPartTimeAvailable() {
        return IsPartTimeAvailable;
    }

    public String getReservedCheckIn() {
        return ReservedCheckIn;
    }

    public String getReservedCheckOut() {
        return ReservedCheckOut;
    }

    public boolean isAllDayAvailable() {
        return IsAllDayAvailable;
    }

    public float getAvgRating() {
        return AvgRating;
    }

    public int getNumOfReview() {
        return NumOfReview;
    }

    public int getNumOfUserPick() {
        return NumOfUserPick;
    }

    public ArrayList<com.example.yanolkka.src.activities.search_result.models.AccomTag> getAccomTag() {
        return AccomTag;
    }
}
