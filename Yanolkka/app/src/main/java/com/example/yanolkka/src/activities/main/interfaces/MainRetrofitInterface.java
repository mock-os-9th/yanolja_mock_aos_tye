package com.example.yanolkka.src.activities.main.interfaces;

import com.example.yanolkka.src.activities.main.models.GroupResult;
import com.example.yanolkka.src.activities.main.models.MyPageResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainRetrofitInterface {

    @GET("/myYanolja")
    Call<MyPageResult> getMyPageInfo();

    @GET("/areas")
    Call<GroupResult>getGroups();

}
