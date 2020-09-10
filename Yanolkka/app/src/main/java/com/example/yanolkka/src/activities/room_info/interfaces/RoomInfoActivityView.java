package com.example.yanolkka.src.activities.room_info.interfaces;

import com.example.yanolkka.src.activities.room_info.models.MotelInfoResult;
import com.example.yanolkka.src.activities.room_info.models.ReviewsInfo;

public interface RoomInfoActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getMotelInfo(MotelInfoResult result);

    void getReviews(ReviewsInfo reviewsInfo);
}
