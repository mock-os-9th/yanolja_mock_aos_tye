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
    @GET("/motels/{AccomIdx}")
    Call<MotelInfoResult> getMotelInfo(@Path("AccomIdx") int accomIdx,
                                       @Query("CheckInDate") String checkIn,
                                       @Query("CheckOutDate") String checkOut,
                                       @Query("AdultNum") int numAdult,
                                       @Query("ChildNum") int numKid);

    @GET("/reviews/{AccomIdx}")
    Call<ReviewsResult> getReviewsInfo(@Path("AccomIdx") int accomIdx,
                                       @Query("order") String order);
}
