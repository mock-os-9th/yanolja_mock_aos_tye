package com.example.yanolkka.src.activities.room_info.models;

import java.util.ArrayList;

public class ReviewsInfo {
    private ArrayList<OverallRatingResult> OverallRating;
    private ArrayList<Review> Reviews;

    public ArrayList<OverallRatingResult> getOverallRating() {
        return OverallRating;
    }

    public ArrayList<Review> getReviews() {
        return Reviews;
    }
}
