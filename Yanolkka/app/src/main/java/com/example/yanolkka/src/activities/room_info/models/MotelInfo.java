package com.example.yanolkka.src.activities.room_info.models;

import java.util.ArrayList;

public class MotelInfo {
    private String AccomName;
    private String AccomThumbnailUrl;
    private float avgRating;
    private int numOfReview;
    private String guideFromStation;
    private double AccomLatitude, AccomLongitude;
    private String AccomAddress;
    private String AccomIntroduction;
    private String AccomGuide;
    private String Contact;
    private int numOfReviewReply;
    private boolean IsFullParking;
    private ArrayList<AccomTagResult> AccomTag;

    public String getAccomName() {
        return AccomName;
    }

    public String getAccomThumbnailUrl() {
        return AccomThumbnailUrl;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public int getNumOfReview() {
        return numOfReview;
    }

    public String getGuideFromStation() {
        return guideFromStation;
    }

    public double getAccomLatitude() {
        return AccomLatitude;
    }

    public double getAccomLongitude() {
        return AccomLongitude;
    }

    public String getAccomAddress() {
        return AccomAddress;
    }

    public String getAccomIntroduction() {
        return AccomIntroduction;
    }

    public String getAccomGuide() {
        return AccomGuide;
    }

    public String getContact() {
        return Contact;
    }

    public boolean isFullParking() {
        return IsFullParking;
    }

    public int getNumOfReviewReply() {
        return numOfReviewReply;
    }

    public ArrayList<AccomTagResult> getAccomTag() {
        return AccomTag;
    }
}
