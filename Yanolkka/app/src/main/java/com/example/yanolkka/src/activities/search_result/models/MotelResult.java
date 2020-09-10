package com.example.yanolkka.src.activities.search_result.models;

import java.util.ArrayList;

public class MotelResult {
    private int AccomIdx;
    private String AccomName;
    private String AccomThumbnailUrl;
    private float AvgRating;
    private int NumOfReview;
    private int NumOfUserPick;
    private String GuideFromStation;
    private boolean IsPartTimeAvailable;
    private int PartTimePrice;
    private String PartTimeHour;
    private boolean IsAllDayAvailable;
    private String AvailableAllDayCheckIn;
    private int AllDayPrice;
    private ArrayList<AccomTag> AccomTag;

    public int getAccomIdx() {
        return AccomIdx;
    }

    public String getAccomName() {
        return AccomName;
    }

    public String getAccomThumbnailUrl() {
        return AccomThumbnailUrl;
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

    public String getGuideFromStation() {
        return GuideFromStation;
    }

    public boolean isPartTimeAvailable() {
        return IsPartTimeAvailable;
    }

    public boolean isAllDayAvailable() {
        return IsAllDayAvailable;
    }

    public ArrayList<com.example.yanolkka.src.activities.search_result.models.AccomTag> getAccomTag() {
        return AccomTag;
    }

    public int getPartTimePrice() {
        return PartTimePrice;
    }

    public String getPartTimeHour() {
        return PartTimeHour;
    }

    public String getAvailableAllDayCheckIn() {
        return AvailableAllDayCheckIn;
    }

    public int getAllDayPrice() {
        return AllDayPrice;
    }
}
