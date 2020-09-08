package com.example.yanolkka.src.activities.search_result.interfaces;

import com.example.yanolkka.src.activities.search_result.models.MotelResult;
import com.example.yanolkka.src.activities.search_result.models.NearByHotelsResult;
import com.example.yanolkka.src.activities.search_result.models.NearByMotelsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchResultRetrofitInterface {
    @GET("/around-motels")
    Call<NearByMotelsResult> getNearbyMotels(@Query("Latitude")double lat,
                                             @Query("Longitude")double lon,
                                             @Query("CheckInDate")String checkIn,
                                             @Query("CheckOutDate")String checkOut,
                                             @Query("AdultNum")int numAdult,
                                             @Query("ChildNum")int numKid);

    @GET("/around-hotels")
    Call<NearByHotelsResult> getNearbyHotels(@Query("Latitude")double lat,
                                             @Query("Longitude")double lon,
                                             @Query("CheckInDate")String checkIn,
                                             @Query("CheckOutDate")String checkOut,
                                             @Query("AdultNum")int numAdult,
                                             @Query("ChildNum")int numKid);

    @GET("/motels")
    Call<MotelResult>getMotelResult(@Query("startAt")String startAt,
                                    @Query("endAt")String endAt,
                                    @Query("motelGroupIdx")int groupIdx,
                                    @Query("adult")int numAdult,
                                    @Query("child")int numKid);
}
