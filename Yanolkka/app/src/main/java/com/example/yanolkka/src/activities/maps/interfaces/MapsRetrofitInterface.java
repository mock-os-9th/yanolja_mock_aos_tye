package com.example.yanolkka.src.activities.maps.interfaces;

import com.example.yanolkka.src.activities.maps.models.MapResult;
import com.example.yanolkka.src.activities.profile.models.EditInfoResult;
import com.example.yanolkka.src.activities.profile.models.NicknameToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToChange;
import com.example.yanolkka.src.activities.profile.models.PasswordToValidate;
import com.example.yanolkka.src.activities.profile.models.UserInfoResult;
import com.example.yanolkka.src.activities.profile.models.ValidResult;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MapsRetrofitInterface {
    @GET("/around/map")
    Call<MapResult> getMapResult(@Query("Latitude") double latitude,
                                 @Query("Longitude") double longitude,
                                 @Query("Scope") int scope,
                                 @Query("CheckInDate") String checkIn,
                                 @Query("CheckOutDate") String checkOut,
                                 @Query("AdultNum") int numAdult,
                                 @Query("ChildNum") int numKid);
}
