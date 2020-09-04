package com.example.yanolkka.src.activities.main.interfaces;

import com.example.yanolkka.src.activities.main.models.MyPageResult;
import com.example.yanolkka.src.activities.main.models.UserInfoResult;
import com.example.yanolkka.src.activities.main.models.ValidResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MyPageRetrofitInterface {

    @GET("/myYanolja")
    Call<MyPageResult> getMyPageInfo();

    @GET("/isValidPwd")
    Call<ValidResult> getValidResult(@Body String password);

    @GET("/users")
    Call<UserInfoResult> getUserInfo();
}
