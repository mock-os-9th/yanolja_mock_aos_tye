package com.example.yanolkka.src.activities.main.interfaces;

import com.example.yanolkka.src.activities.main.models.MyPageResult;
import com.example.yanolkka.src.activities.profile.models.UserInfoResult;
import com.example.yanolkka.src.activities.profile.models.ValidResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MyPageRetrofitInterface {

    @GET("/myYanolja")
    Call<MyPageResult> getMyPageInfo();
}
