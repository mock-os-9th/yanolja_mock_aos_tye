package com.example.yanolkka.src.activities.search_result.models;

public class NearByHotel {
    private int AccomIdx;
    private String AccomThumbnailUrl;
    private String AccomName;
    private double AccomLatitude;
    private double AccomLongtitude;
    private String AllDayTime;
    private int AllDayPrice;
    private double OverallRating;
    private int ReviewCount;

    public int getAccomIdx() {
        return AccomIdx;
    }

    public String getAccomThumbnailUrl() {
        return AccomThumbnailUrl;
    }

    public String getAccomName() {
        return AccomName;
    }

    public double getAccomLatitude() {
        return AccomLatitude;
    }

    public double getAccomLongtitude() {
        return AccomLongtitude;
    }

    public String getAllDayTime() {
        return AllDayTime;
    }

    public int getAllDayPrice() {
        return AllDayPrice;
    }

    public double getOverallRating() {
        return OverallRating;
    }

    public int getReviewCount() {
        return ReviewCount;
    }
}
