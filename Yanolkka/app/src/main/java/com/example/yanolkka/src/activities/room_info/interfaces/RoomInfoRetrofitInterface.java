package com.example.yanolkka.src.activities.room_info.interfaces;

import com.example.yanolkka.src.activities.room_info.models.MotelInfoResult;
import com.example.yanolkka.src.activities.room_info.models.ReviewsResult;
import com.example.yanolkka.src.activities.sign_in.models.SignIn;
import com.example.yanolkka.src.activities.sign_in.models.SignInResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoomInfoRetrofitInterface {
    @GET("/motels/{accomIdx}")
    Call<MotelInfoResult> getMotelInfo(@Path("accomIdx") int accomIdx,
                                       @Query("startAt") String checkIn,
                                       @Query("endAt") String checkOut,
                                       @Query("motelGroupIdx") int groupIdx,
                                       @Query("adult") int numAdult,
                                       @Query("child") int numKid);

    @GET("/reviews/{AccomIdx}")
    Call<ReviewsResult> getReviewsInfo(@Path("AccomIdx") int accomIdx,
                                       @Query("order") String order);
}
